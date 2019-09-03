package com.island.asset.domain;

import java.util.Date;

/**
 * 系統預設資料
 */
public class SystemConfig {

    private String sysItemId;
    private int seq;
    private String sysItemName;
    private String sysItemValue1;
    private String sysItemValue2;
    private String sysItemValue3;
    private Date lmTime;
    private String lmUser;

    public SystemConfig() {}

    public String getSysItemId() {
        return sysItemId;
    }

    public void setSysItemId(String sysItemId) {
        this.sysItemId = sysItemId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSysItemName() {
        return sysItemName;
    }

    public void setSysItemName(String sysItemName) {
        this.sysItemName = sysItemName;
    }

    public String getSysItemValue1() {
        return sysItemValue1;
    }

    public void setSysItemValue1(String sysItemValue1) {
        this.sysItemValue1 = sysItemValue1;
    }

    public String getSysItemValue2() {
        return sysItemValue2;
    }

    public void setSysItemValue2(String sysItemValue2) {
        this.sysItemValue2 = sysItemValue2;
    }

    public String getSysItemValue3() {
        return sysItemValue3;
    }

    public void setSysItemValue3(String sysItemValue3) {
        this.sysItemValue3 = sysItemValue3;
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
