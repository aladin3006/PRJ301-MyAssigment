/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Room;
import entity.Subject;
import entity.TimeSlot;
import entity.Schedule;
import entity.Account;
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

    public ArrayList<Schedule> getSchedules (int iid, Date from, Date to) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String sql = "SELECT s.scheid,s.date,r.roomid,t.tid,t.tname,g.gid,g.gname,su.subid,subname,i.iid,i.iname\n"
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
    
//    public ArrayList<Schedule> getInstructorid (String aname, String icode ) {
//        ArrayList<Schedule> iid = new ArrayList<>();
//        try {
//            
//        } catch (SQLException ex) {
//             Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       return iid;
//    }

    @Override
    public ArrayList<Schedule> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Schedule get(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
