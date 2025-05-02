package com.example.jsp_21_1_ex1_memberex.repository;

import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
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
        String query = "INSERT INTO TB_USER (" +
                "ID_USER, " +
                "NM_USER, " +
                "NM_PASWD, " +
                "NM_ENC_PASWD, " +
                "NO_MOBILE, " +
                "NM_EMAIL, " +
                "ST_STATUS, " +
                "CD_USER_TYPE, " +
                "NO_REGISTER, " +
                "DA_FIRST_DATE" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, member.getUserName());
            pstmt.setString(3, member.getPassword());
            pstmt.setString(4, member.getPassword());
            pstmt.setString(5, member.getMobileNo());
            pstmt.setString(6, member.getEmail());
            pstmt.setString(7, member.getStatus());
            pstmt.setString(8, member.getUserType());
            pstmt.setString(9, member.getRegisterNo());
            pstmt.setString(10, member.getFirstDate().toString());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMemberInfo(String userId, String userName, String mobileNo, String email) {
        String query = "UPDATE TB_USER SET " +
                "NM_USER = ?, " +
                "NO_MOBILE = ?, " +
                "NM_EMAIL = ? " +
                "WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, userName);
            pstmt.setString(3, mobileNo);
            pstmt.setString(4, email);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 회원 상태 변경
    public boolean updateMemberStatus(String userId, String status) {
        String query = "UPDATE TB_USER SET ST_STATUS = ? WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, status);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 회원 권한 변경
    public boolean updateMemberType(String userId, String userType) {
        String query = "UPDATE TB_USER SET CD_USER_TYPE = ? WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userType);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public MemberDTO getMemberById(String id) {
        String query = "SELECT * FROM TB_USER WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MemberDTO(
                            rs.getString("ID_USER"),
                            rs.getString("NM_USER"),
                            null,
                            rs.getString("NO_MOBILE"),
                            rs.getString("NM_EMAIL"),
                            rs.getString("ST_STATUS"),
                            rs.getString("CD_USER_TYPE"),
                            rs.getString("NO_REGISTER"),
                            rs.getDate("DA_FIRST_DATE").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 상태별 회원 조회
    public List<MemberDTO> getMembersByStatus(String status) {
        String query = "SELECT * FROM TB_USER WHERE ST_STATUS = ?";
        List<MemberDTO> members = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, status);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    members.add(new MemberDTO(
                            rs.getString("ID_USER"),
                            rs.getString("NM_USER"),
                            null,
                            rs.getString("NO_MOBILE"),
                            rs.getString("NM_EMAIL"),
                            rs.getString("ST_STATUS"),
                            rs.getString("CD_USER_TYPE"),
                            rs.getString("NO_REGISTER"),
                            rs.getDate("DA_FIRST_DATE").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    // 권한별 회원 조회
    public List<MemberDTO> getMembersByUserType(String userType) {
        String query = "SELECT * FROM TB_USER WHERE CD_USER_TYPE = ?";
        List<MemberDTO> members = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userType);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    members.add(new MemberDTO(
                            rs.getString("ID_USER"),
                            rs.getString("NM_USER"),
                            null,
                            rs.getString("NO_MOBILE"),
                            rs.getString("NM_EMAIL"),
                            rs.getString("ST_STATUS"),
                            rs.getString("CD_USER_TYPE"),
                            rs.getString("NO_REGISTER"),
                            rs.getDate("DA_FIRST_DATE").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public boolean deleteMember(String userId) {
        String query = "DELETE FROM TB_USER WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public MemberDTO loginMember(String userId, String password) {
        String query = "SELECT * FROM TB_USER WHERE id = ? AND paswd = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MemberDTO(
                            rs.getString("ID_USER"),
                            rs.getString("NM_USER"),
                            null,
                            rs.getString("NO_MOBILE"),
                            rs.getString("NM_EMAIL"),
                            rs.getString("ST_STATUS"),
                            rs.getString("CD_USER_TYPE"),
                            rs.getString("NO_REGISTER"),
                            rs.getDate("DA_FIRST_DATE").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}