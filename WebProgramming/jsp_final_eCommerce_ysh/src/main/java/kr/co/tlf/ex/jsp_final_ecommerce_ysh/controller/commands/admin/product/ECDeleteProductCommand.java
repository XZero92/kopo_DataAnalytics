package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;

public class ECDeleteProductCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 세션에서 로그인 정보 확인
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

            // 로그인되지 않았거나 관리자가 아닌 경우 접근 제한
            if (loginUser == null || !UserDTO.USER_TYPE_ADMIN.equals(loginUser.getUserType())) {
                response.sendRedirect("login.do");
                return;
            }

            // 요청 파라미터 가져오기
            String productNo = request.getParameter("productNo");
            
            if (productNo == null || productNo.isEmpty()) {
                request.getSession().setAttribute("errorMessage", "상품 번호가 유효하지 않습니다.");
                response.sendRedirect("manage_products.do");
                return;
            }
            
            // 상품 DAO 생성
            ProductDAO productDAO = new ProductDAO();
            
            // 상품-카테고리 연결 제거
            productDAO.removeAllProductCategories(productNo);
            
            // 상품 삭제
            boolean success = productDAO.deleteProduct(productNo);
            
            if (success) {
                // 성공 메시지 설정
                request.getSession().setAttribute("message", "상품이 성공적으로 삭제되었습니다.");
            } else {
                // 실패 메시지 설정
                request.getSession().setAttribute("errorMessage", "상품 삭제 중 오류가 발생했습니다.");
            }
            
            // 상품 관리 페이지로 리다이렉트
            response.sendRedirect("manage_products.do");
        } catch (IOException e) {
            System.err.println("상품 삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}