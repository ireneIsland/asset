package com.island.asset.Repository;

import com.island.asset.domain.Asset;
import com.island.asset.domain.AssetQueryForm;
import com.island.asset.domain.AttachDoc;
import com.island.asset.domain.ResultVO;
import com.island.asset.handler.CompositeQuery;
import com.island.asset.handler.FileUtilsHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.*;

@Repository
public class AssetDao implements IAssetDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileUtilsHandler fileUtils;

	@Autowired
	private CompositeQuery compositeQuery;

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
	public ResultVO regAsset(Asset asset) throws Exception {

		ResultVO result = new ResultVO();
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			logger.info("regAsset Start!");
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			cstmt = con.prepareCall("{?= call regAsset(?, ?, ?, ?, ?, ?, ?, ?)}");

			cstmt.setString(2, asset.getAssetNo());
			cstmt.setString(3, asset.getAssetType());
			cstmt.setString(4, asset.getDesc());
			cstmt.setString(5, asset.getLocation());
			cstmt.setString(6, asset.getAssetNoParent());
			cstmt.setString(7, asset.getAssignee());
			cstmt.setInt(8, asset.getAmount());
			cstmt.setString(9, asset.getLmUser());
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.execute();

			String regStatus = cstmt.getString(1);
			logger.info("regAsset status: " + regStatus);

			if ("success".equals(regStatus)) {
				cstmt = con.prepareCall("{call add_attach_doc(?, ?, ?, ?, ?, ?)}");
				List<AttachDoc> list = new ArrayList<>();
				list = asset.getAttachDocList();

				for (AttachDoc att : list) {
					cstmt.setString(1, asset.getAssetNo());

					if (att.getSeq() != 0) {
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
				logger.info("regAsset commit. ");

			} else {
				result.setSuccess(false);
				return result;
			}

		} catch (SQLException se) {

			try {

				logger.error("rollback");
				con.rollback();
				fileUtils.deleteAttachDocList(asset.getAttachDocList());

			} catch (SQLException se1) {

				logger.error(se1.getMessage());
				throw se1;

			}
			throw se;

		} catch (ClassNotFoundException e) {
			logger.error("Couldn't load database driver. " + e.getMessage());
			fileUtils.deleteAttachDocList(asset.getAttachDocList());
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}

		finally {
			closeResource(con, cstmt, null, null);
		}
		result.setResult(asset);
		return result;
	}

	@Override
	public ResultVO compoundQueryAsset(AssetQueryForm assetQueryForm) throws Exception {

		List<Asset> list = new ArrayList<Asset>();
		ResultVO result = new ResultVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String finalSql = null;

		try {

			finalSql = compositeQuery.get_aCondition_For_Oracle(assetQueryForm);
			logger.info("SQL: "+finalSql);

			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(finalSql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Asset asset = new Asset();
				asset.setAssetNo(rs.getString("asset_no"));
				asset.setAssetNoParent(rs.getString("asset_no_parent"));
				asset.setAmount(rs.getInt("amount"));
				asset.setAssetType(rs.getString("asset_type"));
				asset.setAssignee(rs.getString("assignee"));
				asset.setDesc(rs.getString("asset_desc"));
				asset.setLocation(rs.getString("location"));
				asset.setStatus(rs.getString("status"));
				asset.setLmUser(rs.getString("lm_user"));
				asset.setLmTime(rs.getDate("lm_time"));
				asset.setCrateTime(rs.getDate("create_time"));
				list.add(asset);
			}
			result.setResult(list);

		} catch (SQLException e) {
			throw e;
		} finally {
			closeResource(con, null, pstmt, rs);
		}
		return result;
	}

	@Override
	public ResultVO findAssetDetail(String assetNo) throws Exception {
		
		List<AttachDoc> list = new ArrayList<AttachDoc>();
		ResultVO result = new ResultVO();
		Asset asset = new Asset();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

//			這邊SQL 需要修改
//			若 ATTACH_DOC 無資料  會搜尋不到資料
			
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement("select a.*, b.seq, b.doc_name, b.path, b.doc_desc from ASSET a join ATTACH_DOC b  on a.asset_no = b.asset_no where a.asset_no =? ");
			
			pstmt.setString(1, assetNo);
			rs = pstmt.executeQuery();

			int count = 0;
			while (rs.next()) {
				
				if(count == 0) {
					asset.setAssetNo(rs.getString("asset_no"));
					asset.setAssetNoParent(rs.getString("asset_no_parent"));
					asset.setAmount(rs.getInt("amount"));
					asset.setAssetType(rs.getString("asset_type"));
					asset.setAssignee(rs.getString("assignee"));
					asset.setDesc(rs.getString("asset_desc"));
					asset.setLocation(rs.getString("location"));
					asset.setStatus(rs.getString("status"));
					asset.setLmUser(rs.getString("lm_user"));
					asset.setLmTime(rs.getDate("lm_time"));
					asset.setCrateTime(rs.getDate("create_time"));
				}
				
				AttachDoc attachDoc = new AttachDoc();
				attachDoc.setSeq(rs.getInt("seq"));
				attachDoc.setDocName(rs.getString("doc_name"));
				attachDoc.setPath(rs.getString("path"));
				attachDoc.setDesc(rs.getString("doc_desc"));
				list.add(attachDoc);
				count++;
			}
			
			asset.setAttachDocList(list);
			result.setResult(asset);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeResource(con, null, pstmt, rs);
		}

		return result;
	}

	@Override
	public List<Asset> findAll() {
		return null;
	}

	// 關閉資源
	public void closeResource(Connection con, CallableStatement cstmt, PreparedStatement pstmt, ResultSet rs) {

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
		if (pstmt != null) {
			try {
				pstmt.close();
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
