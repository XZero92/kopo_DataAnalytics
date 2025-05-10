package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;
import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;

import java.io.IOException;

@WebServlet("/modifyOk")
public class ModifyOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("modify.jsp");
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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String mobileNo = request.getParameter("mobileNo");

        // 비밀번호 유효성 검사 (최소 4글자, 영문자+숫자 조합)
        if (!isValidPassword(password)) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('비밀번호는 최소 4자 이상의 영문자와 숫자 조합이어야 합니다.'); history.back();</script>");
            return;
        }

        MemberDAO dao = new MemberDAO();

        if(dao.updateMemberInfo(userId, userName, password, mobileNo)) {
            // 세션 정보 업데이트
            session.setAttribute("userName", userName);

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('회원정보가 성공적으로 수정되었습니다.'); location.href='index.jsp';</script>");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('회원정보 수정에 실패했습니다.'); location.href='modify.jsp';</script>");
        }
    }
    
    // 비밀번호 유효성 검사 메서드
    private boolean isValidPassword(String password) {
        // 영문자와 숫자 조합으로 최소 4글자 이상
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
        return Pattern.matches(regex, password);
    }
}