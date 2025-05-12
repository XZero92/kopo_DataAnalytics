package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao;

import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

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

public class UserDAO {
    private DataSource dataSource;

    public UserDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public boolean insertMember(UserDTO user) {
    // 비밀번호 암호화
    String plainPassword = user.getPassword();
    String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    
    // 암호화된 비밀번호 설정
    user.setEncPassword(hashedPassword);
    
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

        pstmt.setString(1, user.getUserId());
        pstmt.setString(2, user.getUserName());
        pstmt.setString(3, plainPassword);    // 일반 텍스트 비밀번호 (보안상 권장하지 않음)
        pstmt.setString(4, hashedPassword);   // 암호화된 비밀번호
        pstmt.setString(5, user.getMobileNo());
        pstmt.setString(6, user.getEmail());
        pstmt.setString(7, user.getStatus());
        pstmt.setString(8, user.getUserType());
        pstmt.setString(9, user.getRegisterNo());

        int result = pstmt.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public UserDTO loginMember(String userId, String password) {
        String query = "SELECT * FROM TB_USER WHERE ID_USER = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 암호화 방식: BCrypt 검증 사용
                    /*String storedHash = rs.getString("NM_ENC_PASWD");
                    if (BCrypt.checkpw(password, storedHash)) {
                        return extractMemberFromResultSet(rs);
                    }*/
                    // 평문 방식: 단순 문자열 비교
                    String storedPlainPw = rs.getString("NM_PASWD");
                    if (password.equals(storedPlainPw)) {
                        return extractMemberFromResultSet(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMemberInfo(String userId, String userName, String password, String mobileNo) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

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
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, mobileNo);
            pstmt.setString(5, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 비밀번호 확인
    public boolean validatePassword(String userId, String password) {
        String query = "SELECT NM_PASWD, NM_ENC_PASWD FROM TB_USER WHERE ID_USER = ?\n";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    /*// 암호화 방식 검증
                    String storedHash = rs.getString("NM_ENC_PASWD");
                    return BCrypt.checkpw(password, storedHash);*/

                    // 평문 방식 검증
                    String storedPlainPw = rs.getString("NM_PASWD");
                    return password.equals(storedPlainPw);
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

    public UserDTO getMemberById(String id) {
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
    public List<UserDTO> getAllMembers() {
        String query = "SELECT * FROM TB_USER ORDER BY DA_FIRST_DATE DESC";
        List<UserDTO> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                users.add(extractMemberFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public boolean deleteMember(String userId) {
        String query = "DELETE FROM TB_USER WHERE ID_USER = ? AND ST_STATUS = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, UserDTO.STATUS_DELETED);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ResultSet에서 MemberDTO 객체 추출 유틸리티 메서드
    private UserDTO extractMemberFromResultSet(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(rs.getString("ID_USER"));
        user.setUserName(rs.getString("NM_USER"));
        user.setPassword(null); // 보안상 비밀번호는 설정하지 않음
        user.setEncPassword(null);
        user.setMobileNo(rs.getString("NO_MOBILE"));
        user.setEmail(rs.getString("NM_EMAIL"));
        user.setStatus(rs.getString("ST_STATUS"));
        user.setUserType(rs.getString("CD_USER_TYPE"));
        user.setRegisterNo(rs.getString("NO_REGISTER"));

        Timestamp ts = rs.getTimestamp("DA_FIRST_DATE");
        if (ts != null) {
            user.setFirstDate(ts);
        }

        return user;
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
    
    public List<UserDTO> getMembersByStatus(String status) {
    List<UserDTO> users = new ArrayList<>();
    
    try (Connection conn = dataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TB_USER WHERE ST_STATUS = ? ORDER BY DA_FIRST_DATE DESC")) {
        
        pstmt.setString(1, status);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserDTO user = extractMemberFromResultSet(rs);
                
                users.add(user);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return users;
}
    
    public List<UserDTO> getMembers(int currentPage, int recordsPerPage, String searchType, String searchKeyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserDTO> users = new ArrayList<>();
        
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
                UserDTO user = new UserDTO();
                user.setUserId(rs.getString("ID_USER"));
                user.setUserName(rs.getString("NM_USER"));
                user.setPassword(rs.getString("NM_ENC_PASWD"));
                user.setMobileNo(rs.getString("NO_MOBILE"));
                user.setUserType(rs.getString("CD_USER_TYPE"));
                user.setStatus(rs.getString("ST_STATUS"));
                user.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return users;
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