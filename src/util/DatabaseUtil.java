package com.hbase.test.servlet.jdbcutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接与关闭工具类。
 */
public class DatabaseUtil {
	
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/newsdatabase";
    private static final String USER = "root";
    private static final String PASS = "123456";
    
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象。
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        // 获取连接并捕获异常
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return conn;// 返回连接对象
    }

    /**
     * 关闭数据库连接。
     * 
     * @param conn
     *            数据库连接
     * @param stmt
     *            Statement对象
     * @param rs
     *            结果集
     */
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        // 若结果集对象不为空，则关闭
        try {
            if (rs != null && !rs.isClosed())
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 若Statement对象不为空，则关闭
        try {
            if (stmt != null && !stmt.isClosed())
                stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 若数据库连接对象不为空，则关闭
        try {
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
