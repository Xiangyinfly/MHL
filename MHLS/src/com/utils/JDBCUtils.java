package com.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public class JDBCUtils {
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static DataSource dataSource = null;
    static {
        Properties properties = new Properties();
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            tl.set(connection);
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();
        }

    }
}
