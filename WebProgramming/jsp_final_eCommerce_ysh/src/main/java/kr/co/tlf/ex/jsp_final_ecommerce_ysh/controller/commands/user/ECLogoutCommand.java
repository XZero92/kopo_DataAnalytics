package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;

public class ECLogoutCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 세션을 무효화하여 로그아웃 처리

    }
}
