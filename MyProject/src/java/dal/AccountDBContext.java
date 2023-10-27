/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.Feature;
import entity.Instructor;
import entity.Role;
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
public class AccountDBContext extends DBContext<Account> {

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(Account entity) {

        try {
            String sql = "SELECT username,displayname,typeAccount,aname FROM Account\n"
                    + "WHERE username = ? AND [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getUsername());
            stm.setString(2, entity.getPassword());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setDisplayname(rs.getString("displayname"));
                account.setTypeAccount(rs.getInt("typeAccount"));
                account.setName(rs.getString("aname"));

                return account;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Account> getIid(String aname) {
        ArrayList<Account> getId = new ArrayList<>();
        try {
            String sql = "SELECT a.username,a.displayname,a.typeAccount, a.aname, i.iid\n"
                    + "  FROM [Account] a INNER JOIN [Instructor] i ON i.icode = a.aname";
            if (aname != null) {
                sql += " WHERE a.aname = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            if (aname != null) {
                stm.setString(1, aname);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setDisplayname(rs.getString("displayname"));
                a.setTypeAccount(rs.getInt("typeAccount"));

                Instructor ins = new Instructor();
                ins.setId(rs.getInt("iid"));
                a.setInstructor(ins);
                getId.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getId;
    }
    
    public ArrayList<Account> getStuid(String aname) {
        ArrayList<Account> getId = new ArrayList<>();
        try {
            String sql = "SELECT a.username,a.displayname,a.typeAccount, a.aname, s.stuid\n"
                    + "  FROM [Account] a INNER JOIN [Studnet] s ON s.stuname = a.aname";
            if (aname != null) {
                sql += " WHERE a.aname = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            if (aname != null) {
                stm.setString(1, aname);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setDisplayname(rs.getString("displayname"));
                a.setTypeAccount(rs.getInt("typeAccount"));

                Student students = new Student();
                students.setId(rs.getInt("stuid"));
                a.setStudent(students);
                getId.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getId;
    }
    
    

    public ArrayList<Role> getRolesAndFeatures(String username, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.rid,r.rname,f.fid,f.url FROM \n"
                    + "Account a INNER JOIN [Role_Account] ra \n"
                    + "	ON a.username = ra.username \n"
                    + "INNER JOIN [Role] r\n"
                    + "	ON ra.rid = r.rid\n"
                    + "INNER JOIN Role_Feature rf \n"
                    + "	ON rf.rid= r.rid\n"
                    + "INNER JOIN Feature f\n"
                    + "	ON f.fid = rf.fid\n"
                    + "WHERE \n"
                    + "	a.username = ? AND f.url = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));

                Feature f = new Feature();
                f.setId(rs.getInt("fid"));
                f.setUrl(rs.getString("url"));

                r.getFeatures().add(f);
                roles.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
}
