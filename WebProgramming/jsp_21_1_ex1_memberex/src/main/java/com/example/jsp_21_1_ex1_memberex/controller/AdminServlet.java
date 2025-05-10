package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 관리자 권한 확인
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null ||
                !MemberDTO.USER_TYPE_ADMIN.equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String pathInfo = request.getPathInfo();
        String jspPage;

        // URL 패턴에 따라 다른 JSP 페이지로 포워딩
        if ("/userList".equals(pathInfo)) {
            jspPage = "/WEB-INF/views/admin/userList.jsp";
        } else if ("/userRequestList".equals(pathInfo)) {
            jspPage = "/WEB-INF/views/admin/userRequestList.jsp";
        } else if ("/userDeletedList".equals(pathInfo)) {
            jspPage = "/WEB-INF/views/admin/userDeletedList.jsp";
        } else if ("/adminPage".equals(pathInfo)) {
            jspPage = "/WEB-INF/views/admin/adminPage.jsp";
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // JSP 페이지로 포워딩
        request.getRequestDispatcher(jspPage).forward(request, response);
    }
}