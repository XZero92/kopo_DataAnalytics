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

@WebServlet("/admin/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null ||
                !MemberDTO.USER_TYPE_ADMIN.equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String userId = request.getParameter("userId");

        MemberDAO dao = new MemberDAO();
        boolean success = dao.deleteMember(userId);

        response.setContentType("text/html; charset=UTF-8");
        if (success) {
            response.getWriter().println("<script>alert('회원이 성공적으로 삭제되었습니다.'); location.href='" + 
                request.getContextPath() + "/views/admin/userList';</script>");
        } else {
            response.getWriter().println("<script>alert('회원 삭제에 실패했습니다.'); location.href='" + 
                request.getContextPath() + "/views/admin/userList';</script>");
        }
    }
}