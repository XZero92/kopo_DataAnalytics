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
import java.util.List;

public class ECDeleteCategoryCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 디버깅: 전체 요청 URL 출력
            System.out.println("요청 URL: " + request.getRequestURL().toString() + "?" + request.getQueryString());

            // 디버깅: 모든 요청 파라미터 출력
            System.out.println("모든 요청 파라미터:");
            request.getParameterMap().forEach((key, value) -> {
                System.out.println(key + ": " + String.join(", ", value));
            });

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
            System.out.println("받은 카테고리 ID: " + categoryIdStr); // 디버깅 로그

            if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
                System.out.println("카테고리 ID가 null 또는 비어있음"); // 디버깅 로그
                request.getSession().setAttribute("errorMessage", "카테고리 ID가 제공되지 않았습니다.");
                response.sendRedirect("manage_categories.do");
                return;
            }
            
            int categoryId;
            try {
                categoryId = Integer.parseInt(categoryIdStr.trim());
                System.out.println("변환된 카테고리 ID 값: " + categoryId); // 디버깅 로그

                if (categoryId <= 0) {
                    System.out.println("카테고리 ID가 0 이하임"); // 디버깅 로그
                    request.getSession().setAttribute("errorMessage", "유효하지 않은 카테고리 ID입니다.");
                    response.sendRedirect("manage_categories.do");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("카테고리 ID 숫자 변환 실패: " + e.getMessage()); // 디버깅 로그
                request.getSession().setAttribute("errorMessage", "카테고리 ID가 올바른 숫자 형식이 아닙니다.");
                response.sendRedirect("manage_categories.do");
                return;
            }
            
            // 카테고리 DAO 생성
            CategoryDAO categoryDAO = new CategoryDAO();
            
            // 삭제하려는 카테고리 정보 조회
            CategoryDTO targetCategory = categoryDAO.getCategory(categoryId);
            System.out.println("조회된 카테고리: " + (targetCategory != null ? targetCategory.getCategoryName() : "없음")); // 디버깅 로그

            if (targetCategory == null) {
                request.getSession().setAttribute("errorMessage", "존재하지 않는 카테고리입니다.");
                response.sendRedirect("manage_categories.do");
                return;
            }

            // 하위 카테고리 존재 여부 확인
            List<CategoryDTO> subCategories = categoryDAO.getSubCategories(categoryId);
            if (!subCategories.isEmpty()) {
                request.getSession().setAttribute("errorMessage",
                    "이 카테고리에는 하위 카테고리가 존재합니다. 먼저 하위 카테고리를 삭제해주세요.");
                response.sendRedirect("manage_categories.do");
                return;
            }

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
            try {
                request.getSession().setAttribute("errorMessage", "시스템 오류가 발생했습니다. 다시 시도해주세요.");
                response.sendRedirect("manage_categories.do");
            } catch (IOException ex) {
                System.err.println("리다이렉트 중 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
