package kr.co.tlf.ex.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.co.tlf.ex.dto.MBDto;

public class MBDao {
    private DataSource dataSource;

    public MBDao() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MBDto getContentView(String nbMvcBoard) {
        MBDto dto = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM TB_BOARD ORDER BY NB_BOARD DESC GROUP BY ";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nbMvcBoard);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new MBDto();
                dto.setNbMvcBoard(rs.getInt("NB_MVC_BOARD"));
                dto.setNmName(rs.getString("NM_NAME"));
                dto.setNmTitle(rs.getString("NM_TITLE"));
                dto.setNmContent(rs.getString("NM_CONTENT"));
                dto.setDaWrite(rs.getTimestamp("DA_WRITE"));
                dto.setCnHit(rs.getInt("CN_HIT"));
                dto.setNbGroup(rs.getInt("NB_GROUP"));
                dto.setNbStep(rs.getInt("NB_STEP"));
                dto.setNbIndent(rs.getInt("NB_INDENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }

        return dto;
    }

    public List<MBDto> getAllPosts() {
        List<MBDto> boardList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM TB_BOARD ORDER BY NB_BOARD DESC";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MBDto dto = new MBDto();
                dto.setNbMvcBoard(rs.getInt("NB_BOARD"));
                dto.setNmName(rs.getString("NM_WRITER"));
                dto.setNmTitle(rs.getString("NM_TITLE"));
                dto.setNmContent(rs.getString("NM_CONTENT"));
                dto.setDaWrite(rs.getTimestamp("DA_WRITE"));
                dto.setCnHit(rs.getInt("CN_HIT"));
                dto.setNbGroup(rs.getInt("NB_GROUP"));
                dto.setNbStep(rs.getInt("NB_STEP"));
                dto.setNbIndent(rs.getInt("NB_INDENT"));

                boardList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }

        return boardList;
    }

    public int writePost (MBDto dto) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO TB_BOARD VALUES (SEQ_TB_BOARD.NEXTVAL, ?, ?, ?, SYSDATE, 0, SEQ_TB_BOARD.NEXTVAL, 0, 0)";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getNmName());
            pstmt.setString(2, dto.getNmTitle());
            pstmt.setString(3, dto.getNmContent());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }

        return result;
    }
}
