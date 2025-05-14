package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.CategoryDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;

public class ECUpdateCategoryCommand implements ECCommand {
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
            String categoryName = request.getParameter("categoryName");
            String explanation = request.getParameter("explanation");
            String useYn = request.getParameter("useYn");
            
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
            
            // 수정할 카테고리 조회
            CategoryDTO category = categoryDAO.getCategory(categoryId);
            if (category == null) {
                request.getSession().setAttribute("errorMessage", "해당 카테고리를 찾을 수 없습니다.");
                response.sendRedirect("manage_categories.do");
                return;
            }
            
            // 카테고리 정보 업데이트
            category.setCategoryName(categoryName);
            category.setExplain(explanation);
            category.setUseYn(useYn != null && useYn.equals("Y") ? "Y" : "N");
            
            // 전체 카테고리 이름 갱신
            String fullCategoryName = categoryDAO.generateFullCategoryName(category.getParentCategoryNo(), categoryName);
            category.setFullCategoryName(fullCategoryName);
            
            // 카테고리 업데이트
            boolean success = categoryDAO.updateCategory(category);
            
            if (success) {
                // 성공 메시지 설정
                request.getSession().setAttribute("message", "카테고리가 성공적으로 업데이트되었습니다.");
            } else {
                // 실패 메시지 설정
                request.getSession().setAttribute("errorMessage", "카테고리 업데이트 중 오류가 발생했습니다.");
            }
            
            // 카테고리 관리 페이지로 리다이렉트
            response.sendRedirect("manage_categories.do");
        } catch (IOException e) {
            System.err.println("카테고리 업데이트 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}