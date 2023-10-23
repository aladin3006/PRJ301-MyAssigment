 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

//import controller.authentication.BasedRequiredAuthenticationController;
import dal.ScheduleDBContext;
import dal.TimeSlotDBContext;
import dal.AccountDBContext;
import entity.Schedule;
import entity.Account;
import entity.TimeSlot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class ScheduleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)//, Account account)
            throws ServletException, IOException {
        //int instructorid = Integer.parseInt(request.getParameter("id"));
        AccountDBContext aID = new AccountDBContext();
        ArrayList<Account> getId = aID.getIid();
        int instructorid = (getId.isEmpty()) ? 0 : getId.get(0).getInstructor().getId();

        String r_from = request.getParameter("from");
        String r_to = request.getParameter("to");
        ArrayList<Date> dates = new ArrayList<>();

        if (r_from == null)//this week
        {
            dates = DateTimeHelper.getCurrentWeekDates();
        } else {
            try {
                dates = DateTimeHelper.getSqlDatesInRange(r_from, r_to);
            } catch (ParseException ex) {
                Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.list();

        ScheduleDBContext scheDB = new ScheduleDBContext();
        ArrayList<Schedule> schedules = scheDB.getSchedules(instructorid, dates.get(0), dates.get(dates.size() - 1));

        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("from", dates.get(0));
        request.setAttribute("to", dates.get(dates.size() - 1));
        request.setAttribute("schedules", schedules);
        request.setAttribute("getId", getId);

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)//, Account account)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)//, Account account)
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
