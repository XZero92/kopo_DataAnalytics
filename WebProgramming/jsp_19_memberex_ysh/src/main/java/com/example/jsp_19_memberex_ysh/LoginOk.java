package com.example.jsp_19_memberex_ysh;

import com.example.jsp_19_memberex_ysh.dao.MemberDAO;
import com.example.jsp_19_memberex_ysh.dto.MemberDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/loginOk")
public class LoginOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String paswd = request.getParameter("paswd");

        MemberDAO dao = new MemberDAO();
        MemberDTO member = dao.loginMember(id, paswd);

        if (member != null) {
            request.getSession().setAttribute("userId", member.getId());
            request.getSession().setAttribute("username", member.getUsername());

            // 쿠키 생성 및 추가
            Cookie userCookie = new Cookie("userId", id);
            userCookie.setMaxAge(60 * 60 * 24); // 1일 동안 유지
            response.addCookie(userCookie);

            // 로그인 성공 시 loginResult.jsp로 포워딩
            response.sendRedirect("loginResult.jsp");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.'); location.href='login.html';</script>");
            response.getWriter().close();
        }
        /*DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM member WHERE id = ? AND paswd = ?";

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, paswd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 세션에 사용자 ID 저장
                String username = rs.getString("username");
                request.getSession().setAttribute("userId", id);
                request.getSession().setAttribute("username", username);

                // 쿠키 생성 및 추가
                Cookie userCookie = new Cookie("userId", id);
                userCookie.setMaxAge(60 * 60 * 24); // 1일 동안 유지
                response.addCookie(userCookie);

                response.sendRedirect("loginResult.jsp");
            } else {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.'); location.href='login.html';</script>");
                response.getWriter().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}