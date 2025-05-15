package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao;

import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ContentDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.util.ShortUUID;

public class ContentDAO {

    private DataSource dataSource;

    public ContentDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveContent(Part filePart, String registerNo) {
        String fileId = ShortUUID.getShortUUID();
        String originalFileName = filePart.getSubmittedFileName();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        }
        String savedFileName = fileId + "." + fileExtension;
        String filePath = "/uploads/" + savedFileName;

        // 확장자에 따라 파일 타입 동적으로 지정
        String fileTypeCode = "";
        switch (fileExtension.toLowerCase()) {
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                fileTypeCode = "IMG";
                break;
            case "pdf":
                fileTypeCode = "PDF";
                break;
            case "doc":
            case "docx":
                fileTypeCode = "DOC";
                break;
            default:
                fileTypeCode = "OTH";
                break;
        }

        String sql = "INSERT INTO TB_CONTENT " +
                "(ID_FILE, NM_ORG_FILE, NM_SAVE_FILE, NM_FILE_PATH, BO_SAVE_FILE, " +
                "NM_FILE_EXT, CD_FILE_TYPE, DA_SAVE, CN_HIT, ID_SERVICE, ID_ORG_FILE, NO_REGISTER, DA_FIRST_DATE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 0, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             InputStream fileContent = filePart.getInputStream()) {

            ps.setString(1, fileId);
            ps.setString(2, originalFileName);
            ps.setString(3, savedFileName);
            ps.setString(4, filePath);
            ps.setBlob(5, fileContent);
            ps.setString(6, fileExtension);
            ps.setString(7, fileTypeCode);
            ps.setString(8, "PRODUCT");
            ps.setString(9, "");
            ps.setString(10, registerNo);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return fileId;
    }

    // fileId로 컨텐츠 정보를 조회하는 메서드
    public ContentDTO getContent(String fileId) {
        ContentDTO content = null;
        String sql = "SELECT ID_FILE, NM_ORG_FILE, NM_SAVE_FILE, NM_FILE_PATH, BO_SAVE_FILE, NM_FILE_EXT FROM TB_CONTENT WHERE ID_FILE = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fileId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    content = new ContentDTO();
                    content.setFileId(rs.getString("ID_FILE"));
                    content.setOriginalFileName(rs.getString("NM_ORG_FILE"));
                    content.setSavedFileName(rs.getString("NM_SAVE_FILE"));
                    content.setFilePath(rs.getString("NM_FILE_PATH"));
                    content.setSavedFile(rs.getBlob("BO_SAVE_FILE"));
                    content.setFileExtension(rs.getString("NM_FILE_EXT"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}