<%-- 
    File: view_student.jsp
    Purpose: Display specific student profile and history to Counselor.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Security Check
    if (session.getAttribute("userSession") == null || !session.getAttribute("role").equals("counselor")) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Details</title>
    <style>
        .profile-card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 800px; margin: auto; }
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 20px; }
        .info-item { border-left: 4px solid #3498db; padding-left: 10px; background: #f9f9f9; }
    </style>
</head>
<body>
    <div class="profile-card">
        <a href="counselor_dashboard.jsp">← Back to Dashboard</a>
        <hr>
        <%-- Data would be populated from a StudentBean passed by the Controller --%>
        <h2>Student Profile: Alex Smith</h2>
        
        <div class="info-grid">
            <div class="info-item">
                <strong>Student ID:</strong><br>
                <span>2025112233</span>
            </div>
            <div class="info-item">
                <strong>Email:</strong><br>
                <span>alex.smith@student.edu</span>
            </div>
            <div class="info-item">
                <strong>Course:</strong><br>
                <span>CS240</span>
            </div>
            <div class="info-item">
                <strong>Phone:</strong><br>
                <span>+60123456789</span>
            </div>
        </div>

        <h3>Appointment History</h3>
        <table border="1" width="100%" style="border-collapse: collapse;">
            <tr style="background: #9b59b6; color: white;">
                <th>Date</th>
                <th>Issue</th>
                <th>Status</th>
            </tr>
            <tr>
                <td>2025-11-05</td>
                <td>Initial stress consultation</td>
                <td>Completed</td>
            </tr>
        </table>
    </div>
</body>
</html>