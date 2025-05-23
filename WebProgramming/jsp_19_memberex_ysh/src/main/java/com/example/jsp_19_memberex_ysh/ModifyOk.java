package com.example.jsp_19_memberex_ysh;

import com.example.jsp_19_memberex_ysh.dao.MemberDAO;
import com.example.jsp_19_memberex_ysh.dto.MemberDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/modifyOk")
public class ModifyOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청 처리 (필요시)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = (String) request.getSession().getAttribute("userId");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");

        MemberDTO member = new MemberDTO(id, null, username, email, mobile, gender);
        MemberDAO dao = new MemberDAO();

        if(dao.updateMember(member)) {
            request.setAttribute("id", id);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobile);
            request.setAttribute("gender", gender);

            // modifyResult.jsp로 포워딩
            request.getRequestDispatcher("modifyResult.jsp").forward(request, response);
        } else {
            response.getWriter().println("회원정보 수정 실패");
        }

        /*DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;

        String query = "UPDATE member SET username = ?, email = ?, mobile = ?, gender = ? WHERE id = ?";

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, mobile);
            pstmt.setString(4, gender);
            pstmt.setString(5, id);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                request.setAttribute("id", id);
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("mobile", mobile);
                request.setAttribute("gender", gender);

                // modifyResult.jsp로 포워딩
                request.getRequestDispatcher("modifyResult.jsp").forward(request, response);
            } else {
                response.getWriter().println("회원정보 수정 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}