package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao;

import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
        String fileId = UUID.randomUUID().toString();
        String originalFileName = filePart.getSubmittedFileName();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        }
        String savedFileName = fileId + "." + fileExtension;
        String filePath = "/uploads/" + savedFileName;

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
            ps.setString(7, "IMG");
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
}