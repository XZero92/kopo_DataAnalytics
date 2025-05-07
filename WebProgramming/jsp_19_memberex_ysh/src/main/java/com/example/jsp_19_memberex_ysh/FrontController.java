package com.example.jsp_19_memberex_ysh;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length());

        /*String viewPage = null;

        if (command.equals("/join.do")) {
            viewPage = "join.jsp";
        } else if (command.equals("/joinOk.do")) {
            viewPage = "joinOk.jsp";
        } else if (command.equals("/login.do")) {
            viewPage = "login.jsp";
        } else if (command.equals("/loginOk.do")) {
            viewPage = "loginOk.jsp";
        } else if (command.equals("/modify.do")) {
            viewPage = "modify.jsp";
        } else if (command.equals("/modifyOk.do")) {
            viewPage = "modifyOk.jsp";
        } else if (command.equals("/delete.do")) {
            viewPage = "delete.jsp";
        } else if (command.equals("/deleteOk.do")) {
            viewPage = "deleteOk.jsp";
        }

        request.getRequestDispatcher(viewPage).forward(request, response);*/
    }
}