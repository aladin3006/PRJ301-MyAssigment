/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
import entity.Group;
import entity.Subject;
import entity.Term;
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
public class GroupDBContext extends DBContext<Group> {

    public ArrayList<Group> getSubjectbyDepartment(int did) {
        ArrayList<Group> subjects = new ArrayList<>();
        try {
            String sql = "SELECT d.did,d.dname, s.subid,s.subname \n"
                    + "FROM [Subject] s INNER JOIN [Department] d ON d.did = s.did\n"
                    + "		WHERE d.did=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, did);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                Department department = new Department();
                department.setId(rs.getInt("did"));
                department.setName(rs.getString("dname"));
                subject.setDept(department);
                group.setSubject(subject);
                subjects.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public ArrayList<Group> getGroupbySubjectandTerm(int subid, int termid) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "SELECT s.subid,s.subname, g.gid,g.gname, t.termid,t.termname \n"
                    + "FROM [Subject] s INNER JOIN [Group] g ON g.subid = s.subid\n"
                    + "				INNER JOIN [Term] t ON t.termid = g.termid\n"
                    + "		WHERE s.subid=? AND t.termid=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            stm.setInt(2, termid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("gid"));
                group.setName(rs.getString("gname"));
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                group.setSubject(subject);
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                t.setName(rs.getString("termname"));
                group.setTerm(t);
                groups.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
