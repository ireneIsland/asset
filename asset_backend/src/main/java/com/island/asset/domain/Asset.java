package com.island.asset.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 資產
 */
public class Asset {

        private String assetNo;
        private String status;
        private String assetType;
        private String desc;
        private String location;
        private String assetNoParent;
        private String assignee;
        private int amount;
        private Date crateTime;
        private Date lmTime;
        private String lmUser;
        private List<AttachDoc> attachDocList = new ArrayList<>();


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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
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

    public List<AttachDoc> getAttachDocList() {
        return attachDocList;
    }

    public void setAttachDocList(List<AttachDoc> attachDocList) {
        this.attachDocList = attachDocList;
    }
}
