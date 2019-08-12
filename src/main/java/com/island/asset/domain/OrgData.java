package com.island.asset.domain;

import java.util.Date;

/**
 * 組織資料
 */
public class OrgData {

    private String orgId;
    private String orgName;
    private String bossId;
    private String bossName;
    private String orgLevel;
    private String area;
    private String orgIdParent;
    private String orgShortName;
    private String orgLevelName;
    private String bossTitle;
    private String bossIdParent;
    private String company;
    private Date lmTime;
    private String lmUser;

    public OrgData() {}

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBossId() {
        return bossId;
    }

    public void setBossId(String bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrgIdParent() {
        return orgIdParent;
    }

    public void setOrgIdParent(String orgIdParent) {
        this.orgIdParent = orgIdParent;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getOrgLevelName() {
        return orgLevelName;
    }

    public void setOrgLevelName(String orgLevelName) {
        this.orgLevelName = orgLevelName;
    }

    public String getBossTitle() {
        return bossTitle;
    }

    public void setBossTitle(String bossTitle) {
        this.bossTitle = bossTitle;
    }

    public String getBossIdParent() {
        return bossIdParent;
    }

    public void setBossIdParent(String bossIdParent) {
        this.bossIdParent = bossIdParent;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
