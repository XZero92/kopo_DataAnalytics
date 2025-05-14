package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

public class ECEditUserCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            UserDAO userDAO = new UserDAO();
            UserDTO user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/editUser.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("error.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}