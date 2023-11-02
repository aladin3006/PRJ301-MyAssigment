/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import dal.GroupDBContext;
import dal.TermDBContext;
import dal.DepartmentDBContext;
import dal.SubjectDBContext;
import entity.Department;
import entity.Group;
import entity.Subject;
import entity.Term;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class StudentListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String termid_raw = request.getParameter("termid");
        termid_raw = (termid_raw == null) ? "0" : termid_raw;
        int termid = Integer.parseInt(termid_raw);
        GroupDBContext group2 = new GroupDBContext();
        ArrayList<Group> groups2 = group2.getGroupbyTerm(termid);

        String did_raw = request.getParameter("did");
        did_raw = (did_raw == null) ? "0" : did_raw;
        int did = Integer.parseInt(did_raw);
        GroupDBContext subject = new GroupDBContext();
        ArrayList<Group> subjects = subject.getSubjectbyDepartment(did);

        String subid_raw = request.getParameter("subid");
        subid_raw = (subid_raw == null) ? "0" : subid_raw;
        int subid = Integer.parseInt(subid_raw);
        GroupDBContext group1 = new GroupDBContext();
        ArrayList<Group> groups1 = group1.getGroupbySubject(subid);
        
        String gid_raw = request.getParameter("gid");
        gid_raw = (gid_raw == null) ? "0" : gid_raw;
        int gid = Integer.parseInt(gid_raw);
        GroupDBContext student = new GroupDBContext();
        ArrayList<Group> students = student.getStudentbyGroup(gid);
        

        TermDBContext termDB = new TermDBContext();
        ArrayList<Term> terms = termDB.list();

        DepartmentDBContext deptDB = new DepartmentDBContext();
        ArrayList<Department> depts = deptDB.list();
        
        SubjectDBContext subDB = new SubjectDBContext();
        ArrayList<Subject> subs = subDB.list();

        request.setAttribute("subjects", subjects);
        request.setAttribute("groups1", groups1);
        request.setAttribute("groups2", groups2);
        request.setAttribute("students", students);

        request.setAttribute("terms", terms);
        request.setAttribute("depts", depts);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("../view/instructor/studentlist.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
