package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;

public class ECDeleteUserCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            UserDAO userDAO = new UserDAO();
            userDAO.deleteUser(userId);
            response.sendRedirect("manage_users.do");
        } catch (Exception e) {
            e.printStackTrace();}
        }
}