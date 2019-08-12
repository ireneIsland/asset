package com.island.asset.Repository;

import com.island.asset.domain.Asset;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.*;

@Repository
public class AssetRepository implements IAssetRepository {

    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String username = "TEST1";
    private static final String password = "12345";

    private static final String INSERT_STMT =
            "{call regAsset(?, ?, ?, ?, ?, ?, ?, ?)}";


    @Override
    public void regAsset(Asset asset) {

        Connection con = null;
        CallableStatement cstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            cstmt = con.prepareCall(INSERT_STMT);

            cstmt.setString(1, asset.getStatus());
            cstmt.setString(2, asset.getAssetType());
            cstmt.setString(3, asset.getDesc());
            cstmt.setString(4, asset.getLocation());
            cstmt.setString(5, asset.getAssetNoParent());
            cstmt.setString(6, asset.getAssignee());
            cstmt.setInt(7, asset.getAmount());
            cstmt.setString(8, asset.getLmUser());
            cstmt.execute();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            closeResource(con, cstmt, null);
        }
    }

    @Override
    public List<Asset> findAll() {
        return null;
    }

    @Override
    public Asset findOne() {
        return null;
    }


    //	關閉資源
    public void closeResource(Connection con, CallableStatement cstmt, ResultSet rs) {

        if (cstmt != null) {
            try {
                cstmt.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }



    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // TEST ORALE
//    public static void main(String[] args) {
//        Connection connection = AssetDao.getConnection();
//        System.out.println("TEST DONE!!!：" + connection);
//        AssetDao Repository = new AssetDao();
//        Repository.regAsset(null);
//
//    }

}
