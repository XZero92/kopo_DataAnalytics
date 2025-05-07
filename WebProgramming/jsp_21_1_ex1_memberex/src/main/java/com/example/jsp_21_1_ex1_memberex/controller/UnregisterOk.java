package com.example.jsp_21_1_ex1_memberex.controller;

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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String userId = (String) session.getAttribute("userId");
        String inputPassword = request.getParameter("password");

        MemberDAO memberDAO = new MemberDAO();
        boolean isPasswordCorrect = memberDAO.checkPassword(userId, inputPassword);

        if (isPasswordCorrect) {
            boolean isDeleted = memberDAO.deleteAccount(userId);
            if (isDeleted) {
                session.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                response.getWriter().write("계정 삭제에 실패했습니다. 다시 시도해주세요.");
            }
        } else {
            response.getWriter().write("비밀번호가 일치하지 않습니다.");
        }
    }
}
