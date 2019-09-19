package com.island.asset.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDao implements IEmpDao{
	
	@Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.data-username}")
    private String username;
    @Value("${spring.datasource.data-password}")
    private String password;

	@Override
	public List<Map<String, Object>> getAllEmpIdAndName() {
		
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();

        try {

            con = DriverManager.getConnection(url, username, password);
            pstmt = con.prepareStatement("select emp_id, emp_name from EMP_DATA");
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	
            	Map<String, Object> map = new HashMap<>();
            	map.put("empId", rs.getString("emp_id"));
            	map.put("empName", rs.getString("emp_name"));
            	list.add(map);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            closeResource(con, rs, pstmt, null);
        }

		return list;
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
