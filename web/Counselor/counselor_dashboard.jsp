<%-- 
    File: counselor_dashboard.jsp
    Purpose: Main management interface for Counselors.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
    // 1. SESSION CHECK (Security)
    if (session.getAttribute("userSession") == null || !session.getAttribute("role").equals("counselor")) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Counselor Dashboard</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f4f9; padding: 20px; }
        .navbar { background: #fff; padding: 15px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); display: flex; justify-content: space-between; }
        table { width: 100%; border-collapse: collapse; background: white; margin-bottom: 30px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #3498db; color: white; }
        .btn { padding: 5px 10px; text-decoration: none; color: white; border-radius: 3px; font-size: 13px; }
        .btn-accept { background: #27ae60; }
        .btn-reject { background: #e74c3c; }
        .btn-view { background: #9b59b6; }
        .status-badge { padding: 4px 8px; border-radius: 12px; font-size: 11px; color: white; }
        .pending { background: #f39c12; }
    </style>
</head>
<body>
    <div class="navbar">
        <strong>Counselor: <%= session.getAttribute("currentUserName") %></strong>
        <a href="LogoutServlet">Logout</a>
    </div>

    <h2>🔔 Pending Appointments</h2>
    <table>
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Issue</th>
                <th>Date & Time</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%-- Data retrieved from Request Attribute set by Controller --%>
            <% 
                List<Object> pending = (List<Object>) request.getAttribute("pendingApps");
                if (pending != null && !pending.isEmpty()) {
                    for(Object app : pending) { 
                        // Implementation of appointment data would go here
                    }
                } else { 
            %>
            <tr><td colspan="4">No pending requests.</td></tr>
            <% } %>
            <%-- Example Static Row for Storyboard functionality --%>
            <tr>
                <td>2025138315</td>
                <td>Exam Stress</td>
                <td>2025-12-16 02:30 PM</td>
                <td>
                    <a href="AppointmentServlet?action=update&id=1&status=Accepted" class="btn btn-accept">Accept</a>
                    <a href="AppointmentServlet?action=update&id=1&status=Rejected" class="btn btn-reject">Reject</a>
                    <a href="CounselorServlet?action=viewStudent&id=2025138315" class="btn btn-view">View Student</a>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>