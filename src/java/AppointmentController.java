/*
 * File: AppointmentController.java
 * Purpose: Handles Create and Update actions for appointments
 */
package controller;

import dao.AppointmentDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;

@WebServlet(name = "AppointmentController", urlPatterns = {"/AppointmentController"})
public class AppointmentController extends HttpServlet {

    // Handle Creation (Form Submission)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        AppointmentDAO dao = new AppointmentDAO();

        if ("create".equals(action)) {
            // From make_appointment.jsp
            String issue = request.getParameter("appointmentIssue");
            String date = request.getParameter("appointmentDate");
            String time = request.getParameter("appointmentTime");
            String studentID = request.getParameter("studentID");

            Appointment app = new Appointment();
            app.setAppointmentIssue(issue);
            app.setAppointmentDate(date);
            app.setAppointmentTime(time);
            app.setStudentID(studentID);

            dao.addAppointment(app);
            
            // Redirect back to Dashboard Controller to reload the list
            response.sendRedirect("DashboardController");
        }
    }

    // Handle Status Updates (Links/Buttons)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");
        HttpSession session = request.getSession();

        if ("update".equals(action) && idStr != null) {
            int appID = Integer.parseInt(idStr);
            String status = request.getParameter("status"); // 'Accepted' or 'Rejected'
            String counselorID = (String) session.getAttribute("userSession");

            AppointmentDAO dao = new AppointmentDAO();
            dao.updateStatus(appID, status, counselorID);

            response.sendRedirect("DashboardController");
        }
    }
}