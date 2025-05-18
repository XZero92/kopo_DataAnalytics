package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryProductMappingDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.CategoryDTO;

import java.util.List;

public class ECManageCategoryMappingCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryDTO> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);

        try {
            request.getRequestDispatcher("/WEB-INF/views/admin/manage_category_mapping.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
