package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.ProductDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.CategoryDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class ECManageProductsCommand implements ECCommand {
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

            // 상품 목록 조회
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            
            // 카테고리 목록 조회 (상품 등록/수정 시 선택 목록용)
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);

            // 상품 관리 페이지로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/manage_products.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.err.println("상품 관리 페이지 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}