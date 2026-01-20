package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Student;
import util.DBConnection; // Import the connection class we made

public class StudentDAO {

    // 1. REGISTER STUDENT
    public boolean registerStudent(Student student) {
        String sql = "INSERT INTO Student (matrixNumber, studentName, studentEmail, studentPhone, course, studentPassword) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, student.getMatrixNumber());
            ps.setString(2, student.getStudentName());
            ps.setString(3, student.getStudentEmail());
            ps.setString(4, student.getStudentPhone());
            ps.setString(5, student.getCourse());
            ps.setString(6, student.getStudentPassword());
            
            int i = ps.executeUpdate();
            return i > 0; // Returns true if insert was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. AUTHENTICATE STUDENT (LOGIN)
    public Student authenticateUser(String email, String password) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE STUDENTEMAIL = ? AND studentPassword = ?";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                student = new Student();
                student.setStudentID(rs.getString("studentID"));
                student.setStudentName(rs.getString("studentName"));
                student.setStudentEmail(rs.getString("studentEmail"));
                student.setCourse(rs.getString("course"));
                // We don't usually load the password back into the object for security
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student; // Returns null if login failed, or the Student object if successful
    }
    // 3. RETREIVE STUDENT INFORMATION(STUDENT DETAIL ON COUNCELOR)
    public Student getStudentById(String studentID) {
        Student student = null;
        String sql = "SELECT * FROM STUDENT WHERE STUDENTID = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setStudentID(rs.getString("STUDENTID"));
                student.setStudentName(rs.getString("STUDENTNAME"));
                student.setStudentEmail(rs.getString("STUDENTEMAIL"));
                student.setCourse(rs.getString("COURSE"));
                student.setStudentPhone(rs.getString("STUDENTPHONE"));
                student.setMatrixNumber(rs.getString("MATRIXNUMBER"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
    
    //4.  UPDATE STUDENT INFORMATION
    public boolean updateStudentProfile(Student student) {
    String sql = "UPDATE Student SET studentName=?, studentEmail=?, studentPhone=?, course=? WHERE studentID=?";
    
    try (Connection con = DBConnection.createConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, student.getStudentName());
        ps.setString(2, student.getStudentEmail());
        ps.setString(3, student.getStudentPhone());
        ps.setString(4, student.getCourse());
        ps.setString(5, student.getStudentID());
        
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
   