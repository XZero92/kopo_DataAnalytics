package com.example.jsp_21_1_ex1_memberex.controller;

import com.example.jsp_21_1_ex1_memberex.repository.MemberDAO;
import com.example.jsp_21_1_ex1_memberex.model.MemberDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import java.io.IOException;

@WebServlet("/joinOk")
public class JoinOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("join.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");

        // 비밀번호 유효성 검사 (최소 4글자, 영문자+숫자 조합)
        if (!isValidPassword(password)) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('비밀번호는 최소 4자 이상의 영문자와 숫자 조합이어야 합니다.'); history.back();</script>");
            return;
        }

        MemberDTO member = new MemberDTO();
        member.setUserId(userId);
        member.setUserName(userName);
        member.setPassword(password);
        member.setEncPassword(password); // 실제 구현시 암호화 필요
        member.setMobileNo(mobileNo);
        member.setEmail(userId);
        member.setStatus(MemberDTO.STATUS_INACTIVE); // 가입요청 상태로 시작
        member.setUserType(MemberDTO.USER_TYPE_MEMBER); // 일반 회원으로 시작
        member.setRegisterNo(null);
        member.setFirstDate(new Timestamp(System.currentTimeMillis()));

        MemberDAO dao = new MemberDAO();

        if(dao.insertMember(member)) {
            request.setAttribute("userId", userId);
            request.setAttribute("userName", userName);
            request.setAttribute("email", email);
            request.setAttribute("mobileNo", mobileNo);

            // joinResult.jsp로 포워딩
            request.getRequestDispatcher("/WEF-INF/views/joinResult.jsp").forward(request, response);
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('회원가입에 실패했습니다.'); location.href='join.jsp';</script>");
        }
    }
    
    // 비밀번호 유효성 검사 메서드
    private boolean isValidPassword(String password) {
        // 영문자와 숫자 조합으로 최소 4글자 이상
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
        return Pattern.matches(regex, password);
    }
}