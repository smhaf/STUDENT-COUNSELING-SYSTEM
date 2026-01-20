package controller;

import dao.StudentDAO;
import model.Student;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 1. URL Mapping: Matches <form action="UpdateProfileServlet"> in your JSP
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 2. Retrieve form data
        // Note: We do NOT retrieve "matrixNumber" because it was read-only/not sent
        String id = request.getParameter("studentID"); // Hidden input
        String name = request.getParameter("studentName");
        String email = request.getParameter("studentEmail");
        String phone = request.getParameter("studentPhone");
        String course = request.getParameter("course");

        // 3. Prepare the Student object
        Student studentToUpdate = new Student();
        studentToUpdate.setStudentID(id);
        studentToUpdate.setStudentName(name);
        studentToUpdate.setStudentEmail(email);
        studentToUpdate.setStudentPhone(phone);
        studentToUpdate.setCourse(course);

        // 4. Update the Database
        StudentDAO dao = new StudentDAO();
        boolean success = dao.updateStudentProfile(studentToUpdate);

        if (success) {
            // --- SUCCESS FLOW ---
            
            HttpSession session = request.getSession();

            session.setAttribute("currentUserName",name );
           
            response.sendRedirect("DashboardServlet");
            
        } else {
            // --- FAILURE FLOW ---
            response.sendRedirect("edit_profile.jsp?error=Update failed. Please try again.");
        }
    }
}