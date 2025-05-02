package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;
import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/joinOk")
public class JoinOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("id");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String mobileNo = request.getParameter("mobileNo");

        MemberDTO member = new MemberDTO(
                userId,
                userName,
                password,
                email,
                mobileNo,
                MemberDTO.USER_TYPE_MEMBER,
                MemberDTO.STATUS_INACTIVE,
                null,
                java.time.LocalDate.now());
        MemberDAO dao = new MemberDAO();

        if(dao.insertMember(member)) {
            request.setAttribute("id", userId);
            request.setAttribute("username", userName);
            request.setAttribute("email", email);
            request.setAttribute("mobileNo", mobileNo);

            // joinResult.jsp로 포워딩
            request.getRequestDispatcher("joinResult.jsp").forward(request, response);
        } else {
            response.getWriter().println("회원가입 실패");
        }

        /*DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String addMemberQuery = "INSERT INTO member (id, paswd, username, email, mobileNo, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(addMemberQuery);
            pstmt.setString(1, id);
            pstmt.setString(2, paswd);
            pstmt.setString(3, username);
            pstmt.setString(4, email);
            pstmt.setString(5, mobileNo);
            pstmt.setString(6, gender);

            result = pstmt.executeUpdate();

            if (result > 0) {
                request.setAttribute("id", id);
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("mobileNo", mobileNo);
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