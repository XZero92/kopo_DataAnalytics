package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.product;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;

import java.io.PrintWriter;
import java.util.List;

public class ECSearchProductsCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String searchTerm = request.getParameter("term");

            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.searchProducts(searchTerm);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(products));
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(new Gson().toJson(new ErrorResponse("상품 검색 중 오류가 발생했습니다.")));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}
