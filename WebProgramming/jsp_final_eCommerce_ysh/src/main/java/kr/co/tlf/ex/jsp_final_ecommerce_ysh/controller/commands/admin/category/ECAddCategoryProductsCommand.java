package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryProductMappingDAO;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECAddCategoryProductsCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // JSON 요청 본문 읽기
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // JSON 파싱
            Gson gson = new Gson();
            JsonObject jsonRequest = gson.fromJson(sb.toString(), JsonObject.class);
            int categoryNo = jsonRequest.get("categoryNo").getAsInt();
            List<String> productNos = gson.fromJson(
                jsonRequest.get("productNos"),
                new TypeToken<List<String>>(){}.getType()
            );

            // 세션에서 등록자 정보 가져오기
            String registerNo = (String) request.getSession().getAttribute("userNo");

            // 매핑 추가 수행
            CategoryProductMappingDAO mappingDAO = new CategoryProductMappingDAO();
            boolean success = mappingDAO.addMappings(categoryNo, productNos, registerNo);

            // JSON 응답 생성
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> result = new HashMap<>();
            result.put("success", success);
            if (!success) {
                result.put("error", "상품 매핑 추가 중 오류가 발생했습니다.");
            }

            response.getWriter().write(gson.toJson(result));

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
