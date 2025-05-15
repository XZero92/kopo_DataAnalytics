package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ContentDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ECAddProductCommand implements ECCommand {
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
            if (productName == null || productName.isEmpty() ||
                customerQuantityStr == null || customerQuantityStr.isEmpty() ||
                salePriceStr == null || salePriceStr.isEmpty() ||
                stockQuantityStr == null || stockQuantityStr.isEmpty()) {
                request.getSession().setAttribute("errorMessage", "상품명, 소비자 가격, 판매 가격, 재고 수량은 필수 입력 항목입니다.");
                response.sendRedirect("manage_products.do");
                return;
            }
            
            // 상품 DAO 생성
            ProductDAO productDAO = new ProductDAO();
            
            // 새 상품 객체 생성
            ProductDTO newProduct = new ProductDTO();
            newProduct.setProductNo(productDAO.generateProductNo());
            newProduct.setProductName(productName);
            newProduct.setDetailExplain(detailExplain);
            
            // 파일 ID 처리 (파일 업로드 기능이 추가로 필요함)
            try {
                Part filePart = request.getPart("productImage");
                if (filePart != null && filePart.getSize() > 0) {
                    ContentDAO contentDAO = new ContentDAO();
                    String fileId = contentDAO.saveContent(filePart, loginUser.getUserId());
                    newProduct.setFileId(fileId);
                } else {
                    newProduct.setFileId("");
                }
            } catch (IOException | ServletException e) {
                System.err.println("파일 업로드 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }


            if (startDate != null && !startDate.isEmpty()) {
                newProduct.setStartDate(startDate.replace("-", ""));
            } else {
                newProduct.setStartDate(null);
            }

            if (endDate != null && !endDate.isEmpty()) {
                newProduct.setEndDate(endDate.replace("-", ""));
            } else {
                newProduct.setEndDate(null);
            }
            
            // 숫자형 데이터 변환
            try {
                newProduct.setSalePrice(Integer.parseInt(salePriceStr));
                newProduct.setStockQuantity(Integer.parseInt(stockQuantityStr));
                newProduct.setDeliveryFee(deliveryFeeStr != null && !deliveryFeeStr.isEmpty() 
                                          ? Integer.parseInt(deliveryFeeStr) : 0);
                newProduct.setCustomerQuantity(customerQuantityStr != null && !customerQuantityStr.isEmpty() 
                                              ? Integer.parseInt(customerQuantityStr) : 1);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("errorMessage", "숫자 형식이 올바르지 않습니다.");
                response.sendRedirect("manage_products.do");
                return;
            }
            
            // 등록자 정보 설정
            newProduct.setRegisterNo(loginUser.getUserId());
            
            // 상품 추가
            boolean success = productDAO.addProduct(newProduct);
            
            if (success) {
                /*// 카테고리 연결
                if (categoryIds != null && categoryIds.length > 0) {
                    for (String categoryId : categoryIds) {
                        try {
                            int catId = Integer.parseInt(categoryId);
                            productDAO.addProductToCategory(newProduct.getProductNo(), catId);
                        } catch (NumberFormatException e) {
                            // 잘못된 카테고리 ID는 무시
                            System.err.println("잘못된 카테고리 ID: " + categoryId);
                        }
                    }
                }*/
                
                // 성공 메시지 설정
                request.getSession().setAttribute("message", "상품이 성공적으로 추가되었습니다.");
            } else {
                // 실패 메시지 설정
                request.getSession().setAttribute("errorMessage", "상품 추가 중 오류가 발생했습니다.");
            }
            
            // 상품 관리 페이지로 리다이렉트
            response.sendRedirect("manage_products.do");
        } catch (IOException e) {
            System.err.println("상품 추가 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}