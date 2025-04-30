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

@WebServlet("/joinOk")
public class JoinOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String paswd = request.getParameter("paswd");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");

        MemberDTO member = new MemberDTO(id, paswd, username, email, mobile, gender);
        MemberDAO dao = new MemberDAO();

        if(dao.insertMember(member)) {
            request.setAttribute("id", id);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobile);
            request.setAttribute("gender", gender);

            // joinResult.jsp로 포워딩
            request.getRequestDispatcher("joinResult.jsp").forward(request, response);
        } else {
            response.getWriter().println("회원가입 실패");
        }

        /*DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String addMemberQuery = "INSERT INTO member (id, paswd, username, email, mobile, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(addMemberQuery);
            pstmt.setString(1, id);
            pstmt.setString(2, paswd);
            pstmt.setString(3, username);
            pstmt.setString(4, email);
            pstmt.setString(5, mobile);
            pstmt.setString(6, gender);

            result = pstmt.executeUpdate();

            if (result > 0) {
                request.setAttribute("id", id);
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("mobile", mobile);
                request.setAttribute("gender", gender);

                // joinResult.jsp로 포워딩
                request.getRequestDispatcher("joinResult.jsp").forward(request, response);
            } else {
                response.getWriter().println("회원가입 실패");
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