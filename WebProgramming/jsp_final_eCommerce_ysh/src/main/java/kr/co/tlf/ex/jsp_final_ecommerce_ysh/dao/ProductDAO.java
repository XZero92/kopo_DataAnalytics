package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao;

import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.ProductDTO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DataSource dataSource;
    
    public ProductDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 모든 상품 조회
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_PRODUCT ORDER BY NO_PRODUCT";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductNo(rs.getString("NO_PRODUCT"));
                product.setProductName(rs.getString("NM_PRODUCT"));
                product.setDetailExplain(rs.getString("NM_DETAIL_EXPLAIN"));
                product.setFileId(rs.getString("ID_FILE"));
                product.setStartDate(rs.getString("DT_START_DATE"));
                product.setEndDate(rs.getString("DT_END_DATE"));
                product.setCustomerQuantity(rs.getInt("QT_CUSTOMER"));
                product.setSalePrice(rs.getInt("QT_SALE_PRICE"));
                product.setStockQuantity(rs.getInt("QT_STOCK"));
                product.setDeliveryFee(rs.getInt("QT_DELIVERY_FEE"));
                product.setRegisterNo(rs.getString("NO_REGISTER"));
                product.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                
                products.add(product);
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
        
        return products;
    }
    
    // 특정 카테고리의 상품 조회
    public List<ProductDTO> getProductsByCategory(int categoryNo) {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT p.* FROM TB_PRODUCT p " +
                    "JOIN PRODUCT_CATEGORY pc ON p.NO_PRODUCT = pc.NO_PRODUCT " +
                    "WHERE pc.CATEGORY_NO = ? " +
                    "ORDER BY p.NO_PRODUCT";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryNo);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductNo(rs.getString("NO_PRODUCT"));
                product.setProductName(rs.getString("NM_PRODUCT"));
                product.setDetailExplain(rs.getString("NM_DETAIL_EXPLAIN"));
                product.setFileId(rs.getString("ID_FILE"));
                product.setStartDate(rs.getString("DT_START_DATE"));
                product.setEndDate(rs.getString("DT_END_DATE"));
                product.setCustomerQuantity(rs.getInt("QT_CUSTOMER"));
                product.setSalePrice(rs.getInt("QT_SALE_PRICE"));
                product.setStockQuantity(rs.getInt("QT_STOCK"));
                product.setDeliveryFee(rs.getInt("QT_DELIVERY_FEE"));
                product.setRegisterNo(rs.getString("NO_REGISTER"));
                product.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
                
                products.add(product);
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
        
        return products;
    }
    
    // 특정 상품 조회
    public ProductDTO getProduct(String productNo) {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_PRODUCT WHERE NO_PRODUCT = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productNo);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                product = new ProductDTO();
                product.setProductNo(rs.getString("NO_PRODUCT"));
                product.setProductName(rs.getString("NM_PRODUCT"));
                product.setDetailExplain(rs.getString("NM_DETAIL_EXPLAIN"));
                product.setFileId(rs.getString("ID_FILE"));
                product.setStartDate(rs.getString("DT_START_DATE"));
                product.setEndDate(rs.getString("DT_END_DATE"));
                product.setCustomerQuantity(rs.getInt("QT_CUSTOMER"));
                product.setSalePrice(rs.getInt("QT_SALE_PRICE"));
                product.setStockQuantity(rs.getInt("QT_STOCK"));
                product.setDeliveryFee(rs.getInt("QT_DELIVERY_FEE"));
                product.setRegisterNo(rs.getString("NO_REGISTER"));
                product.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));
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
        
        return product;
    }
    
    // 상품 추가
    public boolean addProduct(ProductDTO product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "INSERT INTO TB_PRODUCT (NO_PRODUCT, NM_PRODUCT, NM_DETAIL_EXPLAIN, ID_FILE, " +
                    "DT_START_DATE, DT_END_DATE, QT_CUSTOMER, QT_SALE_PRICE, QT_STOCK, " +
                    "QT_DELIVERY_FEE, NO_REGISTER, DA_FIRST_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, product.getProductNo());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getDetailExplain());
            pstmt.setString(4, product.getFileId());
            pstmt.setString(5, product.getStartDate());
            pstmt.setString(6, product.getEndDate());
            pstmt.setInt(7, product.getCustomerQuantity());
            pstmt.setInt(8, product.getSalePrice());
            pstmt.setInt(9, product.getStockQuantity());
            pstmt.setInt(10, product.getDeliveryFee());
            pstmt.setString(11, product.getRegisterNo());
            
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
    
    // 상품 수정
    public boolean updateProduct(ProductDTO product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "UPDATE TB_PRODUCT SET NM_PRODUCT = ?, NM_DETAIL_EXPLAIN = ?, ID_FILE = ?, " +
                    "DT_START_DATE = ?, DT_END_DATE = ?, QT_CUSTOMER = ?, QT_SALE_PRICE = ?, " +
                    "QT_STOCK = ?, QT_DELIVERY_FEE = ? " +
                    "WHERE NO_PRODUCT = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDetailExplain());
            pstmt.setString(3, product.getFileId());
            pstmt.setString(4, product.getStartDate());
            pstmt.setString(5, product.getEndDate());
            pstmt.setInt(6, product.getCustomerQuantity());
            pstmt.setInt(7, product.getSalePrice());
            pstmt.setInt(8, product.getStockQuantity());
            pstmt.setInt(9, product.getDeliveryFee());
            pstmt.setString(10, product.getProductNo());
            
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
    
    // 상품 삭제
    public boolean deleteProduct(String productNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "DELETE FROM TB_PRODUCT WHERE NO_PRODUCT = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productNo);
            
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
    
    // 새 상품 번호 생성 (접두사 P + 5자리 숫자)
    public String generateProductNo() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String newProductNo = "P00001";
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT MAX(TO_NUMBER(SUBSTR(NO_PRODUCT, 2))) + 1 FROM TB_PRODUCT";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int nextNo = rs.getInt(1);
                if (nextNo <= 0) {
                    nextNo = 1;
                }
                newProductNo = "P" + String.format("%05d", nextNo);
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
        
        return newProductNo;
    }
    
    // 상품과 카테고리 연결
    public boolean addProductToCategory(String productNo, int categoryNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "INSERT INTO PRODUCT_CATEGORY (NO_PRODUCT, CATEGORY_NO) VALUES (?, ?)";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productNo);
            pstmt.setInt(2, categoryNo);
            
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
    
    // 상품의 모든 카테고리 제거
    public boolean removeAllProductCategories(String productNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "DELETE FROM PRODUCT_CATEGORY WHERE NO_PRODUCT = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productNo);
            
            pstmt.executeUpdate();
            return true;
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
    
    // 제품에 연결된 카테고리 목록 조회
    public List<Integer> getProductCategories(String productNo) {
        List<Integer> categoryIds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dataSource.getConnection();
            String query = "SELECT CATEGORY_NO FROM PRODUCT_CATEGORY WHERE NO_PRODUCT = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productNo);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                categoryIds.add(rs.getInt("CATEGORY_NO"));
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
        
        return categoryIds;
    }

    // 상품명 기반 검색
    public List<ProductDTO> searchProducts(String searchTerm) {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM TB_PRODUCT WHERE NM_PRODUCT LIKE ? ORDER BY NO_PRODUCT";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + searchTerm + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductNo(rs.getString("NO_PRODUCT"));
                product.setProductName(rs.getString("NM_PRODUCT"));
                product.setDetailExplain(rs.getString("NM_DETAIL_EXPLAIN"));
                product.setFileId(rs.getString("ID_FILE"));
                product.setStartDate(rs.getString("DT_START_DATE"));
                product.setEndDate(rs.getString("DT_END_DATE"));
                product.setCustomerQuantity(rs.getInt("QT_CUSTOMER"));
                product.setSalePrice(rs.getInt("QT_SALE_PRICE"));
                product.setStockQuantity(rs.getInt("QT_STOCK"));
                product.setDeliveryFee(rs.getInt("QT_DELIVERY_FEE"));
                product.setRegisterNo(rs.getString("NO_REGISTER"));
                product.setFirstDate(rs.getTimestamp("DA_FIRST_DATE"));

                products.add(product);
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

        return products;
    }
}

