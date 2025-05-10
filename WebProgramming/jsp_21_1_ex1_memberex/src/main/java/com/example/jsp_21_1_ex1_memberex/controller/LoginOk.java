package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;
import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/loginOk")
public class LoginOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        MemberDAO dao = new MemberDAO();
        MemberDTO member = dao.loginMember(userId, password);

        if (member != null) {
            if (MemberDTO.STATUS_INACTIVE.equals(member.getStatus())) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('아직 가입 승인 요청 상태인 회원입니다.'); location.href='index.jsp';</script>");
                return;
            } else if (MemberDTO.STATUS_SUSPENDED.equals(member.getStatus())) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('계정이 일시 정지 상태입니다. 관리자에게 문의하세요.'); location.href='index.jsp';</script>");
                return;
            } else if (MemberDTO.STATUS_DELETED.equals(member.getStatus())) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('탈퇴된 계정입니다.'); location.href='index.jsp';</script>");
                return;
            }
        
        // 세션에 사용자 ID 저장
        request.getSession().setAttribute("userId", member.getUserId());
        request.getSession().setAttribute("userName", member.getUserName());
        request.getSession().setAttribute("userType", member.getUserType());

        // 쿠키 생성 및 추가
        Cookie userCookie = new Cookie("userId", userId);
        userCookie.setMaxAge(60 * 60 * 24); // 1일 동안 유지
        response.addCookie(userCookie);

        // 로그인 성공 시 index.jsp로 포워딩
        response.sendRedirect("index.jsp");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.'); location.href='login.jsp';</script>");
        }
    }
}