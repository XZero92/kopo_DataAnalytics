package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;

public class ECApproveUserCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            UserDAO userDAO = new UserDAO();
            userDAO.updateUserStatus(userId, "ST01"); // 상태를 '정상'으로 변경
            response.sendRedirect("manage_users.do");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it, show an error message, etc.)
        }
    }
}