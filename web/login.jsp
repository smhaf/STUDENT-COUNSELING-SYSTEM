<%-- 
    File: login.jsp
    Purpose: Entry point for Students and Counselors to log in.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login - Counseling System</title>
    <style>
        /* (Copy your CSS from login.html here) */
        body { font-family: sans-serif; padding: 20px; background-color: #f4f4f9; }
        .container { max-width: 400px; margin: auto; background: white; padding: 20px; border-radius: 8px; }
        input { width: 100%; padding: 10px; margin: 10px 0; }
        button { width: 100%; padding: 10px; background-color: #3498db; color: white; border: none; }
        .error { color: red; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <h2>System Login</h2>
        
        <% 
            String msg = (String) request.getAttribute("errorMsg");
            if (msg != null) { 
        %>
            <p class="error"><%= msg %></p>
        <% } %>

        <form action="LoginServlet" method="POST">
            <label>User ID (Student ID or Counselor ID)</label>
            <input type="text" name="userid" style="width:100%; padding: 10px;" required placeholder="e.g., 202412345">
            
            <label>Password</label>
            <input type="password" name="password" style="width:100%; padding: 10px;" required>
            
            <label>Role</label>
            <select name="role" style="width:100%; padding: 10px; margin-bottom: 10px;">
                <option value="student">Student</option>
                <option value="counselor">Counselor</option>
            </select>

            <button type="submit">Login</button>
        </form>
        
        <p>New Student? <a href="register.jsp">Register Here</a></p>
    </div>
</body>
</html>