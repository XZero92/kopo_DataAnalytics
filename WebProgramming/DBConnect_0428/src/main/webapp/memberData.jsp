<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>

<%
  Connection connection = null;
  Statement statement = null;
  ResultSet resultSet = null;

  String driver = "oracle.jdbc.OracleDriver";
  String url = "jdbc:oracle:thin:@//192.168.119.119:1521/dinkdb";
  String uid = "scott";
  String upw = "tiger";
  String query = "SELECT * FROM EMP";
  String createTableQuery = "CREATE TABLE MEMBERZ (" +
                            "ID VARCHAR2(20), " +
                            "PW VARCHAR2(20), " +
                            "NAME VARCHAR2(20), " +
                            "PHONE VARCHAR2(20))";
  String insertQuery = "INSERT INTO MEMBERZ (ID, PW, NAME, PHONE) VALUES ('abc', '123', '홍길동', '010-1234-5678')";
%>

<%
    try {
        // JDBC 드라이버 로드
        Class.forName(driver);
        // DB 연결
        connection = DriverManager.getConnection(url, uid, upw);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String empno = resultSet.getString("EMPNO");
            String ename = resultSet.getString("ENAME");
            String job = resultSet.getString("JOB");

            out.println("사원번호: " + empno + ", 이름: " + ename + ", 직무: " + job + "<br>");
        }
    } catch (ClassNotFoundException e) {
        out.println("JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
    } catch (SQLException e) {
        out.println("SQL 오류: " + e.getMessage());
    } finally {
      // 리소스 정리
      if (resultSet != null) try { resultSet.close(); } catch (SQLException ignored) {}
      if (statement != null) try { statement.close(); } catch (SQLException ignored) {}
      if (connection != null) try { connection.close(); } catch (SQLException ignored) {}
    }
%>