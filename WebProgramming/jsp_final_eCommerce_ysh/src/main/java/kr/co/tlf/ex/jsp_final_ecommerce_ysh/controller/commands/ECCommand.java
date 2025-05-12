package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ECCommand {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
