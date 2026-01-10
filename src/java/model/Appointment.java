package model;

import java.io.Serializable;

public class Appointment implements Serializable {

    private int appointmentID;          // PK (int is common for auto-increment IDs)
    private String appointmentIssue;
    private String appointmentDate;     // Stored as YYYY-MM-DD
    private String appointmentTime;     // Stored as HH:MM
    private String status;              // 'Pending', 'Accepted', 'Completed'
    private String studentID;           // FK
    private String counselorID;         // FK

    public Appointment() {
    }

    // Getters and Setters
    public int getAppointmentID() { return appointmentID; }
    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }

    public String getAppointmentIssue() { return appointmentIssue; }
    public void setAppointmentIssue(String appointmentIssue) { this.appointmentIssue = appointmentIssue; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getCounselorID() { return counselorID; }
    public void setCounselorID(String counselorID) { this.counselorID = counselorID; }
}