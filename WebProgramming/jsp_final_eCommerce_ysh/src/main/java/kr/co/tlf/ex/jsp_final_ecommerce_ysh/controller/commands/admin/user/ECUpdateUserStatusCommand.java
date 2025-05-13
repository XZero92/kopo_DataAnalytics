package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;

public class ECUpdateUserStatusCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String status = request.getParameter("status");
        UserDAO userDAO = new UserDAO();
        userDAO.updateUserStatus(userId, status);
        response.sendRedirect("manage_users.do");
    }
}