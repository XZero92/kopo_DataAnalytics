package kr.co.tlf.ex.dao;

import kr.co.tlf.ex.dto.MBDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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

    /**
     * 특정 게시글의 상세 정보를 조회합니다. (CLOB 데이터 포함)
     * 조회수는 증가시키지 않고 내용만 가져옵니다.
     * 
     * @param nbBoard 조회할 게시글 번호
     * @return 조회된 게시글 정보를 담은 MBDto 객체, 없으면 null
     */
    public MBDto getContent(String nbBoard) {
        MBDto dto = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT NB_BOARD, NM_TITLE, NM_CONTENT, CB_CONTENT, NM_WRITER, " +
                      "DA_WRITE, DA_FIRST_DATE, CN_HIT, NB_GROUP, NB_STEP, NB_INDENT, ID_FILE " +
                      "FROM TB_BOARD WHERE NB_BOARD = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nbBoard);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new MBDto();
                dto.setNbBoard(rs.getInt("NB_BOARD"));
                dto.setNmTitle(rs.getString("NM_TITLE"));
                dto.setNmContent(rs.getString("NM_CONTENT"));
                
                // CLOB 데이터 처리
                try {
                    // Oracle JDBC 드라이버는 rs.getString("CB_CONTENT")로 CLOB을 바로 읽을 수 있습니다
                    String clobContent = rs.getString("CB_CONTENT");
                    dto.setCbContent(clobContent);
                } catch (SQLException e) {
                    // CLOB 읽기 실패 시 예외 처리
                    System.err.println("CLOB 읽기 실패: " + e.getMessage());
                    // CLOB 데이터가 없으면 일반 컨텐츠를 사용
                    dto.setCbContent(rs.getString("NM_CONTENT"));
                }
                
                dto.setNmWriter(rs.getString("NM_WRITER"));
                dto.setDaWrite(rs.getTimestamp("DA_WRITE"));

                // 최초 작성일 처리
                java.sql.Timestamp firstDate = rs.getTimestamp("DA_FIRST_DATE");
                dto.setDaFirstDate(firstDate);

                dto.setCnHit(rs.getInt("CN_HIT"));
                dto.setNbGroup(rs.getInt("NB_GROUP"));
                dto.setNbStep(rs.getInt("NB_STEP"));
                dto.setNbIndent(rs.getInt("NB_INDENT"));
                dto.setIdFile(rs.getString("ID_FILE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }

        return dto;
    }

    /**
     * 새 게시글을 데이터베이스에 삽입합니다. (CLOB 데이터 포함)
     * 
     * @param dto 저장할 게시글 정보를 담은 MBDto 객체
     * @return 삽입 성공 시 1, 실패 시 0
     */
    public int insertPost(MBDto dto) {
        Connection conn = null;
        PreparedStatement pstmtSeq = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rsSeq = null;
        int generatedNbBoard = 0;
        int result = 0;

        String sqlSeq = "SELECT seq_tb_board.NEXTVAL FROM DUAL";
        String sqlInsert = "INSERT INTO TB_BOARD " +
            "(NB_BOARD, NM_TITLE, NM_CONTENT, CB_CONTENT, NM_WRITER, " +
            "NB_GROUP, NB_STEP, NB_INDENT, ID_FILE, DA_WRITE, CN_HIT, DA_FIRST_DATE) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, 0, SYSDATE)";

        try {
            conn = getConnection();
            if (conn == null) {
                System.err.println("DB Connection is null in insertBoard.");
                return 0;
            }
            conn.setAutoCommit(false); // 트랜잭션 시작

        // 1. 시퀀스로부터 NB_BOARD 값 가져오기
        pstmtSeq = conn.prepareStatement(sqlSeq);
        rsSeq = pstmtSeq.executeQuery();
        if (rsSeq.next()) {
            generatedNbBoard = rsSeq.getInt(1);
        } else {
            throw new SQLException("Failed to get sequence value.");
        }

        // 2. DTO에 NB_BOARD 및 관련 값 설정
        dto.setNbBoard(generatedNbBoard);
        dto.setNbGroup(generatedNbBoard); // 새 글이므로 NB_GROUP은 NB_BOARD와 동일
        dto.setNbStep(0);                 // 새 글이므로 NB_STEP은 0
        dto.setNbIndent(0);               // 새 글이므로 NB_INDENT는 0

        // 3. 게시글 삽입 (CLOB 포함)
        pstmtInsert = conn.prepareStatement(sqlInsert);
        pstmtInsert.setInt(1, dto.getNbBoard());
        pstmtInsert.setString(2, dto.getNmTitle());
        pstmtInsert.setString(3, dto.getNmContent());
        
        // CLOB 데이터 설정 - 없으면 일반 내용으로 대체
        String clobContent = dto.getCbContent();
        if (clobContent == null || clobContent.isEmpty()) {
            clobContent = dto.getNmContent(); // 기본적으로 일반 컨텐츠와 동일하게 설정
        }
        pstmtInsert.setString(4, clobContent);
        
        pstmtInsert.setString(5, dto.getNmWriter());
        pstmtInsert.setInt(6, dto.getNbGroup());
        pstmtInsert.setInt(7, dto.getNbStep());
        pstmtInsert.setInt(8, dto.getNbIndent());
        pstmtInsert.setString(9, dto.getIdFile()); // 향후 파일 첨부 시 사용, 현재는 null

        result = pstmtInsert.executeUpdate();

        conn.commit(); // 트랜잭션 커밋

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // 오류 발생 시 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            result = 0; // 실패 시
        } finally {
            try {
                if (rsSeq != null) rsSeq.close();
                if (pstmtSeq != null) pstmtSeq.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // 원래 자동 커밋 상태로 복구
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result; // 성공 시 1 (삽입된 행 수), 실패 시 0
    }


    // 전체 게시글 수 조회
    public int getTotalCount() {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) FROM TB_BOARD"; // 실제 테이블명 확인

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return count;
    }

    // 페이징 처리된 게시글 목록 조회 (답글 정렬 포함)
    public List<MBDto> getBoardList(int startRow, int endRow) {
        List<MBDto> boardList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Oracle 페이징 쿼리 (NB_GROUP DESC, NB_STEP ASC 정렬)
        // 실제 테이블 및 컬럼명 확인 필요
        String query = "SELECT * FROM (" +
                "    SELECT ROWNUM AS rnum, TB.* FROM (" +
                "        SELECT NB_BOARD, NM_TITLE, NM_WRITER, DA_WRITE, CN_HIT, NB_GROUP, NB_STEP, NB_INDENT " +
                "        FROM TB_BOARD " +
                "        ORDER BY NB_GROUP DESC, NB_STEP ASC" +
                "    ) TB WHERE ROWNUM <= ?" +
                ") WHERE rnum >= ?";


        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, endRow);
            pstmt.setInt(2, startRow);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MBDto dto = new MBDto();
                dto.setNbBoard(rs.getInt("NB_BOARD"));
                dto.setNmTitle(rs.getString("NM_TITLE"));
                dto.setNmWriter(rs.getString("NM_WRITER"));
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

    /**
     * 게시글 조회 시 조회수를 증가시키는 메소드
     *
     * @param nbBoard 조회수를 증가시킬 게시글 번호
     * @return 성공 시 1, 실패 시 0
     */
    public int updateHit(String nbBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "UPDATE TB_BOARD SET CN_HIT = CN_HIT + 1 WHERE NB_BOARD = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nbBoard);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }

        return result;
    }
    // 1. 같은 그룹 내 현재 step보다 큰 글들의 step 증가
    public void updateStep(int nbGroup, int nbStep) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE TB_BOARD SET NB_STEP = NB_STEP + 1 "
                     + "WHERE NB_GROUP = ? AND NB_STEP > ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, nbGroup);
            pstmt.setInt(2, nbStep);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    /**
     * 특정 게시글의 제목과 내용을 수정합니다. (CLOB 데이터 포함)
     * 게시글 수정 시 작성일(DA_WRITE)도 현재 시간으로 업데이트됩니다.
     *
     * @param nbBoard 수정할 게시글 번호
     * @param title 수정할 제목
     * @param content 수정할 내용
     * @return 수정 성공 시 1, 실패 시 0
     */
    public int modifyPost(String nbBoard, String title, String content) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        // 제목, 내용(일반 컨텐츠 및 CLOB), 작성일 업데이트 (최초작성일은 변경하지 않음)
        String query = "UPDATE TB_BOARD SET NM_TITLE = ?, NM_CONTENT = ?, CB_CONTENT = ?, DA_WRITE = SYSDATE "
            + "WHERE NB_BOARD = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, content); // CLOB 필드도 동일한 내용으로 업데이트
            pstmt.setString(4, nbBoard);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }

        return result;
    }

    /**
     * 답글을 삽입합니다. (CLOB 데이터 포함)
     */
    public int insertReply(MBDto dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "INSERT INTO TB_BOARD "
                 + "(NB_BOARD, NM_TITLE, NM_CONTENT, CB_CONTENT, NM_WRITER, DA_WRITE, CN_HIT, "
                 + "NB_GROUP, NB_STEP, NB_INDENT, ID_FILE, DA_FIRST_DATE) "
                 + "VALUES (SEQ_TB_BOARD.NEXTVAL, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?, ?, SYSDATE)";

        try {
            conn = getConnection();

            // 1. 같은 그룹 내 기존 답글들의 STEP 증가
            updateStep(dto.getNbGroup(), dto.getNbStep());

            // 2. 새 답글 삽입
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getNmTitle());
            pstmt.setString(2, dto.getNmContent());
        
            // CLOB 데이터 설정
            String clobContent = dto.getCbContent();
            if (clobContent == null || clobContent.isEmpty()) {
                clobContent = dto.getNmContent();
            }
            pstmt.setString(3, clobContent);
        
            pstmt.setString(4, dto.getNmWriter());
            pstmt.setInt(5, dto.getNbGroup());     // 원글과 동일한 그룹
            pstmt.setInt(6, dto.getNbStep() + 1);  // 부모글의 step + 1
            pstmt.setInt(7, dto.getNbIndent() + 1); // 부모글의 indent + 1
            pstmt.setString(8, dto.getIdFile());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }

        return result;
    }
    /**
     * 특정 게시글을 삭제합니다.
     *
     * @param nbBoard 삭제할 게시글 번호
     * @return 삭제 성공 시 1, 실패 시 0
     */
    public int deletePost(String nbBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "DELETE FROM TB_BOARD WHERE NB_BOARD = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nbBoard);
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }

        return result;
    }
}