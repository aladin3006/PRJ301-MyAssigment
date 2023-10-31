/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import entity.Campus;
import entity.Group;
import entity.Room;
import entity.Schedule;
import entity.Student;
import entity.Subject;
import entity.TimeSlot;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adamin
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public ArrayList<Attendance> getAttendances(int scheid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        try {
            String sql = "SELECT s.stuid,s.stuname,stu_code,stud_fullname,ISNULL(a.status,0) as [status], \n"
                    + "  ISNULL(a.description,'') as [description],\n"
                    + "  ISNULL(a.att_datetime, GETDATE()) as [att_datetime],\n"
                    + "  a.scheid\n"
                    + "  FROM [Schedule] sche INNER JOIN [Group] g ON sche.gid = g.gid\n"
                    + "								INNER JOIN [Group_Student] gs ON g.gid = gs.gid\n"
                    + "								INNER JOIN [Student] s ON s.stuid = gs.stuid\n"
                    + "								LEFT JOIN Attendance a ON s.stuid = a.stuid \n"
                    + "								AND sche.scheid = a.scheid\n"
                    + "	WHERE sche.scheid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, scheid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Student student = new Student();
                Schedule schedule = new Schedule();
                student.setId(rs.getInt("stuid"));
                student.setName(rs.getString("stuname"));
                student.setCode(rs.getString("stu_code"));
                student.setFullName(rs.getString("stud_fullname"));
                schedule.setId(scheid);
                att.setStudent(student);
                att.setSchedule(schedule);
                att.setStatus(rs.getBoolean("status"));
                att.setDescription(rs.getString("description"));
                att.setDatetime(rs.getTimestamp("att_datetime"));
                atts.add(att);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    public ArrayList<Attendance> getTimetableStudent(int stuid, Date from, Date to) {
        ArrayList<Attendance> timetables = new ArrayList<>();
        try {
            String sql = "SELECT  stu.stuid, sche.gid, g.gname, g.subid,\n"
                    + "sche.date,  sche.isAtt, sche.scheid,\n"
                    + "su.subid, subname, r.roomid, t.tid, t.tname, a.status, c.cname \n"
                    + "FROM [Student] stu \n"
                    + "JOIN [Group_Student] gs on stu.stuid = gs.stuid\n"
                    + "JOIN [Group] g on g.gid = gs.gid\n"
                    + "JOIN [Subject] su ON g.subid = su.subid\n"
                    + "JOIN [Schedule] sche on sche.gid = g.gid\n"
                    + "JOIN [Campus] c ON stu.cid = c.cid\n"
                    + "LEFT JOIN [TimeSlot] t ON sche.tid = t.tid \n"
                    + "LEFT JOIN [Room] r ON r.roomid = sche.rid\n"
                    + "LEFT JOIN [Attendance] a on a.stuid = stu.stuid AND a.scheid = sche.scheid\n"
                    + "WHERE stu.stuid = ? AND sche.[date] >= ? AND sche.[date] <= ?";   
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance timetable = new Attendance();
                timetable.setStatus(rs.getBoolean("status"));
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("scheid"));
                schedule.setDate(rs.getDate("date"));
                schedule.setIsAtt(rs.getBoolean("isAtt"));
                timetable.setSchedule(schedule);
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
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                timetable.setSubject(subject);
                Campus campus = new Campus();
                campus.setName(rs.getString("cname"));
                timetable.setCampus(campus);
                timetables.add(timetable);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timetables;
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
