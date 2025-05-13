package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;

import java.io.IOException;

public class ECLogoutCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 세션 가져오기
            HttpSession session = request.getSession(false);
            
            // 세션이 존재하면 무효화
            if (session != null) {
                session.invalidate();
            }
            
            // 로그인 페이지로 리다이렉트 (로그아웃 성공 메시지 포함)
            response.sendRedirect("login.do?logout=true");
        } catch (IOException e) {
            // 예외 로깅
            System.err.println("로그아웃 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}