package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.admin.category;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.CategoryDAO;

import java.io.IOException;
import java.util.*;

public class ECUpdateCategoryOrderCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String[] categoryIds = request.getParameterValues("categoryId");
        String[] orders = request.getParameterValues("order");
        String[] levels = request.getParameterValues("level");
        String[] parents = request.getParameterValues("parent");

        CategoryDAO categoryDAO = new CategoryDAO();
        boolean allSuccess = true;

        try {
            if (categoryIds != null && orders != null && levels != null && parents != null &&
                categoryIds.length == orders.length && orders.length == levels.length && levels.length == parents.length) {

                // 데이터를 정렬 가능한 객체로 변환
                List<CategoryOrder> categoryOrders = new ArrayList<>();
                for (int i = 0; i < categoryIds.length; i++) {
                    categoryOrders.add(new CategoryOrder(
                        Integer.parseInt(categoryIds[i]),
                        Integer.parseInt(orders[i]),
                        Integer.parseInt(levels[i]),
                        Integer.parseInt(parents[i])
                    ));
                }

                // 부모 카테고리별로 그룹화
                Map<Integer, List<CategoryOrder>> groupedCategories = new HashMap<>();
                for (CategoryOrder co : categoryOrders) {
                    groupedCategories.computeIfAbsent(co.parentId, k -> new ArrayList<>()).add(co);
                }

                // 각 그룹 내에서 순서 업데이트
                int globalOrder = 1;
                for (CategoryOrder topLevel : categoryOrders.stream()
                        .filter(co -> co.level == 1)
                        .sorted(Comparator.comparingInt(co -> co.order))
                        .toList()) {

                    // 대분류 순서 업데이트
                    allSuccess &= categoryDAO.updateCategoryOrder(topLevel.categoryId, globalOrder++);

                    // 중분류 처리
                    List<CategoryOrder> children = groupedCategories.getOrDefault(topLevel.categoryId, new ArrayList<>());
                    for (CategoryOrder child : children.stream()
                            .sorted(Comparator.comparingInt(co -> co.order))
                            .toList()) {

                        // 중분류 순서 업데이트
                        allSuccess &= categoryDAO.updateCategoryOrder(child.categoryId, globalOrder++);

                        // 소분류 처리
                        List<CategoryOrder> grandChildren = groupedCategories.getOrDefault(child.categoryId, new ArrayList<>());
                        for (CategoryOrder grandChild : grandChildren.stream()
                                .sorted(Comparator.comparingInt(co -> co.order))
                                .toList()) {
                            // 소분류 순서 업데이트
                            allSuccess &= categoryDAO.updateCategoryOrder(grandChild.categoryId, globalOrder++);
                        }
                    }
                }
            }

            if (allSuccess) {
                request.getSession().setAttribute("message", "카테고리 순서가 성공적으로 업데이트되었습니다.");
            } else {
                request.getSession().setAttribute("errorMessage", "순서 업데이트 중 오류가 발생했습니다.");
            }
            response.sendRedirect("manage_categories.do");

        } catch (Exception e) {
            System.err.println("카테고리 순서 업데이트 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            try {
                request.getSession().setAttribute("errorMessage", "순서 업데이트 중 오류가 발생했습니다.");
                response.sendRedirect("manage_categories.do");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class CategoryOrder {
        final int categoryId;
        final int order;
        final int level;
        final int parentId;

        CategoryOrder(int categoryId, int order, int level, int parentId) {
            this.categoryId = categoryId;
            this.order = order;
            this.level = level;
            this.parentId = parentId;
        }
    }
}

