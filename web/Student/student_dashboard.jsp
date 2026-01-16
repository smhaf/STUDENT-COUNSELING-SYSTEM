<%-- 
    File: student_dashboard.jsp
    Purpose: View for students to see their appointments.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%-- Import your Model classes here once created, e.g., page import="model.Appointment" --%>

<%
    // 1. SESSION CHECK (Security)
    // If no session exists or role is not student, redirect to login
    if (session.getAttribute("userSession") == null || !session.getAttribute("role").equals("student")) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <style>
        /* Reuse your dashboard CSS */
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #3498db; color: white; }
    </style>
</head>
<body>
    <h1>Welcome, <%= session.getAttribute("currentUserName") %>!</h1>
    
    <nav>
        <a href="student_dashboard.jsp">Dashboard</a> | 
        <a href="make_appointment.jsp">Book Appointment</a> | 
        <a href="LogoutServlet">Logout</a>
    </nav>

    <h3>Your Appointments</h3>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Issue</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%-- 
               This loop retrieves the list of appointments sent by the Controller.
               We will write the Controller later to fill this list.
            --%>
            <% 
                List<Object> appointments = (List<Object>) request.getAttribute("appointmentList");
                if (appointments != null && !appointments.isEmpty()) {
                    for(Object appt : appointments) {
            %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>Pending</td>
                <td><a href="#">Cancel</a></td>
            </tr>
            <% 
                    } 
                } else { 
            %>
            <tr><td colspan="5">No appointments found.</td></tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>