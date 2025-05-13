package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ECMainCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 메인 페이지로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.err.println("메인 페이지 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}