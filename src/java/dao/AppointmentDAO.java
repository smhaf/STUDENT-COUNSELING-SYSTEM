package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;
import util.DBConnection;

public class AppointmentDAO {

    // 1. CREATE APPOINTMENT (Student books it)
    public boolean addAppointment(Appointment app) {
        String sql = "INSERT INTO Appointment (appointmentIssue, appointmentDate, appointmentTime, Status, studentID) VALUES (?, ?, ?, 'Pending', ?)";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, app.getAppointmentIssue());
            ps.setString(2, app.getAppointmentDate());
            ps.setString(3, app.getAppointmentTime());
            ps.setString(4, app.getStudentID());
            
            int i = ps.executeUpdate();
            return i > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. READ APPOINTMENTS BY STUDENT (For Student Dashboard)
    public List<Appointment> getAppointmentsByStudent(String studentID) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE studentID = ?";
        System.out.println(studentID);
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Appointment app = new Appointment();
                app.setAppointmentID(rs.getInt("appointmentID"));
                app.setAppointmentIssue(rs.getString("appointmentIssue"));
                app.setAppointmentDate(rs.getString("appointmentDate"));
                app.setAppointmentTime(rs.getString("appointmentTime"));
                app.setStatus(rs.getString("Status"));
                app.setStudentID(rs.getString("studentID"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. READ ALL PENDING APPOINTMENTS (For Counselor Dashboard)
    public List<Appointment> getPendingAppointments() {
        List<Appointment> list = new ArrayList<>();
        // SQL JOIN to get student details along with the appointment
        String sql = "SELECT a.*, s.STUDENTNAME, s.MATRIXNUMBER " +
                     "FROM APPOINTMENT a " +
                     "JOIN STUDENT s ON a.STUDENTID = s.STUDENTID " +
                     "WHERE a.STATUS = 'Pending'";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment app = new Appointment();
                app.setAppointmentID(rs.getInt("APPOINTMENTID"));
                app.setAppointmentIssue(rs.getString("APPOINTMENTISSUE"));
                app.setAppointmentDate(rs.getString("APPOINTMENTDATE"));
                app.setAppointmentTime(rs.getString("APPOINTMENTTIME"));
                app.setStatus(rs.getString("STATUS"));
                app.setStudentID(rs.getString("STUDENTID"));

                // Set the new joined data
                app.setStudentName(rs.getString("STUDENTNAME"));
                app.setMatrixNumber(rs.getString("MATRIXNUMBER"));

                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 4. UPDATE APPOINTMENT STATUS (Counselor Accepts/Rejects)
    public boolean updateStatus(int appointmentID, String newStatus, String counselorID) {
        String sql = "UPDATE Appointment SET Status = ?, counselorID = ? WHERE appointmentID = ?";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newStatus);
            ps.setString(2, counselorID);
            ps.setInt(3, appointmentID);
            
            int i = ps.executeUpdate();
            return i > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 5. CANCEL APPOINTMENT (Student)
    public boolean cancelAppointment(int appointmentID) {
        String sql = "UPDATE Appointment SET Status = 'Cancel' WHERE appointmentID = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}