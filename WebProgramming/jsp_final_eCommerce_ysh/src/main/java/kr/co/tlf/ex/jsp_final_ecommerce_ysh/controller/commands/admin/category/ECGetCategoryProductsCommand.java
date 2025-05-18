package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryProductMappingDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.CategoryProductMappingDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ECGetCategoryProductsCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

            CategoryProductMappingDAO mappingDAO = new CategoryProductMappingDAO();
            ProductDAO productDAO = new ProductDAO();

            // 해당 카테고리의 매핑 정보를 가져옴
            List<CategoryProductMappingDTO> mappings = mappingDAO.getMappingsByCategory(categoryNo);
            List<ProductDTO> products = new ArrayList<>();

            // 각 매핑의 상품 정보를 조회
            for (CategoryProductMappingDTO mapping : mappings) {
                ProductDTO product = productDAO.getProduct(mapping.getProductNo());
                if (product != null) {
                    products.add(product);
                }
            }

            // JSON 응답 생성
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(products));
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(new Gson().toJson(new ErrorResponse("상품 목록을 불러오는 중 오류가 발생했습니다.")));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class ErrorResponse {
        private final String error;
        private final boolean success = false;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}
