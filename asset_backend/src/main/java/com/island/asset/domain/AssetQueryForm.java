package com.island.asset.domain;

import java.util.Date;

public class AssetQueryForm {
	
	private String assetNo;
    private String status;
    private String assetType;
    private String location;
    private String assetNoParent;
    private String assignee;
    private String stAmount;
    private String ltAmount;
    private Date stDate;
    private Date ltDate;
    
    
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAssetNoParent() {
		return assetNoParent;
	}
	public void setAssetNoParent(String assetNoParent) {
		this.assetNoParent = assetNoParent;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getStAmount() {
		return stAmount;
	}
	public void setStAmount(String stAmount) {
		this.stAmount = stAmount;
	}
	public String getLtAmount() {
		return ltAmount;
	}
	public void setLtAmount(String ltAmount) {
		this.ltAmount = ltAmount;
	}
	public Date getStDate() {
		return stDate;
	}
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}
	public Date getLtDate() {
		return ltDate;
	}
	public void setLtDate(Date ltDate) {
		this.ltDate = ltDate;
	}
}
