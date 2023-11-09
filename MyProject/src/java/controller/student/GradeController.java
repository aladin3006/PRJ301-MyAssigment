/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.AccountDBContext;
import dal.ScoreDBContext;
import dal.SubjectDBContext;
import dal.TermDBContext;
import entity.Account;
import entity.Score;
import entity.Subject;
import entity.Term;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
import controller.authentication.BasedAuthorizationController;
import controller.authentication.BasedRequiredAuthenticationController;
import entity.Role;
public class GradeController extends BasedAuthorizationController {

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
        HttpSession session = request.getSession();
        Account loggedUser = (Account) session.getAttribute("account");

        if (loggedUser != null) {
            String aname = loggedUser.getName();
            AccountDBContext dbContext = new AccountDBContext();
            ArrayList<Account> accountList = dbContext.getStuid(aname);

            int stuid = -1; // Default value if not found
            for (Account account : accountList) {
                if (account.getStudent() != null) {
                    stuid = account.getStudent().getId();
                    break;
                }
            }
            ScoreDBContext allCourse = new ScoreDBContext();
            ArrayList<Score> allCourses = allCourse.getSubjectbyStudent(stuid);

            String termid_raw = request.getParameter("termid");
            termid_raw = (termid_raw == null) ? "0" : termid_raw;
            int termid = Integer.parseInt(termid_raw);
            ScoreDBContext courseDB = new ScoreDBContext();
            ArrayList<Score> courses = courseDB.getSubjectbyTerm(termid);

            TermDBContext termDB = new TermDBContext();
            ArrayList<Term> terms = termDB.list();

            SubjectDBContext subDB = new SubjectDBContext();
            ArrayList<Subject> subs = subDB.list();

            request.setAttribute("allCourses", allCourses);
            request.setAttribute("courses", courses);

            request.setAttribute("terms", terms);
            request.setAttribute("subs", subs);
        }
        String stuid_raw = request.getParameter("stuid");
        if (stuid_raw != null) {
            int stuid = Integer.parseInt(stuid_raw);
            ScoreDBContext viewGrade = new ScoreDBContext();
            ArrayList<Score> allCourses = viewGrade.getSubjectbyStudent(stuid);
            request.setAttribute("allCourses", allCourses);
        }       
        request.getRequestDispatcher("../view/student/gradeManage.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
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
