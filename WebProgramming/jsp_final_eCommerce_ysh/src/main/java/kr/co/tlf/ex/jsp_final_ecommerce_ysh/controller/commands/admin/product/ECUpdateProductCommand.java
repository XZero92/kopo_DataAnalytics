package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;

public class ECUpdateProductCommand implements ECCommand {
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
            String productName = request.getParameter("productName");
            String detailExplain = request.getParameter("detailExplain");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String salePriceStr = request.getParameter("salePrice");
            String stockQuantityStr = request.getParameter("stockQuantity");
            String customerQuantityStr = request.getParameter("customerQuantity");
            String deliveryFeeStr = request.getParameter("deliveryFee");
            String[] categoryIds = request.getParameterValues("categoryIds");
            
            // 필수 파라미터 검증
            if (productNo == null || productNo.isEmpty() ||
                productName == null || productName.isEmpty() ||
                customerQuantityStr == null || customerQuantityStr.isEmpty() ||
                salePriceStr == null || salePriceStr.isEmpty() ||
                stockQuantityStr == null || stockQuantityStr.isEmpty()) {
                request.getSession().setAttribute("errorMessage", "상품번호, 상품명,  소비자 가격, 판매 가격, 재고 수량은 필수 입력 항목입니다.");
                response.sendRedirect("manage_products.do");
                return;
            }
            
            // 상품 DAO 생성
            ProductDAO productDAO = new ProductDAO();
            
            // 수정할 상품 조회
            ProductDTO product = productDAO.getProduct(productNo);
            if (product == null) {
                request.getSession().setAttribute("errorMessage", "해당 상품을 찾을 수 없습니다.");
                response.sendRedirect("manage_products.do");
                return;
            }
            
            // 상품 정보 업데이트
            product.setProductName(productName);
            product.setDetailExplain(detailExplain);
            product.setStartDate(startDate);
            product.setEndDate(endDate);
            
            // 숫자형 데이터 변환
            try {
                product.setSalePrice(Integer.parseInt(salePriceStr));
                product.setStockQuantity(Integer.parseInt(stockQuantityStr));
                product.setDeliveryFee(deliveryFeeStr != null && !deliveryFeeStr.isEmpty() 
                                      ? Integer.parseInt(deliveryFeeStr) : 0);
                product.setCustomerQuantity(customerQuantityStr != null && !customerQuantityStr.isEmpty() 
                                          ? Integer.parseInt(customerQuantityStr) : 1);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("errorMessage", "숫자 형식이 올바르지 않습니다.");
                response.sendRedirect("manage_products.do");
                return;
            }
            
            // 상품 업데이트
            boolean success = productDAO.updateProduct(product);
            
            if (success) {
                // 카테고리 연결 업데이트
                // 기존 카테고리 연결 모두 삭제
                productDAO.removeAllProductCategories(productNo);
                
                // 새로운 카테고리 연결 추가
                if (categoryIds != null && categoryIds.length > 0) {
                    for (String categoryId : categoryIds) {
                        try {
                            int catId = Integer.parseInt(categoryId);
                            productDAO.addProductToCategory(productNo, catId);
                        } catch (NumberFormatException e) {
                            // 잘못된 카테고리 ID는 무시
                            System.err.println("잘못된 카테고리 ID: " + categoryId);
                        }
                    }
                }
                
                // 성공 메시지 설정
                request.getSession().setAttribute("message", "상품이 성공적으로 업데이트되었습니다.");
            } else {
                // 실패 메시지 설정
                request.getSession().setAttribute("errorMessage", "상품 업데이트 중 오류가 발생했습니다.");
            }
            
            // 상품 관리 페이지로 리다이렉트
            response.sendRedirect("manage_products.do");
        } catch (IOException e) {
            System.err.println("상품 업데이트 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}