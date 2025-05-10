package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;
import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/unregisterOk")
public class UnregisterOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("unregister.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = (String) session.getAttribute("userId");
        String password = request.getParameter("password");

        // 비밀번호 확인
        MemberDAO dao = new MemberDAO();
        boolean isPasswordValid = dao.validatePassword(userId, password);

        if (!isPasswordValid) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
            return;
        }

        // 회원 상태를 '탈퇴'로 변경
        boolean success = dao.updateMemberStatus(userId, MemberDTO.STATUS_DELETED);

        if (success) {
            // 세션 무효화
            session.invalidate();

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('회원 탈퇴가 완료되었습니다. 그동안 이용해 주셔서 감사합니다.'); location.href='index.jsp';</script>");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('회원 탈퇴 처리 중 오류가 발생했습니다.'); history.back();</script>");
        }
    }
}