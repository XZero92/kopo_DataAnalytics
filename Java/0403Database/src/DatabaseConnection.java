import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String dbUrl = "jdbc:oracle:thin@localhost:1521/orcl";
            String dbUser = "oracletest";
            String dbPaswd = "oraclepaswd";

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPaswd);
            System.out.println("연결 성공");

            String sql = "INSERT INTO USERS(ID_USER, NM_USER, ID_PASWD, NB_AGE, ID_EMAIL)"
                    + "VALUE(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "test1");
            pstmt.setString(2, "홍길동");
            pstmt.setString(3, "test3");
            pstmt.setInt(4, 25);
            pstmt.setString(5, "test5@test.com");

            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수: " + rows);

            pstmt.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
