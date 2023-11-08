/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedAuthorizationController;
import controller.authentication.BasedRequiredAuthenticationController;
import dal.ScheduleDBContext;
import dal.TimeSlotDBContext;
import dal.AccountDBContext;
import dal.CampusDBContext;
import entity.Schedule;
import entity.Account;
import entity.Campus;
import entity.Role;
import entity.TimeSlot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
public class ScheduleController extends BasedAuthorizationController {

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
            ArrayList<Account> accountList = dbContext.getIid(aname);

            int instructorid = -1; // Default value if not found
            for (Account account : accountList) {
                if (account.getInstructor() != null) {
                    instructorid = account.getInstructor().getId();
                    break;
                }
            }

            String r_from = request.getParameter("from");
            String r_to = request.getParameter("to");
            ArrayList<Date> dates = new ArrayList<>();

            if (r_from == null) {
                dates = DateTimeHelper.getCurrentWeekDates();
            } else {
                try {
                    dates = DateTimeHelper.getSqlDatesInRange(r_from, r_to);
                } catch (ParseException ex) {
                    Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            CampusDBContext campDB = new CampusDBContext();
            ArrayList<Campus> campusIns = campDB.listbyIns(instructorid);

            TimeSlotDBContext timeDB = new TimeSlotDBContext();
            ArrayList<TimeSlot> slots = timeDB.list();

            ScheduleDBContext scheDB = new ScheduleDBContext();
            ArrayList<Schedule> schedules = scheDB.getSchedules(instructorid, dates.get(0), dates.get(dates.size() - 1));

            request.setAttribute("slots", slots);
            request.setAttribute("dates", dates);
            request.setAttribute("campusIns", campusIns);
            request.setAttribute("from", dates.get(0));
            request.setAttribute("to", dates.get(dates.size() - 1));
            request.setAttribute("schedules", schedules);
        }

        request.getRequestDispatcher("../view/instructor/lecturerHome.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)//, Account account)
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
