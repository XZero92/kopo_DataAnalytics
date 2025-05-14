package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;

public class ECDeleteCategoryCommand implements ECCommand {
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
            String categoryIdStr = request.getParameter("categoryId");
            
            if (categoryIdStr == null || categoryIdStr.isEmpty()) {
                request.getSession().setAttribute("errorMessage", "카테고리 ID가 유효하지 않습니다.");
                response.sendRedirect("manage_categories.do");
                return;
            }
            
            int categoryId;
            try {
                categoryId = Integer.parseInt(categoryIdStr);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("errorMessage", "카테고리 ID가 유효하지 않습니다.");
                response.sendRedirect("manage_categories.do");
                return;
            }
            
            // 카테고리 DAO 생성
            CategoryDAO categoryDAO = new CategoryDAO();
            
            // 카테고리 삭제 (논리적 삭제)
            boolean success = categoryDAO.deleteCategory(categoryId);
            
            if (success) {
                // 성공 메시지 설정
                request.getSession().setAttribute("message", "카테고리가 성공적으로 삭제되었습니다.");
            } else {
                // 실패 메시지 설정
                request.getSession().setAttribute("errorMessage", "카테고리 삭제 중 오류가 발생했습니다.");
            }
            
            // 카테고리 관리 페이지로 리다이렉트
            response.sendRedirect("manage_categories.do");
        } catch (IOException e) {
            System.err.println("카테고리 삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}