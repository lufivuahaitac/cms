/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.netbit.db;

/**
 *
 * @author Truong
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author Tran Ba Y
 */


public class ConnectionManager {

    private final static Logger logger = LogManager.getLogger(ConnectionManager.class);

    private ConnectionManager() {

    }

    private static final class SingletonHolder {

        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static DataSource datasource;

    public static DataSource getDataSource() {
        if (datasource == null) {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl("jdbc:mysql://158.69.199.45:3306/admin_phanmemtienich");
            config.setUsername("admin_anhnhackt5");
            config.setPassword("nobita92");

            config.setMaximumPoolSize(10);
            config.setAutoCommit(false);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            datasource = new HikariDataSource(config);
        }
        return datasource;
    }

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            DataSource dataSource = ConnectionManager.getDataSource();
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM an_posts");

            System.out.println("The Connection Object is of Class: " + connection.getClass());

            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("post_content") + "," + resultSet.getString(2) + "," + resultSet.getString(3));
            }

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
