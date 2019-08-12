package com.island.asset.domain;

import java.util.Date;

/**
 * 資產管理詳情資料連接
 */
public class AttachDoc {

    private String assetNo;
    private int seq;
    private String docName;
    private String path;
    private String desc;
    private Date lmTime;
    private String lmUser;

    public AttachDoc() {}

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
