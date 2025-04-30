package com.example.jsp_19_memberex_ysh.dao;

import com.example.jsp_19_memberex_ysh.dto.MemberDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    private DataSource dataSource;

    public MemberDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertMember(MemberDTO member) {
        String query = "INSERT INTO member (id, paswd, username, email, mobile, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPaswd());
            pstmt.setString(3, member.getUsername());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getMobile());
            pstmt.setString(6, member.getGender());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMember(MemberDTO member) {
        String query = "UPDATE member SET username = ?, email = ?, mobile = ?, gender = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getUsername());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getMobile());
            pstmt.setString(4, member.getGender());
            pstmt.setString(5, member.getId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public MemberDTO loginMember(String id, String paswd) {
        String query = "SELECT * FROM member WHERE id = ? AND paswd = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            pstmt.setString(2, paswd);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MemberDTO(
                            rs.getString("id"),
                            null,
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("mobile"),
                            rs.getString("gender")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}