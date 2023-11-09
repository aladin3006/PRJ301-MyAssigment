/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Score;
import entity.Student;
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
public class ScoreDBContext extends DBContext<Score> {

    public ArrayList<Score> getSubjectbyStudent(int stuid) {
        ArrayList<Score> allCourses = new ArrayList<>();
        try {
            String sql = "WITH MinGID AS (\n"
                    + "    SELECT su.subid, MIN(g.gid) AS min_gid\n"
                    + "    FROM [Group] g\n"
                    + "    INNER JOIN [Subject] su ON g.subid = su.subid\n"
                    + "    GROUP BY su.subid)\n"
                    + "    SELECT s.stuid,s.stuname,s.stu_code,s.stud_fullname, g.gid,g.gname,g.termid, "
                    + "          su.subname,su.subid,su.description, sdt.category,sdt.value,sdt.status, t.termname,t.startdate,t.enddate \n"
                    + "   FROM [Student] s INNER JOIN [Group_Student] gs ON s.stuid = gs.stuid\n"
                    + "                 INNER JOIN [Group] g ON g.gid = gs.gid\n"
                    + "                 INNER JOIN [Term] t ON g.termid = t.termid\n"
                    + "                 INNER JOIN [Subject] su ON g.subid = su.subid\n"
                    + "                 INNER JOIN [Subjects_detail] sdt ON sdt.subid = su.subid  "
                    + "                 INNER JOIN MinGID mg ON g.gid = mg.min_gid"
                    + "		WHERE s.stuid=? AND sdt.status IS NOT NULL";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Score score = new Score();
                score.setCategory(rs.getString("category"));
                score.setValue(rs.getDouble("value"));
                score.setStatus(rs.getBoolean("status"));
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                subject.setDescription(rs.getString("description"));
                Group group = new Group();
                group.setId(rs.getInt("gid"));
                group.setName(rs.getString("gname"));
                subject.setGroup(group);
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                t.setName(rs.getString("termname"));
                t.setStartdate(rs.getDate("startdate"));
                t.setEnddate(rs.getDate("enddate"));
                subject.setTerm(t);
                Student student = new Student();
                student.setId(rs.getInt("stuid"));
                subject.setStudent(student);
                score.setSubject(subject);
                allCourses.add(score);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCourses;
    }

    public ArrayList<Score> getSubjectbyTerm(int termid) {
        ArrayList<Score> courses = new ArrayList<>();
        try {
            String sql = "SELECT s.stuid,s.stuname,s.stu_code,s.stud_fullname, g.gid,g.gname, su.subid, su.subname \n"
                    + "FROM [Student] s INNER JOIN [Group_Student] gs ON s.stuid = gs.stuid\n"
                    + "                 INNER JOIN [Group] g ON g.gid = gs.gid\n"
                    + "                 INNER JOIN [Term] t ON g.termid = t.termid\n"
                    + "                 INNER JOIN [Subject] su ON g.subid = su.subid  "
                    + "		WHERE t.termid=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, termid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Score score = new Score();
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                Group group = new Group();
                group.setId(rs.getInt("gid"));
                group.setName(rs.getString("gname"));
                subject.setGroup(group);
                Student student = new Student();
                student.setId(rs.getInt("stuid"));
                subject.setStudent(student);
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                t.setName(rs.getString("termname"));
                subject.setTerm(t);
                score.setSubject(subject);
                courses.add(score);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<Score> getScorebySubject(int subid) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            String sql = "SELECT sdt.subid,sdt.category,sdt.type,sdt.weight,sdt.value,sdt.comment,sdt.status, su.subname,su.description \n"
                    + "FROM [Subjects_detail] sdt INNER JOIN [Subject] su ON sdt.subid = su.subid\n"
                    + "		WHERE sdt.subid=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Score score = new Score();
                score.setCategory(rs.getString("category"));
                score.setType(rs.getString("type"));
                score.setWeight(rs.getString("weight"));
                score.setValue(rs.getDouble("value"));
                score.setComment(rs.getString("comment"));
                score.setStatus(rs.getBoolean("status"));
                Subject subject = new Subject();
                subject.setId(rs.getInt("subid"));
                subject.setName(rs.getString("subname"));
                subject.setDescription(rs.getString("description"));
//                Student student = new Student();
//                student.setId(rs.getInt("stuid"));
//                subject.setStudent(student);
                score.setSubject(subject);
                scores.add(score);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }

    @Override
    public ArrayList<Score> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Score get(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
