package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;

public class ECProductDetailCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // request 파라미터에서 상품 번호 가져오기
            String productNo = request.getParameter("productNo");

            // 상품 정보 조회
            ProductDAO productDAO = new ProductDAO();
            ProductDTO product = productDAO.getProduct(productNo);

            // 조회된 상품 정보를 request에 저장
            request.setAttribute("product", product);

            // 상품 상세 페이지로 포워딩
            request.getRequestDispatcher("/WEB-INF/views/product/product_detail.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            // 예외 처리
            System.err.println("상품 상세 페이지 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();

            // 에러 페이지로 포워딩
            try {
                request.setAttribute("errorMessage", "상품 상세 페이지 처리 중 오류가 발생했습니다.");
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("에러 페이지 포워딩 중 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}