/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
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
public class TermDBContext extends DBContext<Term> {

    @Override
    public ArrayList<Term> list() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            String sql = "SELECT [termid]\n"
                    + "      ,[termname],[startdate],[enddate]\n"
                    + "  FROM [Term]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                t.setName(rs.getString("termname"));
                t.setStartdate(rs.getDate("startdate"));
                t.setEnddate(rs.getDate("enddate"));
                terms.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return terms;
    }

    @Override
    public void insert(Term entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Term entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Term entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Term get(Term entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
