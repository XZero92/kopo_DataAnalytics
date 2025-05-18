package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryProductMappingDAO;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ECRemoveCategoryProductCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 매개변수 추출
            int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
            String productNo = request.getParameter("productNo");

            // 매핑 삭제 수행
            CategoryProductMappingDAO mappingDAO = new CategoryProductMappingDAO();
            boolean success = mappingDAO.deleteMapping(categoryNo, productNo);

            // JSON 응답 생성
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> result = new HashMap<>();
            result.put("success", success);
            if (!success) {
                result.put("error", "매핑 삭제 중 오류가 발생했습니다.");
            }

            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(result));
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("error", "요청 처리 중 오류가 발생했습니다.");
                response.getWriter().write(new Gson().toJson(error));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
