package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;

public class ECUnregisterCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 회원 탈퇴 처리 로직 구현
        // 예: DB에서 회원 정보 삭제, 세션 종료 등
        // 탈퇴 후 메인 페이지로 리다이렉트
        response.sendRedirect("main.jsp");
    }
}
