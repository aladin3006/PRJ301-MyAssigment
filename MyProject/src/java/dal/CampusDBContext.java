/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Campus;
import entity.Instructor;
import entity.Student;
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
public class CampusDBContext extends DBContext<Campus> {

    public ArrayList<Campus> listbyStu(int stuid) {
        ArrayList<Campus> campusStu = new ArrayList<>();
        try {
            String sql = "SELECT c.cid, c.cname\n"
                    + "  FROM [Campus] c INNER JOIN [Student] s ON s.cid = c.cid\n"
                    + " WHERE s.stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Campus c = new Campus();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                Student s = new Student();
                s.setId(rs.getInt("stuid"));
                c.setStudent(s);
                campusStu.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campusStu;
    }
    
    public ArrayList<Campus> listbyIns(int iid) {
        ArrayList<Campus> campusIns = new ArrayList<>();
        try {
            String sql = "SELECT c.cid, c.cname\n"
                    + "  FROM [Campus] c INNER JOIN [Instructor] i ON i.cid = c.cid\n"
                    + " WHERE i.iid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, iid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Campus c = new Campus();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                Instructor i = new Instructor();
                i.setId(rs.getInt("iid"));
                c.setInstructor(i);
                campusIns.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campusIns;
    }

    @Override
    public void insert(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Campus get(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Campus> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
