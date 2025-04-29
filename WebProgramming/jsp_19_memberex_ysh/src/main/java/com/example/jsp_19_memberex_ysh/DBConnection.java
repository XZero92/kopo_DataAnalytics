package com.example.jsp_19_memberex_ysh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String driver = "oracle.jdbc.driver.OracleDriver";
    // 가상 머신 DB
    /*private String url = "jdbc:oracle:thin:@//192.168.119.119:1521/dinkdb";
    private String uid = "scott";
    private String upw = "tiger";*/

    // 데이터분석과 서버실 DB
    private String url = "jdbc:oracle:thin:@//192.168.217.202:1521/KOPODA";
    private String uid = "da2509";
    private String upw = "da09";

    // 클라우드 DB (접근 실패)
    /*private String url = "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=C://Users/kopo/Downloads/Wallet_DinkDB";
    private String uid = "DA2509";
    private String upw = "Data2509";*/

    private Connection connection = null;

    public DBConnection() {

    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, uid, upw);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }
    public void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpw() {
        return upw;
    }
    public void setUpw(String upw) {
        this.upw = upw;
    }
}
