package com.example.jsp_21_1_ex1_memberex.repository;

import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;

import org.mindrot.jbcrypt.BCrypt;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
    // 비밀번호 암호화
    String plainPassword = member.getPassword();
    String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    
    // 암호화된 비밀번호 설정
    member.setEncPassword(hashedPassword);
    
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
        pstmt.setString(3, plainPassword);    // 일반 텍스트 비밀번호 (보안상 권장하지 않음)
        pstmt.setString(4, hashedPassword);   // 암호화된 비밀번호
        pstmt.setString(5, member.getMobileNo());
        pstmt.setString(6, member.getEmail());
        pstmt.setString(7, member.getStatus());
        pstmt.setString(8, member.getUserType());
        pstmt.setString(9, member.getRegisterNo());

        int result = pstmt.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public MemberDTO loginMember(String userId, String password) {
        String query = "SELECT * FROM TB_USER WHERE ID_USER = ? AND NM_PASWD = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMemberFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMemberInfo(String userId, String userName, String password, String mobileNo) {
        String query = "UPDATE TB_USER SET " +
                "NM_USER = ?, " +
                "NM_PASWD = ?, " +
                "NM_ENC_PASWD = ?, " +
                "NO_MOBILE = ? " +
                "WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, password); // 암호화 처리는 생략
            pstmt.setString(4, mobileNo);
            pstmt.setString(5, userId);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 비밀번호 확인
    public boolean validatePassword(String userId, String password) {
        String query = "SELECT COUNT(*) FROM TB_USER WHERE ID_USER = ? AND NM_PASWD = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                    return rs.getInt(1) > 0;
            }
            }
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
                    return extractMemberFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 전체 회원 조회
    public List<MemberDTO> getAllMembers() {
        String query = "SELECT * FROM TB_USER ORDER BY DA_FIRST_DATE DESC";
        List<MemberDTO> members = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                members.add(extractMemberFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


    public boolean deleteMember(String userId) {
        String query = "DELETE FROM TB_USER WHERE ID_USER = ? AND ST_STATUS = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, MemberDTO.STATUS_DELETED);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ResultSet에서 MemberDTO 객체 추출 유틸리티 메서드
    private MemberDTO extractMemberFromResultSet(ResultSet rs) throws SQLException {
        MemberDTO member = new MemberDTO();
        member.setUserId(rs.getString("ID_USER"));
        member.setUserName(rs.getString("NM_USER"));
        member.setPassword(null); // 보안상 비밀번호는 설정하지 않음
        member.setEncPassword(null);
        member.setMobileNo(rs.getString("NO_MOBILE"));
        member.setEmail(rs.getString("NM_EMAIL"));
        member.setStatus(rs.getString("ST_STATUS"));
        member.setUserType(rs.getString("CD_USER_TYPE"));
        member.setRegisterNo(rs.getString("NO_REGISTER"));

        Timestamp ts = rs.getTimestamp("DA_FIRST_DATE");
        if (ts != null) {
            member.setFirstDate(ts);
        }

        return member;
    }
    
    public int countAllMembers() {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TB_USER");
         ResultSet rs = pstmt.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(1);
        }
        
        return 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
    
public int countMembersByStatus(String status) {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TB_USER WHERE ST_STATUS = ?")) {
        
        pstmt.setString(1, status);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            
            return 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
    
    public List<MemberDTO> getMembersByStatus(String status) {
    List<MemberDTO> members = new ArrayList<>();
    
    try (Connection conn = dataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TB_USER WHERE ST_STATUS = ? ORDER BY DA_FIRST_DATE DESC")) {
        
        pstmt.setString(1, status);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MemberDTO member = extractMemberFromResultSet(rs);
                
                members.add(member);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return members;
}
    
    public List<MemberDTO> getMembers(int currentPage, int recordsPerPage, String searchType, String searchKeyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberDTO> members = new ArrayList<>();
        
        try {
            conn = dataSource.getConnection();
            
            // Oracle용 페이징 처리 쿼리 (ROWNUM 사용)
            StringBuilder sql = new StringBuilder(
                "SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (" +
                "SELECT * FROM TB_USER");
            
            // 검색 조건이 있는 경우 WHERE 절 추가
            if (searchType != null && !searchType.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {
                sql.append(" WHERE ").append(searchType).append(" LIKE ?");
            }
            
            // 중첩 쿼리 구조 완성
            sql.append(" ORDER BY DA_FIRST_DATE DESC) a WHERE ROWNUM <= ?) " +
                       "WHERE rnum > ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            
            int parameterIndex = 1;
            
            // 검색 조건이 있는 경우 파라미터 설정
            if (searchType != null && !searchType.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {
                pstmt.setString(parameterIndex++, "%" + searchKeyword + "%");
            }
            
            // 페이징 파라미터 설정
            int endRow = currentPage * recordsPerPage;
            int startRow = (currentPage - 1) * recordsPerPage;
            pstmt.setInt(parameterIndex++, endRow);    // ROWNUM <= ?
            pstmt.setInt(parameterIndex, startRow);    // rnum > ?
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setUserId(rs.getString("ID_USER"));
                member.setUserName(rs.getString("NM_USER"));
                member.setPassword(rs.getString("NM_ENC_PASWD"));
                member.setMobileNo(rs.getString("NO_MOBILE"));
                member.setUserType(rs.getString("CD_USER_TYPE"));
                member.setStatus(rs.getString("ST_STATUS"));
                member.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                
                members.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return members;
    }
    
    public int countMembers(String searchType, String searchKeyword) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM TB_USER");

        // 검색 조건이 있는 경우 WHERE 절 추가
        if (searchType != null && !searchType.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append(" WHERE ").append(searchType).append(" LIKE ?");
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            // 검색 조건이 있는 경우 파라미터 설정
            if (searchType != null && !searchType.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {
                pstmt.setString(1, "%" + searchKeyword + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }

                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}