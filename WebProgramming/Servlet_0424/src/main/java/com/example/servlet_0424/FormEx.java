package com.example.servlet_0424;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FormEx")
public class FormEx extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("userName");
        String userID = request.getParameter("userID");
        String userPW = request.getParameter("userPW");

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>POST 요청 처리 결과</h1>");

        out.println("<p>이름: " + userName + "</p>");
        out.println("<p>아이디: " + userID + "</p>");
        out.println("<p>비밀번호: " + userPW + "</p>");
        out.println("</body></html>");
    }
}