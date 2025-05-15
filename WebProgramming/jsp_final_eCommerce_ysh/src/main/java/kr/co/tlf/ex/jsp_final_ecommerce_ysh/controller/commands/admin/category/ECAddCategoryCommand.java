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

public class ECAddCategoryCommand implements ECCommand {
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
            String categoryName = request.getParameter("categoryName");
            String explanation = request.getParameter("explanation");
            String parentCategoryIdStr = request.getParameter("parentCategoryId");
            int parentCategoryId = 0; // 기본값은 루트 카테고리
            
            if (parentCategoryIdStr != null && !parentCategoryIdStr.isEmpty()) {
                try {
                    parentCategoryId = Integer.parseInt(parentCategoryIdStr);
                } catch (NumberFormatException e) {
                    // 숫자 형식이 아닌 경우 루트 카테고리로 설정
                    parentCategoryId = 0;
                }
            }
            
            // 카테고리 DAO 생성
            CategoryDAO categoryDAO = new CategoryDAO();
            
            // 새 카테고리 객체 생성
            CategoryDTO newCategory = new CategoryDTO();
            newCategory.setCategoryNo(categoryDAO.getNextCategoryNo());
            newCategory.setParentCategoryNo(parentCategoryId);
            newCategory.setCategoryName(categoryName);
            newCategory.setExplain(explanation);
            
            // 부모 카테고리가 있다면 부모의 레벨 + 1로 설정, 없으면 1로 설정
            int level = 1;
            if (parentCategoryId > 0) {
                CategoryDTO parentCategory = categoryDAO.getCategory(parentCategoryId);
                if (parentCategory != null) {
                    level = parentCategory.getLevel() + 1;
                }
            }
            newCategory.setLevel(level);
            
            // 같은 부모 카테고리 내에서의 순서 (마지막에 추가)
            // 실제로는 더 복잡한 로직이 필요할 수 있음
            int newOrder = categoryDAO.getNextCategoryOrder(parentCategoryId);
            newCategory.setOrder(newOrder);
            
            // 전체 카테고리 이름 생성 (부모 카테고리 이름 + 현재 카테고리 이름)
            String fullCategoryName = categoryDAO.generateFullCategoryName(parentCategoryId, categoryName);
            newCategory.setFullCategoryName(fullCategoryName);
            
            // 사용 여부 및 삭제 여부 설정
            newCategory.setUseYn("Y");
            newCategory.setDeleteYn("N");
            newCategory.setRegisterNo(loginUser.getUserId());
            
            // 등록자 정보 설정
            newCategory.setRegisterNo(loginUser.getUserId());
            
            // 카테고리 추가
            boolean success = categoryDAO.addCategory(newCategory);
            
            if (success) {
                // 성공 메시지 설정
                request.getSession().setAttribute("message", "카테고리가 성공적으로 추가되었습니다.");
            } else {
                // 실패 메시지 설정
                request.getSession().setAttribute("errorMessage", "카테고리 추가 중 오류가 발생했습니다.");
            }
            
            // 카테고리 관리 페이지로 리다이렉트
            response.sendRedirect("manage_categories.do");
        } catch (IOException e) {
            System.err.println("카테고리 추가 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}