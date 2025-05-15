package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ContentDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ContentDTO;

public class ECGetImageCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        String fileId = request.getParameter("fileId");

        try {
            if (fileId == null || fileId.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "파일 ID가 유효하지 않습니다.");
                return;
            }

            ContentDAO contentDAO = new ContentDAO();
            ContentDTO content = contentDAO.getContent(fileId);

            if (content == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 파일을 찾을 수 없습니다.");
                return;
            }

            // 파일 확장자에 따른 MIME 타입 결정
            String fileExtension = content.getFileExtension().toLowerCase();
            String mimeType = "application/octet-stream";
            if (fileExtension.equals("jpg") || fileExtension.equals("jpeg")) {
                mimeType = "image/jpeg";
            } else if (fileExtension.equals("png")) {
                mimeType = "image/png";
            } else if (fileExtension.equals("gif")) {
                mimeType = "image/gif";
            } else if (fileExtension.equals("pdf")) {
                mimeType = "application/pdf";
            }

            response.setContentType(mimeType);

            Blob blob = content.getSavedFile();
            if (blob != null) {
                try (InputStream in = blob.getBinaryStream();
                     OutputStream out = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                } catch (Exception e) {
                    throw new ServletException("파일 전송 중 오류가 발생했습니다.", e);
                }
            }
        } catch (IOException | ServletException e) {
            System.err.println("파일 전송 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}