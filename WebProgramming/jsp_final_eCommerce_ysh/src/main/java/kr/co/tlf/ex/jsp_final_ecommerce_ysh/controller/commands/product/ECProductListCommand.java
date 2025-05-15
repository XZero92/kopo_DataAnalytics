package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;

public class ECProductListCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 전체 상품 목록 조회
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.getAllProducts();

            // 조회된 상품 목록을 request에 저장
            request.setAttribute("products", products);

            // 상품 목록 페이지로 포워딩
            request.getRequestDispatcher("/WEB-INF/views/product/product_list.jsp").forward(request, response);
        } catch (Exception e) {
            try {
                // 오류 시 에러 메시지와 함께 에러 페이지로 포워딩
                request.setAttribute("errorMessage", "상품 목록 처리 중 오류가 발생했습니다.");
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("상품 목록 처리 중 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}