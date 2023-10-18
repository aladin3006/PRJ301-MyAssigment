/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Schedule;
import entity.Account;
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

    @Override
    public Schedule get(Schedule s) {

        try {
            String sql = "SELECT s.[slot]\n"
                    + "      ,s.[day]\n"
                    + "      ,s.[year]\n"
                    + "      ,s.[week_number]\n"
                    + "      ,s.[week_description]\n"
                    + "      ,s.[weekdays]\n"
                    + "      ,s.[classID]\n"
                    + "      ,s.[roomID]\n"
                    + "      ,s.[course]\n"
                    + "      ,a.[name]\n"
                    + "  FROM [Schedule] s INNER JOIN Account a \n"
                    + "  ON s.name = a.name WHERE [aname] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                s.setSlot(rs.getInt("slot"));
                s.setDay(rs.getDate("day"));
                s.setYear(rs.getInt("year"));
                s.setWeek_number(rs.getInt("week_number"));
                s.setWeek_description(rs.getString("week_description"));
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
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "SELECT s.[slot]\n"
                    + "      ,s.[day]\n"
                    + "      ,s.[year]\n"
                    + "      ,s.[week_number]\n"
                    + "      ,s.[week_description]\n"
                    + "      ,s.[weekdays]\n"
                    + "      ,s.[classID]\n"
                    + "      ,s.[roomID]\n"
                    + "      ,s.[course]\n"
                    + "      ,a.[name]\n"
                    + "  FROM [Schedule] s INNER JOIN Account a \n"
                    + "  ON s.name = a.name WHERE [aname] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSlot(rs.getInt("slot"));
                s.setDay(rs.getDate("day"));
                s.setYear(rs.getInt("year"));
                s.setWeek_number(rs.getInt("week_number"));
                s.setWeek_description(rs.getString("week_description"));
                s.setWeekdays(rs.getString("weekdays"));
                s.setClassID(rs.getString("classID"));
                s.setRoomID(rs.getString("roomID"));
                s.setCourse(rs.getString("course"));

                Account a = new Account();
                a.setName(rs.getString("dname"));
                s.setAct(a);
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Schedule> search(int year, int week_number, String week_description){
 
        ArrayList<Schedule> list = new ArrayList<>();
        String sql = "SELECT [slot]\n"
                    + "      ,[day]\n"
                    + "      ,[year]\n"
                    + "      ,[week_number]\n"
                    + "      ,[week_description]\n"
                    + "      ,[weekdays]\n"
                    + "      ,[classID]\n"
                    + "      ,[roomID]\n"
                    + "      ,[course]\n"
                    + "  FROM [Schedule] where 1=1 ";
        if(year!=0){
            sql+=" and year="+year;
        }
        if(week_number!=0){
            sql+=" and week_number="+week_number;
        }
        if( week_description!= null && !week_description.equals("")){
            sql+=" and week_description like '%"+week_description+"%'";
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSlot(rs.getInt("slot"));
                s.setDay(rs.getDate("day"));
                s.setYear(rs.getInt("year"));
                s.setWeek_number(rs.getInt("week_number"));
                s.setWeek_description(rs.getString("week_description"));
                s.setWeekdays(rs.getString("weekdays"));
                s.setClassID(rs.getString("classID"));
                s.setRoomID(rs.getString("roomID"));
                s.setCourse(rs.getString("course"));

                Account a = new Account();
                a.setName(rs.getString("dname"));
                s.setAct(a);
                list.add(s);
            }
        } catch (SQLException ex) {
            
        }
        return list;
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
