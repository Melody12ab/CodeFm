package com.MemAndMybatis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by xiaobai on 15-11-10.
 */
public class JDBCUtils {
    private final static String DRIVER="com.mysql.jdbc.Driver";
    private final static String URL="jdbc:mysql://127.0.0.1:3306/test";
    private final static String NAME="root";
    private final static String PASS="123456";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(URL,NAME,PASS);
        return connection;
    }
}
