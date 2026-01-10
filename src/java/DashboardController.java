/*
 * File: DashboardController.java
 * Purpose: Loads data (Appointments) before showing the JSP
 */
package controller;

import dao.AppointmentDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;

@WebServlet(name = "DashboardController", urlPatterns = {"/DashboardController"})
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String userID = (String) session.getAttribute("userSession");

        // Security check
        if (role == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AppointmentDAO appDao = new AppointmentDAO();

        if ("student".equals(role)) {
            // 1. Fetch Student's Appointments
            List<Appointment> list = appDao.getAppointmentsByStudent(userID);
            
            // 2. Attach data to request
            request.setAttribute("appointmentList", list);
            
            // 3. Forward to Student JSP
            request.getRequestDispatcher("student_dashboard.jsp").forward(request, response);

        } else if ("counselor".equals(role)) {
            // 1. Fetch Pending Appointments for Counselor
            List<Appointment> pending = appDao.getPendingAppointments();
            
            // 2. Attach data
            request.setAttribute("pendingApps", pending);
            
            // 3. Forward to Counselor JSP
            request.getRequestDispatcher("counselor_dashboard.jsp").forward(request, response);
        }
    }
}