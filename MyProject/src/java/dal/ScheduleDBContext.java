/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Schedule;
import entity.Account;
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

    @Override
    public Schedule get(Schedule s) {

        try {
            String sql = "SELECT s.[slot]\n"
                    + "      ,s.[day]\n"
                    + "      ,s.[weekdays]\n"
                    + "      ,s.[classID]\n"
                    + "      ,s.[roomID]\n"
                    + "      ,s.[course]\n"
                    + "  FROM [Schedule] s INNER JOIN Account a \n"
                    + "  ON s.name = a.name WHERE [aname] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                
                s.setSlot(rs.getInt("slot"));
                s.setDay(rs.getDate("day"));
                s.setWeekdays(rs.getString("weekdays"));
                s.setClassID(rs.getString("classID"));
                s.setRoomID(rs.getString("roomID"));
                s.setCourse(rs.getString("course"));
                
                Account a = new Account();
                a.setName(rs.getString("dname"));
                s.setAct(a);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
}
