<%-- 
    File: make_appointment.jsp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Security Check
    if (session.getAttribute("userSession") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
</head>
<body>
    <h2>Book a Counseling Session</h2>
    
    <form action="<%= request.getContextPath() %>/AppointmentServlet" method="POST">
        <input type="hidden" name="studentID" value="<%= session.getAttribute("userSession") %>">
        <script>
            console.log('user',<%= session.getAttribute("userSession") %>);
        </script>
        
        <label>Date:s</label>
        <input type="date" name="appointmentDate" required><br><br>

        <label>Time:</label>
        <input type="time" name="appointmentTime" required><br><br>

        <label>Issue/Reason:</label><br>
        <textarea name="appointmentIssue" rows="4" cols="50" required></textarea><br><br>

        <button type="submit">Submit Request</button>
    </form>
    
    <br>
    <a href="<%= request.getContextPath() %>/DashboardServlet">Cancel</a>
</body>
</html>