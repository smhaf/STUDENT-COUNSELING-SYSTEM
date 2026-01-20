<%-- 
    File: edit_profile.jsp
    Purpose: Allow students to update their personal information.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="dao.StudentDAO"%> <%
    // 1. GET SESSION DATA SAFELY
    Object sessionObj = session.getAttribute("userSession");
    Student student = null;

    // Security: Check if session exists at all
    if (sessionObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // 2. SMART CHECK: Is it a Student Object or just a String ID?
    if (sessionObj instanceof Student) {
        // Perfect scenario: Session already has the object
        student = (Student) sessionObj;
    } 
    else if (sessionObj instanceof String) {
        // "String" scenario: Session only has the ID (e.g., "S12345")
        // We must fetch the full details from the Database now.
        String tempID = (String) sessionObj;
        StudentDAO dao = new StudentDAO();
        student = dao.getStudentById(tempID);
    }
    
    // Safety check: If student is still null (DB error), kick them out
    if (student == null) {
        response.sendRedirect("login.jsp?error=SessionError");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <style>
        .form-container { width: 40%; margin: 50px auto; font-family: Arial, sans-serif; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], select { 
            width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; 
        }
        input[readonly] { background-color: #eee; color: #555; cursor: not-allowed; }
        .btn-save { background-color: #2ecc71; color: white; padding: 10px 15px; border: none; cursor: pointer; }
        .btn-cancel { background-color: #e74c3c; color: white; text-decoration: none; padding: 10px 15px; border-radius: 2px; }
        .error-msg { color: red; margin-bottom: 10px; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Edit Your Profile</h2>
    <hr>
    
    <% String error = request.getParameter("error"); %>
    <% if (error != null) { %>
        <div class="error-msg"><%= error %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/UpdateProfileServlet" method="POST">
        
        <%-- Hidden ID is required for the SQL UPDATE command --%>
        <input type="hidden" name="studentID" value="<%= student.getStudentID() %>">

        <div class="form-group">
            <label>Matrix Number</label>
            <input type="text" value="<%= student.getMatrixNumber() %>" readonly>
        </div>

        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="studentName" value="<%= student.getStudentName() %>" required>
        </div>

        <div class="form-group">
            <label>Email Address</label>
            <input type="email" name="studentEmail" value="<%= student.getStudentEmail() %>" required>
        </div>

        <div class="form-group">
            <label>Phone Number</label>
            <input type="text" name="studentPhone" value="<%= (student.getStudentPhone() != null) ? student.getStudentPhone() : "" %>">
        </div>

        <div class="form-group">
            <label>Course</label>
             <input type="text" name="course" value="<%= student.getCourse() %>" required>
        </div>

        <div style="margin-top: 20px;">
            <button type="submit" class="btn-save">Update Profile</button>
            <a href="<%= request.getContextPath() %>/DashboardServlet"  class="btn-cancel">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>