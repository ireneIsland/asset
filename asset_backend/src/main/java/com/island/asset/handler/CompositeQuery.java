package com.island.asset.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.island.asset.domain.AssetQueryForm;

@Component("CompositeQuery")
public class CompositeQuery {
	
	public String get_aCondition_For_Oracle(AssetQueryForm assetQueryForm) {
		
		String sql = "select * from ASSET";
		String aCondition = null;
		StringBuffer whereCondition = new StringBuffer();
		boolean isCondition = false;
		
		if(assetQueryForm.getAssetNo() != null && !assetQueryForm.getAssetNo().isEmpty()) {
			whereCondition.append(" and asset_no like '%" + assetQueryForm.getAssetNo() + "%'");
			isCondition = true;
		}
		
		if(assetQueryForm.getAssetNoParent() != null && !assetQueryForm.getAssetNoParent().isEmpty()) {
			whereCondition.append(" and asset_no_parent like '%" + assetQueryForm.getAssetNoParent() + "%'");
			isCondition = true;
		}
		
		if(assetQueryForm.getAssetType() != null && !assetQueryForm.getAssetType().isEmpty()) {
			whereCondition.append(" and asset_type like '%" + assetQueryForm.getAssetType() + "%'");
			isCondition = true;
		}
		
		if(assetQueryForm.getAssignee() != null && !assetQueryForm.getAssignee().isEmpty()) {
			whereCondition.append(" and assignee like '%" + assetQueryForm.getAssignee() + "%'");
			isCondition = true;
		}
		
		if(assetQueryForm.getLocation() != null && !assetQueryForm.getLocation().isEmpty()) {
			whereCondition.append(" and location like '%" + assetQueryForm.getLocation() + "%'");
			isCondition = true;
		}
		
		if(assetQueryForm.getStatus() != null && !assetQueryForm.getStatus().isEmpty()) {
			whereCondition.append(" and status like '%" + assetQueryForm.getStatus() + "%'");
			isCondition = true;
		}
		
		if(assetQueryForm.getStAmount() != null && !assetQueryForm.getStAmount().isEmpty()) {
			whereCondition.append(" and amount >= " + assetQueryForm.getStAmount());
			isCondition = true;
		}
		
		if(assetQueryForm.getLtAmount() != null && !assetQueryForm.getLtAmount().isEmpty()) {
			whereCondition.append(" and amount <= " + assetQueryForm.getLtAmount());
			isCondition = true;
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(assetQueryForm.getStDate() != null) {
			whereCondition.append(" and to_char(create_time,'yyyy-MM-dd') >= '" + dateFormat.format(assetQueryForm.getStDate()) + "'" );
			isCondition = true;
		}
		
		if(assetQueryForm.getLtDate() != null) {
			whereCondition.append(" and to_char(create_time,'yyyy-MM-dd') <= '" + dateFormat.format(assetQueryForm.getLtDate()) + "'" );
			isCondition = true;
		}

		if(isCondition) {
			sql = sql + " where 1 = 1";
		}
		
		aCondition = whereCondition.toString();

		return sql + aCondition;
	}

}
