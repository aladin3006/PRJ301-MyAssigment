/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Group_Student;
import entity.Room;
import entity.Subject;
import entity.TimeSlot;
import entity.Schedule;
import entity.Student;
import entity.Instructor;
import entity.Attendance;
import java.sql.Date;
//import entity.Department;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ScheduleDBContext extends DBContext<Schedule> {

    public ArrayList<Schedule> getSchedules(int iid, Date from, Date to) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String sql = "SELECT s.scheid,s.date,r.roomid,t.tid,t.tname,g.gid,g.gname,su.subid,subname,i.iid,i.iname,s.isAtt\n"
                    + "FROM [Schedule] s INNER JOIN [Instructor] i ON i.iid = s.iid\n"
                    + "				INNER JOIN [Group] g ON g.gid = s.gid\n"
                    + "				INNER JOIN [TimeSlot] t ON s.tid = t.tid\n"
                    + "				INNER JOIN [Room] r ON r.roomid = s.rid\n"
                    + "				INNER JOIN [Subject] su ON g.subid = su.subid\n"
                    + "		WHERE i.iid = ? AND s.[date] >= ? AND s.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, iid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("scheid"));
                schedule.setDate(rs.getDate("date"));
                schedule.setIsAtt(rs.getBoolean("isAtt"));
                Instructor instructor = new Instructor();
                instructor.setId(rs.getInt("iid"));
                schedule.setInstructor(instructor);
                Room room = new Room();
                room.setRid(rs.getString("roomid"));
                schedule.setRoom(room);
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setName(rs.getString("tname"));
                schedule.setTime(t);
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                schedule.setGroup(g);
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                schedule.setSubject(subject);
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    public ArrayList<Schedule> getTimetables(int stuid, Date from, Date to) {
        ArrayList<Schedule> timetables = new ArrayList<>();
        try {
            String sql = "SELECT s.scheid,s.date,r.roomid,t.tid,t.tname,g.gid,g.gname,su.subid,subname,s.isAtt\n"
                    + "FROM [Schedule] s "                 
                    + "				INNER JOIN [Group] g ON g.gid = s.gid\n"
                    + "                         INNER JOIN [Group_Student] gs ON g.gid = gs.gid\n"
                    + "                         INNER JOIN [Student] stu ON stu.stuid = gs.stuid\n"
                    + "				INNER JOIN [TimeSlot] t ON s.tid = t.tid\n"
                    + "				INNER JOIN [Room] r ON r.roomid = s.rid\n"
                    + "				INNER JOIN [Subject] su ON g.subid = su.subid\n"
                    + "		WHERE stu.stuid = ? AND s.[date] >= ? AND s.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule timetable = new Schedule();
                timetable.setId(rs.getInt("scheid"));
                timetable.setDate(rs.getDate("date"));
                timetable.setIsAtt(rs.getBoolean("isAtt"));
                Student student = new Student();
                student.setId(rs.getInt("stuid"));
                timetable.setStudent(student);
                Room room = new Room();
                room.setRid(rs.getString("roomid"));
                timetable.setRoom(room);
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setName(rs.getString("tname"));
                timetable.setTime(t);
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                timetable.setGroup(g);
                //Group_Student gs = new Group_Student();
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                timetable.setSubject(subject);
                timetables.add(timetable);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timetables;
    }

    public void addAttendances(Schedule sche) {
        try {
            connection.setAutoCommit(false);
            String sql_update_isAtt = "UPDATE [Schedule] SET isAtt = 1 WHERE scheid =?";
            PreparedStatement stm_update_isAtt = connection.prepareStatement(sql_update_isAtt);
            stm_update_isAtt.setInt(1, sche.getId());
            stm_update_isAtt.executeUpdate();

            String sql_remove_atts = "DELETE Attendance WHERE scheid =?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setInt(1, sche.getId());
            stm_remove_atts.executeUpdate();

            for (Attendance att : sche.getAtts()) {
                String sql_insert_att = "INSERT INTO [Attendance]\n"
                        + "           ([scheid]\n"
                        + "           ,[stuid]\n"
                        + "           ,[status]\n"
                        + "           ,[description]\n"
                        + "           ,[att_datetime])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1, sche.getId());
                stm_insert_att.setInt(2, att.getStudent().getId());
                stm_insert_att.setBoolean(3, att.isStatus());
                stm_insert_att.setString(4, att.getDescription());
                stm_insert_att.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Schedule> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Schedule entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Schedule entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Schedule get(Schedule entity) {
        try {
            String sql = "SELECT s.scheid,s.date,r.roomid,t.tid,t.description,g.gid,g.gname,su.subid,subname,i.iid,i.iname,s.isAtt\n"
                    + "FROM [Schedule] s INNER JOIN [Instructor] i ON i.iid = s.iid\n"
                    + "				INNER JOIN [Group] g ON g.gid = s.gid\n"
                    + "				INNER JOIN [TimeSlot] t ON s.tid = t.tid\n"
                    + "				INNER JOIN [Room] r ON r.roomid = s.rid\n"
                    + "				INNER JOIN [Subject] su ON g.subid = su.subid\n"
                    + "		WHERE s.scheid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("scheid"));
                schedule.setDate(rs.getDate("date"));
                schedule.setIsAtt(rs.getBoolean("isAtt"));
                Room room = new Room();
                room.setRid(rs.getString("roomid"));
                schedule.setRoom(room);
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                schedule.setTime(t);
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                schedule.setGroup(g);
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                schedule.setSubject(subject);
                return schedule;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
