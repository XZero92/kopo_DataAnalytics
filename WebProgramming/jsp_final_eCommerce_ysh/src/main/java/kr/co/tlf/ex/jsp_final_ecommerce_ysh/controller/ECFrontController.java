package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.*;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.product.*;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user.*;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.*;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.user.*;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category.*;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.product.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 모든 요청을 중앙에서 처리하는 프론트 컨트롤러 서블릿
 * URL 패턴 *.do에 매핑되어 있어 .do로 끝나는 모든 요청을 처리
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 20 // 20MB
)

@WebServlet("*.do")
public class ECFrontController extends HttpServlet {
    
    // 커맨드 객체를 저장하는 맵
    private Map<String, ECCommand> commandMap = new HashMap<>();
    
    /**
     * 서블릿 초기화 메서드
     * 각 URL 패턴에 해당하는 커맨드 객체를 맵에 등록
     */
    @Override
    public void init() throws ServletException {

        // 메인 페이지
        commandMap.put("/main.do", new ECMainCommand());

        // 기존 매핑
        commandMap.put("/register.do", new ECRegisterCommand());
        commandMap.put("/login.do", new ECLoginCommand());
        commandMap.put("/logout.do", new ECLogoutCommand());
        commandMap.put("/unregister.do", new ECUnregisterCommand());

        // 마이페이지
        commandMap.put("/mypage.do", new ECMyPageCommand());
        commandMap.put("/update_profile.do", new ECUpdateProfileCommand());
        commandMap.put("/change_password.do", new ECChangePasswordCommand());
        /*commandMap.put("/order_history.do", new ECOrderHistoryCommand());*/
        commandMap.put("/unregister.do", new ECUnregisterCommand());

        // 관리자 페이지
        commandMap.put("/adminPage.do", new ECAdminPageCommand());
        // 사용자 관리
        commandMap.put("/manage_users.do", new ECManageUsersCommand());
        commandMap.put("/approve_user.do", new ECApproveUserCommand());
        commandMap.put("/reject_user.do", new ECRejectUserCommand());
        commandMap.put("/delete_user.do", new ECDeleteUserCommand());
        commandMap.put("/edit_user.do", new ECEditUserCommand());
        commandMap.put("/update_user_status.do", new ECUpdateUserStatusCommand());
        commandMap.put("/update_user_type.do", new ECUpdateUserTypeCommand());
        // 카테고리 관리
        commandMap.put("/manage_categories.do", new ECManageCategoriesCommand());
        commandMap.put("/add_category.do", new ECAddCategoryCommand());
        commandMap.put("/update_category.do", new ECUpdateCategoryCommand());
        commandMap.put("/delete_category.do", new ECDeleteCategoryCommand());
        commandMap.put("/update_category_order.do", new ECUpdateCategoryOrderCommand());
        // 상품 관리
        commandMap.put("/manage_products.do", new ECManageProductsCommand());
        commandMap.put("/add_product.do", new ECAddProductCommand());
        commandMap.put("/update_product.do", new ECUpdateProductCommand());
        commandMap.put("/delete_product.do", new ECDeleteProductCommand());

        // 카테고리-상품 매핑 관리
        commandMap.put("/manage_category_mapping.do", new ECManageCategoryMappingCommand());
        commandMap.put("/get_category_products.do", new ECGetCategoryProductsCommand());
        commandMap.put("/remove_category_product.do", new ECRemoveCategoryProductCommand());
        commandMap.put("/search_products.do", new ECSearchProductsCommand());
        commandMap.put("/add_category_products.do", new ECAddCategoryProductsCommand());

        // 쇼핑몰
        commandMap.put("/product_list.do", new ECProductListCommand());
        commandMap.put("/product_detail.do", new ECProductDetailCommand());

        // 범용 커맨드
        commandMap.put("/getImage.do", new ECGetImageCommand());
    }
    
    /**
     * GET 요청 처리
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    /**
     * POST 요청 처리
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 요청 및 응답 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 클라이언트가 요청한 URI에서 컨텍스트 경로를 제외한 명령어 추출
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        System.out.println("요청 URI: " + requestURI);
        System.out.println("컨텍스트 경로: " + contextPath);
        System.out.println("실행할 명령어: " + command);

        // 요청에 해당하는 커맨드 객체 가져오기
        ECCommand cmd = commandMap.get(command);

        // 커맨드 객체가 존재하면 실행
        if (cmd != null) {
            try {
                // 커맨드 객체에게 요청 처리 위임
                cmd.execute(request, response);
            } catch (Exception e) {
                // 예외 발생 시 에러 로그 출력
                System.out.println("명령어 실행 중 오류 발생: " + command);
                e.printStackTrace();

                // 에러 메시지를 request에 설정하고 에러 페이지로 포워딩
                request.setAttribute("errorMessage", "요청을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } else {
            // 요청에 해당하는 커맨드가 없는 경우 404 페이지로 이동
            System.out.println("명령어를 찾을 수 없음: " + command);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 페이지를 찾을 수 없습니다.");
        }
    }
}

