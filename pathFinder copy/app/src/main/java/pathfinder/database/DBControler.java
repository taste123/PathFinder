package pathfinder.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pathfinder.database.DBConfig;
import pathfinder.models.User;
import pathfinder.models.data_pdf;

public class DBControler {
    public void insertUser(String username, String password, String name, String email) {
        String sql = "INSERT INTO user(username, password, name, email) VALUES(?, ?, ?, ?)";
        try (Connection conn = pathfinder.database.DBConfig.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDataPDF(String fullname, String jobTitle, String profileDesc, int phoneNum, String address,
            String schoolName, String schoolLoc, String degrees, String field, int gradStart, int gradEnd,
            String workplace1, String pos1, int year1, String workplace2, String pos2, int year2, String skill1,
            String skill2, String skill3, String skill4, String username) {
        String sql = "INSERT INTO data_pdf(fullname, jobTitle, profileDesc, phoneNum, address, schoolName, schoolLoc, degrees, field, gradStart, gradEnd, workplace1, pos1, year1, workplace2, pos2, year2, skill1, skill2, skill3, skill4, username) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = pathfinder.database.DBConfig.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullname);
            pstmt.setString(2, jobTitle);
            pstmt.setString(3, profileDesc);
            pstmt.setInt(4, phoneNum);
            pstmt.setString(5, address);
            pstmt.setString(6, schoolName);
            pstmt.setString(7, schoolLoc);
            pstmt.setString(8, degrees);
            pstmt.setString(9, field);
            pstmt.setInt(10, gradStart);
            pstmt.setInt(11, gradEnd);
            pstmt.setString(12, workplace1);
            pstmt.setString(13, pos1);
            pstmt.setInt(14, year1);
            pstmt.setString(15, workplace2);
            pstmt.setString(16, pos2);
            pstmt.setInt(17, year2);
            pstmt.setString(18, skill1);
            pstmt.setString(19, skill2);
            pstmt.setString(20, skill3);
            pstmt.setString(21, skill4);
            pstmt.setString(22, username);
            pstmt.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT username, password, name, email FROM user WHERE username = ?";
        User user = null;
        try (Connection conn = pathfinder.database.DBConfig.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public ObservableList<data_pdf> getDataPdfByUsername(String username) {
        System.out.println("kk");
        String sql = "SELECT * FROM data_pdf WHERE username = ?";
        ObservableList<data_pdf> dataPdfList = FXCollections.observableArrayList();
        try (Connection conn = pathfinder.database.DBConfig.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                data_pdf dataPdf = new data_pdf();
                dataPdf.setFullname(rs.getString("fullname"));
                dataPdf.setJobTitle(rs.getString("jobTitle"));
                dataPdf.setProfileDesc(rs.getString("profileDesc"));
                dataPdf.setPhoneNum(rs.getInt("phoneNum"));
                dataPdf.setAddress(rs.getString("address"));
                dataPdf.setSchoolName(rs.getString("schoolName"));
                dataPdf.setSchoolLoc(rs.getString("schoolLoc"));
                dataPdf.setDegrees(rs.getString("degrees"));
                dataPdf.setField(rs.getString("field"));
                dataPdf.setGradStart(rs.getInt("gradStart"));
                dataPdf.setGradEnd(rs.getInt("gradEnd"));
                dataPdf.setWorkplace1(rs.getString("workplace1"));
                dataPdf.setPos1(rs.getString("pos1"));
                dataPdf.setYear1(rs.getInt("year1"));
                dataPdf.setWorkplace2(rs.getString("workplace2"));
                dataPdf.setPos2(rs.getString("pos2"));
                dataPdf.setYear2(rs.getInt("year2"));
                dataPdf.setSkill1(rs.getString("skill1"));
                dataPdf.setSkill2(rs.getString("skill2"));
                dataPdf.setSkill3(rs.getString("skill3"));
                dataPdf.setSkill4(rs.getString("skill4"));
                dataPdf.setId(rs.getInt("id"));
                dataPdfList.add(dataPdf);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataPdfList;
    }
}
