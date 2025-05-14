package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao;

import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.CategoryDTO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private DataSource dataSource;
    
    public CategoryDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 모든 카테고리 조회 (사용 중이고 삭제되지 않은 카테고리)
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_CATEGORY WHERE YN_USE = 'Y' AND YN_DELETE = 'N' ORDER BY CN_LEVEL, CN_ORDER";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCategoryNo(rs.getInt("NB_CATEGORY"));
                category.setParentCategoryNo(rs.getInt("NB_PARENT_CATEGORY"));
                category.setCategoryName(rs.getString("NM_CATEGORY"));
                category.setFullCategoryName(rs.getString("NM_FULL_CATEGORY"));
                category.setExplain(rs.getString("NM_EXPLAIN"));
                category.setLevel(rs.getInt("CN_LEVEL"));
                category.setOrder(rs.getInt("CN_ORDER"));
                category.setUseYn(rs.getString("YN_USE"));
                category.setDeleteYn(rs.getString("YN_DELETE"));
                category.setRegisterNo(rs.getString("NO_REGISTER"));
                category.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                
                categories.add(category);
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
        
        return categories;
    }
    
    // 특정 부모 카테고리에 속한 하위 카테고리 조회
    public List<CategoryDTO> getSubCategories(int parentCategoryNo) {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_CATEGORY WHERE NB_PARENT_CATEGORY = ? AND YN_USE = 'Y' AND YN_DELETE = 'N' ORDER BY CN_ORDER";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, parentCategoryNo);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCategoryNo(rs.getInt("NB_CATEGORY"));
                category.setParentCategoryNo(rs.getInt("NB_PARENT_CATEGORY"));
                category.setCategoryName(rs.getString("NM_CATEGORY"));
                category.setFullCategoryName(rs.getString("NM_FULL_CATEGORY"));
                category.setExplain(rs.getString("NM_EXPLAIN"));
                category.setLevel(rs.getInt("CN_LEVEL"));
                category.setOrder(rs.getInt("CN_ORDER"));
                category.setUseYn(rs.getString("YN_USE"));
                category.setDeleteYn(rs.getString("YN_DELETE"));
                category.setRegisterNo(rs.getString("NO_REGISTER"));
                category.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                
                categories.add(category);
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
        
        return categories;
    }
    
    // 특정 카테고리 조회
    public CategoryDTO getCategory(int categoryNo) {
        CategoryDTO category = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_CATEGORY WHERE NB_CATEGORY = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                category = new CategoryDTO();
                category.setCategoryNo(rs.getInt("NB_CATEGORY"));
                category.setParentCategoryNo(rs.getInt("NB_PARENT_CATEGORY"));
                category.setCategoryName(rs.getString("NM_CATEGORY"));
                category.setFullCategoryName(rs.getString("NM_FULL_CATEGORY"));
                category.setExplain(rs.getString("NM_EXPLAIN"));
                category.setLevel(rs.getInt("CN_LEVEL"));
                category.setOrder(rs.getInt("CN_ORDER"));
                category.setUseYn(rs.getString("YN_USE"));
                category.setDeleteYn(rs.getString("YN_DELETE"));
                category.setRegisterNo(rs.getString("NO_REGISTER"));
                category.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
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
        
        return category;
    }
    
    // 카테고리 추가
    public boolean addCategory(CategoryDTO category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "INSERT INTO TB_CATEGORY (NB_CATEGORY, NB_PARENT_CATEGORY, NM_CATEGORY, NM_FULL_CATEGORY, " +
                    "NM_EXPLAIN, CN_LEVEL, CN_ORDER, YN_USE, YN_DELETE, NO_REGISTER, DA_FIRST_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, category.getCategoryNo());
            if(category.getParentCategoryNo() == 0) {
                pstmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(2, category.getParentCategoryNo());
            }
            pstmt.setString(3, category.getCategoryName());
            pstmt.setString(4, category.getFullCategoryName());
            pstmt.setString(5, category.getExplain());
            pstmt.setInt(6, category.getLevel());
            pstmt.setInt(7, category.getOrder());
            pstmt.setString(8, category.getUseYn());
            pstmt.setString(9, category.getDeleteYn());
            pstmt.setString(10, category.getRegisterNo());
            
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
    
    // 카테고리 수정
    public boolean updateCategory(CategoryDTO category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "UPDATE TB_CATEGORY SET NB_PARENT_CATEGORY = ?, NM_CATEGORY = ?, NM_FULL_CATEGORY = ?, " +
                    "NM_EXPLAIN = ?, CN_LEVEL = ?, CN_ORDER = ?, YN_USE = ?, YN_DELETE = ? " +
                    "WHERE NB_CATEGORY = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, category.getParentCategoryNo());
            pstmt.setString(2, category.getCategoryName());
            pstmt.setString(3, category.getFullCategoryName());
            pstmt.setString(4, category.getExplain());
            pstmt.setInt(5, category.getLevel());
            pstmt.setInt(6, category.getOrder());
            pstmt.setString(7, category.getUseYn());
            pstmt.setString(8, category.getDeleteYn());
            pstmt.setInt(9, category.getCategoryNo());
            
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

    
    // 카테고리 논리적 삭제 (YN_DELETE = 'Y'로 설정)
    public boolean deleteCategory(int categoryNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "UPDATE TB_CATEGORY SET YN_DELETE = 'Y' WHERE NB_CATEGORY = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            
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
    
    // 새 카테고리 번호 생성 (시퀀스 또는 최대값 + 1)
    public int getNextCategoryNo() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nextNo = 1;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT MAX(NB_CATEGORY) + 1 FROM TB_CATEGORY";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                nextNo = rs.getInt(1);
                if (nextNo <= 0) {
                    nextNo = 1;
                }
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
        
        return nextNo;
    }
    
    // 카테고리의 전체 경로 이름 생성 (상위 카테고리 이름을 포함)
    public String generateFullCategoryName(int parentCategoryNo, String categoryName) {
        if (parentCategoryNo == 0) {
            return categoryName;
        }
        
        CategoryDTO parentCategory = getCategory(parentCategoryNo);
        if (parentCategory != null) {
            return parentCategory.getFullCategoryName() + " > " + categoryName;
        } else {
            return categoryName;
        }
    }
}