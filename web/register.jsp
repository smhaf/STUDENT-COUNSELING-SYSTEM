<%-- 
    File: register.jsp
    Purpose: Allows new students to sign up.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
    <style>
        body { font-family: sans-serif; background-color: #f4f4f9; padding: 20px; }
        .form-container { max-width: 500px; margin: 0 auto; background: #fff; padding: 20px; border-radius: 8px; }
        input { width: 95%; padding: 10px; margin: 5px 0 15px 0; }
        button { background-color: #2ecc71; color: white; padding: 10px 20px; border: none; cursor: pointer; width: 100%; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Create Student Account</h2>
        
        <form action="RegisterServlet" method="POST">
            
            <label>Student ID</label>
            <input type="text" name="matrixNumber" required placeholder="2025xxxxxx">

            <label>Full Name</label>
            <input type="text" name="studentName" required>

            <label>Email</label>
            <input type="email" name="studentEmail" required>

            <label>Phone Number</label>
            <input type="text" name="studentPhone" required>

            <label>Course Code</label>
            <input type="text" name="course" required placeholder="e.g., CS110">

            <label>Password</label>
            <input type="password" name="studentPassword" required>

            <button type="submit">Register</button>
        </form>
        
        <a href="login.jsp">Back to Login</a>
    </div>
</body>
</html>