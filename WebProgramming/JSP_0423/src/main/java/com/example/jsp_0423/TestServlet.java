package com.example.jsp_0423;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {


    public void init() {
        // Initialization code, if needed
        System.out.println("TestServlet init");
    }

    public void destroy() {
        // Cleanup code, if needed
        System.out.println("TestServlet destroy");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello Servlet!</h1>");
        out.println("<p>테스트 텍스트</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 데이터의 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 폼 데이터 읽기
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        // 응답 데이터 설정
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>POST 요청 처리 결과</h1>");
        if (name != null && age != null) {
            out.println("<p>이름: " + name + "</p>");
            out.println("<p>나이: " + age + "</p>");
        } else {
            out.println("<p>폼 데이터가 누락되었습니다.</p>");
        }
        out.println("</body></html>");
    }
}
