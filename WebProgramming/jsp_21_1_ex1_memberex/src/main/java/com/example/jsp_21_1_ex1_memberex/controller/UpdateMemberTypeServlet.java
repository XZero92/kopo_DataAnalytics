package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;
import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/admin/updateMemberType")
public class UpdateMemberTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null || 
            !MemberDTO.USER_TYPE_ADMIN.equals(session.getAttribute("userType"))) {
            response.sendRedirect("../login.jsp");
            return;
        }

        String userId = request.getParameter("userId");
        String userType = request.getParameter("userType");

        MemberDAO dao = new MemberDAO();
        boolean success = dao.updateMemberType(userId, userType);

        response.setContentType("text/html; charset=UTF-8");
        if (success) {
            response.getWriter().println("<script>alert('회원 권한이 성공적으로 변경되었습니다.'); location.href='../admin/userList.jsp';</script>");
        } else {
            response.getWriter().println("<script>alert('회원 권한 변경에 실패했습니다.'); location.href='../admin/userList.jsp';</script>");
        }
    }
}