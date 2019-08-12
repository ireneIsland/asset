package com.island.asset.domain;

import java.util.Date;


/**
 * 資產管理歷史紀錄
 */
public class AssetHistory {

    private String assetNo;
    private String changeType;
    private String desc;
    private String retirementType;
    private String locationOld;
    private String location;
    private String assetNoParent;
    private String assigneeOld;
    private String assignee;
    private int amount;
    private Date lmTime;
    private String lmUser;

    public AssetHistory() {}

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRetirementType() {
        return retirementType;
    }

    public void setRetirementType(String retirementType) {
        this.retirementType = retirementType;
    }

    public String getLocationOld() {
        return locationOld;
    }

    public void setLocationOld(String locationOld) {
        this.locationOld = locationOld;
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

    public String getAssigneeOld() {
        return assigneeOld;
    }

    public void setAssigneeOld(String assigneeOld) {
        this.assigneeOld = assigneeOld;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getLmTime() {
        return lmTime;
    }

    public void setLmTime(Date lmTime) {
        this.lmTime = lmTime;
    }

    public String getLmUser() {
        return lmUser;
    }

    public void setLmUser(String lmUser) {
        this.lmUser = lmUser;
    }
}
