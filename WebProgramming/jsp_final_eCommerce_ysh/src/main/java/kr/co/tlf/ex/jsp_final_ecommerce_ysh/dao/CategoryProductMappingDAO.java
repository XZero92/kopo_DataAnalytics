package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao;

import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.CategoryProductMappingDTO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryProductMappingDAO {
    private DataSource dataSource;

    public CategoryProductMappingDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리에 속한 상품 매핑 목록 조회
    public List<CategoryProductMappingDTO> getMappingsByCategory(int categoryNo) {
        List<CategoryProductMappingDTO> mappings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NB_CATEGORY = ? ORDER BY CN_ORDER";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoryProductMappingDTO mapping = new CategoryProductMappingDTO();
                mapping.setCategoryNo(rs.getInt("NB_CATEGORY"));
                mapping.setProductNo(rs.getString("NO_PRODUCT"));
                mapping.setOrder(rs.getInt("CN_ORDER"));
                mapping.setRegisterNo(rs.getString("NO_REGISTER"));
                mapping.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                mappings.add(mapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappings;
    }

    // 상품에 연결된 카테고리 매핑 목록 조회
    public List<CategoryProductMappingDTO> getMappingsByProduct(String productNo) {
        List<CategoryProductMappingDTO> mappings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NO_PRODUCT = ? ORDER BY CN_ORDER";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productNo);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoryProductMappingDTO mapping = new CategoryProductMappingDTO();
                mapping.setCategoryNo(rs.getInt("NB_CATEGORY"));
                mapping.setProductNo(rs.getString("NO_PRODUCT"));
                mapping.setOrder(rs.getInt("CN_ORDER"));
                mapping.setRegisterNo(rs.getString("NO_REGISTER"));
                mapping.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                mappings.add(mapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappings;
    }

    // 카테고리와 상품 매핑 추가
    public boolean addMapping(CategoryProductMappingDTO mapping) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            String query = "INSERT INTO TB_CATEGORY_PRODUCT_MAPPING (NB_CATEGORY, NO_PRODUCT, CN_ORDER, NO_REGISTER, DA_FIRST_DATE) "
                    + "VALUES (?, ?, ?, ?, SYSDATE)";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, mapping.getCategoryNo());
            pstmt.setString(2, mapping.getProductNo());
            pstmt.setInt(3, mapping.getOrder());
            pstmt.setString(4, mapping.getRegisterNo());

            return pstmt.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 카테고리와 상품 매핑 삭제
    public boolean deleteMapping(int categoryNo, String productNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            String query = "DELETE FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NB_CATEGORY = ? AND NO_PRODUCT = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            pstmt.setString(2, productNo);

            return pstmt.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 카테고리 내 상품 순서 업데이트
    public boolean updateOrder(int categoryNo, String productNo, int newOrder) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            String query = "UPDATE TB_CATEGORY_PRODUCT_MAPPING SET CN_ORDER = ? WHERE NB_CATEGORY = ? AND NO_PRODUCT = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newOrder);
            pstmt.setInt(2, categoryNo);
            pstmt.setString(3, productNo);

            return pstmt.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 다음 순서 값 가져오기
    public int getNextOrder(int categoryNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nextOrder = 1;

        try {
            conn = dataSource.getConnection();
            String query = "SELECT NVL(MAX(CN_ORDER), 0) + 1 FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NB_CATEGORY = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                nextOrder = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nextOrder;
    }

    // 대량 매핑 추가
    public boolean addMappings(int categoryNo, List<String> productNos, String registerNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = true;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);  // 트랜잭션 시작

            String query = "INSERT INTO TB_CATEGORY_PRODUCT_MAPPING (NB_CATEGORY, NO_PRODUCT, CN_ORDER, NO_REGISTER, DA_FIRST_DATE) "
                    + "VALUES (?, ?, ?, ?, SYSDATE)";

            pstmt = conn.prepareStatement(query);
            int nextOrder = getNextOrder(categoryNo);

            for (String productNo : productNos) {
                pstmt.setInt(1, categoryNo);
                pstmt.setString(2, productNo);
                pstmt.setInt(3, nextOrder++);
                pstmt.setString(4, registerNo);
                pstmt.addBatch();
            }

            int[] results = pstmt.executeBatch();
            for (int result : results) {
                if (result != 1) {
                    success = false;
                    break;
                }
            }

            if (success) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    // 카테고리별 상품 수 조회
    public int getProductCount(int categoryNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = dataSource.getConnection();
            String query = "SELECT COUNT(*) FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NB_CATEGORY = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    // 매핑 존재 여부 확인
    public boolean existsMapping(int categoryNo, String productNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = dataSource.getConnection();
            String query = "SELECT COUNT(*) FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NB_CATEGORY = ? AND NO_PRODUCT = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            pstmt.setString(2, productNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return exists;
    }
}
