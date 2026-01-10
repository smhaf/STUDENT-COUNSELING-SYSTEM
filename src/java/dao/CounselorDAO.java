package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Counselor;
import util.DBConnection;

public class CounselorDAO {

    public Counselor authenticateCounselor(String counselorID, String password) {
        Counselor counselor = null;
        String sql = "SELECT * FROM Counselor WHERE counselorID = ? AND counselorPassword = ?";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, counselorID);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                counselor = new Counselor();
                counselor.setCounselorID(rs.getString("counselorID"));
                counselor.setCounselorName(rs.getString("counselorName"));
                counselor.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counselor;
    }
}