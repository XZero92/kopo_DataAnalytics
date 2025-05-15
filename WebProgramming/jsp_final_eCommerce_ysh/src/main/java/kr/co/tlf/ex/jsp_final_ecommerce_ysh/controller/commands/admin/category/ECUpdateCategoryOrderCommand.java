package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryDAO;
import java.io.IOException;

public class ECUpdateCategoryOrderCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        String[] categoryIds = request.getParameterValues("categoryId");
        String[] orders = request.getParameterValues("order");
        CategoryDAO categoryDAO = new CategoryDAO();
        boolean allSuccess = true;

        try {
            if (categoryIds != null && orders != null && categoryIds.length == orders.length) {
                for (int i = 0; i < categoryIds.length; i++) {
                    try {
                        int catId = Integer.parseInt(categoryIds[i]);
                        int order = Integer.parseInt(orders[i]);
                        boolean success = categoryDAO.updateCategoryOrder(catId, order);
                        if (!success) {
                            allSuccess = false;
                        }
                    } catch (NumberFormatException e) {
                        allSuccess = false;
                        e.printStackTrace();
                    }
                }
            }

            if (allSuccess) {
                request.getSession().setAttribute("message", "카테고리 순서가 성공적으로 업데이트되었습니다.");
            } else {
                request.getSession().setAttribute("errorMessage", "순서 업데이트 중 오류가 발생했습니다.");
            }
            response.sendRedirect("manage_categories.do");
        } catch (IOException e) {
            System.err.println("카테고리 순서 업데이트 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}