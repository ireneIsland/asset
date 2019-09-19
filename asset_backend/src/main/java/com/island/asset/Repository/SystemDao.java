package com.island.asset.Repository;

import com.island.asset.domain.SystemConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SystemDao implements ISystemDao{

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.data-username}")
    private String username;
    @Value("${spring.datasource.data-password}")
    private String password;

    @Override
    public String addSystemParam(SystemConfig systemConfig) {
        return null;
    }

    @Override
    public String moddifySystemParam(SystemConfig systemConfig) {
        return null;
    }

    @Override
    public String deleteSystemParam(String sysItemID, int req) {
        return null;
    }

    /**
     * 取得全部系統參數
     * @return
     */
    @Override
    public Map<String,Object> getAllSystemParam() {

        List<SystemConfig> assetTypeList = new ArrayList<SystemConfig>();
        List<SystemConfig> retirementTypeList = new ArrayList<SystemConfig>();
        List<SystemConfig> statusList = new ArrayList<SystemConfig>();
        Map<String,Object> map = new HashMap<String,Object>();
        SystemConfig sysConfig = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = DriverManager.getConnection(url, username, password);
            pstmt = con.prepareStatement("SELECT * FROM SYSTEM_CONFIG");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sysConfig = new SystemConfig();
                sysConfig.setSysItemId(rs.getString("sys_item_id"));
                sysConfig.setSysItemName(rs.getString("sys_item_name"));
                sysConfig.setSeq(rs.getInt("seq"));
                sysConfig.setSysItemValue1(rs.getString("sys_item_value1"));
                sysConfig.setSysItemValue2(rs.getString("sys_item_value2"));
                sysConfig.setSysItemValue3(rs.getString("sys_item_value3"));
                sysConfig.setLmTime(rs.getDate("lm_time"));
                sysConfig.setLmUser(rs.getString("lm_user"));

                if ("asset_type".equals(sysConfig.getSysItemId())){
                	assetTypeList.add(sysConfig);
                }

                if ("retirement_type".equals(sysConfig.getSysItemId())){
                	retirementTypeList.add(sysConfig);
                }
                
                if ("status".equals(sysConfig.getSysItemId())){
                	statusList.add(sysConfig);
                }

                if ("asset_no_end".equals(sysConfig.getSysItemId())){
                    map.put("assetNoEnd", sysConfig);
                }

                if ("assignee".equals(sysConfig.getSysItemId())){
                    map.put("assignee", sysConfig);
                }

                if ("location".equals(sysConfig.getSysItemId())){
                    map.put("location", sysConfig);
                }
                
                
            }

            map.put("assetType", assetTypeList);
            map.put("retirementType", retirementTypeList);
            map.put("status", statusList);

            // Handle any driver errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            closeResource(con, rs, pstmt, null);
        }
        return map;

    }

    //	關閉資源
    public void closeResource(Connection con, ResultSet rs, PreparedStatement  pstmt, CallableStatement cstmt) {

        if (cstmt != null) {
            try {
                cstmt.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
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
