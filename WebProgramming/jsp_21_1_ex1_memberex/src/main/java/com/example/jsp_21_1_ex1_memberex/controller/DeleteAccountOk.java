package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet("/deleteOk")
public class DeleteAccountOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getSession().getAttribute("userId").toString();
        String password = request.getParameter("password");

        MemberDAO dao = new MemberDAO();

        if(dao.deleteMember(userId)) {
            request.setAttribute("userId", userId);

            // modifyResult.jsp로 포워딩
            request.getRequestDispatcher("deleteResult.jsp").forward(request, response);
        } else {
        }
    }
}