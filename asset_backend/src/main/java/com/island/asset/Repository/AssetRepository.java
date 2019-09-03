package com.island.asset.Repository;

import com.island.asset.domain.Asset;
import com.island.asset.domain.AttachDoc;
import com.island.asset.handler.FileUtilsHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.*;

@Repository
public class AssetRepository implements IAssetRepository {
	
	@Autowired
    private FileUtilsHandler fileUtils;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.data-username}")
    private String username;
    @Value("${spring.datasource.data-password}")
    private String password;

    @SuppressWarnings("resource")
	@Override
    public Asset regAsset(Asset asset) {
    	
        Connection con = null;
        CallableStatement cstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
            cstmt = con.prepareCall("{?= call regAsset(?, ?, ?, ?, ?, ?, ?, ?)}");

            cstmt.setString(2, asset.getStatus());
            cstmt.setString(3, asset.getAssetType());
            cstmt.setString(4, asset.getDesc());
            cstmt.setString(5, asset.getLocation());
            cstmt.setString(6, asset.getAssetNoParent());
            cstmt.setString(7, asset.getAssignee());
            cstmt.setInt(8, asset.getAmount());
            cstmt.setString(9, asset.getLmUser());
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.execute();

            String newAssetNo = cstmt.getString(1);
            System.out.println("AssetNo:" + newAssetNo);
            cstmt = con.prepareCall("{call add_attach_doc(?, ?, ?, ?, ?, ?)}");
            List<AttachDoc> list = new ArrayList<>();
            list = asset.getAttachDocList();

            for(AttachDoc att: list){
                cstmt.setString(1, newAssetNo);

                if(att.getSeq() != 0) {
                    cstmt.setInt(2, att.getSeq());
                }

                cstmt.setString(3, att.getDocName());
                cstmt.setString(4, att.getPath());
                cstmt.setString(5, att.getDesc());
                cstmt.setString(6, asset.getLmUser());
                cstmt.addBatch();
            }
            cstmt.executeBatch();
            
            con.commit();
            
            asset.setAssetNo(newAssetNo);
 
        } catch(SQLException e) {

            try {
                con.rollback();
                System.out.println("rollback");
                
                fileUtils.deleteAttachDocList(asset.getAttachDocList());
                
                throw new SQLException("SQL error. " + e.getMessage());
            } catch (Exception se) {
                throw new RuntimeException("A database error occured. " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        }
        finally {
            closeResource(con, cstmt, null);
        }
        
        return asset;
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

}
