package model;

import java.io.Serializable;

public class Counselor implements Serializable {

    private String counselorID;      // PK
    private String counselorName;
    private String email;
    private String counselorPassword;

    public Counselor() {
    }

    public String getCounselorID() { return counselorID; }
    public void setCounselorID(String counselorID) { this.counselorID = counselorID; }

    public String getCounselorName() { return counselorName; }
    public void setCounselorName(String counselorName) { this.counselorName = counselorName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCounselorPassword() { return counselorPassword; }
    public void setCounselorPassword(String counselorPassword) { this.counselorPassword = counselorPassword; }
}