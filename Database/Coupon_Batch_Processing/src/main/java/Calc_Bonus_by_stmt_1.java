import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calc_Bonus_by_stmt_1 {

    public static void main(String[] args) {

        // 1. 데이터베이스 연결 정보
        String dbUrl = "jdbc:oracle:thin:@192.168.119.119:1521:dinkdb";
        String dbUser = "scott";
        String dbPassword = "tiger";

        // 2. 시간 측정 시작
        long startTime = System.currentTimeMillis();
        System.out.println("보너스 쿠폰 지급 처리(stmt_1, 구조 반영) 시작...");

        int processedCount = 0;

        // try-with-resources 구문을 사용하여 리소스 자동 해제
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            conn.setAutoCommit(false);

            // 3. BONUS_COUPON 테이블 초기화 
            try (Statement truncateStmt = conn.createStatement()) {
                truncateStmt.executeUpdate("TRUNCATE TABLE BONUS_COUPON");
                System.out.println("BONUS_COUPON 테이블이 초기화되었습니다.");
            }

            // 4. 전체 고객 데이터 조회
            String selectSql = "SELECT ID, EMAIL, ENROLL_DT, CREDIT_LIMIT, GENDER, ADDRESS1 FROM CUSTOMER";

            try (Statement selectStmt = conn.createStatement();
                 ResultSet rs = selectStmt.executeQuery(selectSql)) {

                // 5. Java에서 데이터 필터링 및 쿠폰 계산
                while (rs.next()) {
                    String customerId = rs.getString("ID");
                    String email = rs.getString("EMAIL");
                    Date enrollDt = rs.getDate("ENROLL_DT");
                    int creditLimit = rs.getInt("CREDIT_LIMIT");
                    String gender = rs.getString("GENDER");
                    String address1 = rs.getString("ADDRESS1");

                    // 필터링 조건 1: 2018년 1월 1일 이후 가입 고객 
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date criteriaDate = sdf.parse("2018-01-01");

                    if (enrollDt != null && enrollDt.after(criteriaDate)) {

                        // 쿠폰 코드 계산 로직 
                        String couponCode = "";
                        if (creditLimit < 1000) {
                            couponCode = "AA";
                        } else if (creditLimit <= 2999) {
                            couponCode = "BB";
                        } else if (creditLimit <= 3999) {
                            // 특별 조건: GENDER가 'F'이고 주소에 '송파구 풍납 1동'이 포함된 경우 'C2'
                            // GENDER 컬럼이 'F'(Female), 'M'(Male) 등으로 저장되어 있다고 가정
                            boolean isSpecialCondition = "F".equalsIgnoreCase(gender) &&
                                    (address1 != null && address1.contains("송파구 풍납 1동"));

                            if (isSpecialCondition) {
                                couponCode = "C2";
                            } else {
                                couponCode = "CC";
                            }
                        } else {
                            couponCode = "DD";
                        }

                        // 6. BONUS_COUPON 테이블에 데이터 삽입 
                        // 루프 내에서 매번 새로운 Statement 객체 생성 (비효율적)
                        try (Statement insertStmt = conn.createStatement()) {
                            // BONUS_COUPON 테이블의 컬럼명은 과제 설명서 기준 
                            String insertSql = "INSERT INTO BONUS_COUPON (YYYYMM, CUSTOMER_ID, EMAIL, COUPON_CD, CREDIT_POINT, SEND_DT) " +
                                    "VALUES ('202506', '" + customerId + "', '" + email + "', '" + couponCode + "', " + creditLimit + ", NULL)";

                            insertStmt.executeUpdate(insertSql);
                        }

                        // 7. 1건 INSERT 후 바로 COMMIT (비효율적) 
                        conn.commit();
                        processedCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 8. 시간 측정 종료 및 결과 출력
        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000;

        System.out.println("보너스 쿠폰 지급 처리(stmt_1, 구조 반영) 종료.");
        System.out.println("총 " + processedCount + "명의 고객에게 쿠폰 정보가 생성되었습니다.");
        System.out.println("총 소요 시간: " + elapsedTime + "초");
    }
}