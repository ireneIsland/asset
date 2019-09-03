alter session set deferred_segment_creation=false;

DROP TABLE ASSET_HISTORY;
DROP TABLE ATTACH_DOC;
DROP TABLE SYSTEM_CONFIG;
DROP TABLE EMP_DATA;
DROP TABLE ORG_DATA;
DROP TABLE ASSET;


DROP SEQUENCE org_data_seq;
DROP SEQUENCE emp_data_seq;

DROP SEQUENCE asset_type_seq;
DROP SEQUENCE assignee_seq;
DROP SEQUENCE location_seq;
DROP SEQUENCE retirement_type_seq;

--資產
CREATE TABLE ASSET (
 ASSET_NO                    VARCHAR2(20) NOT NULL,
 STATUS                      VARCHAR2(10) NOT NULL,
 ASSET_TYPE                  VARCHAR2(20),
 ASSET_DESC                  NVARCHAR2(50),
 LOCATION                    NVARCHAR2(50) NOT NULL,
 ASSET_NO_PARENT             VARCHAR2(20),
 ASSIGNEE                    VARCHAR2(10) NOT NULL,
 AMOUNT                      INTEGER,
 CREATE_TIME                 DATE NOT NULL,
 LM_TIME                     DATE NOT NULL,
 LM_USER                     VARCHAR2(10) NOT NULL,

CONSTRAINT ASSET_PK PRIMARY KEY (ASSET_NO));

-- ============================================================
--資產歷史紀錄
CREATE TABLE ASSET_HISTORY (
 ASSET_NO                    VARCHAR2(20) NOT NULL,
 CHANGE_TYPE                  VARCHAR2(20),
 ASSET_DESC                  NVARCHAR2(50),
 RETIREMENT_TYPE             NVARCHAR2(50),
 LOCATION_OLD                NVARCHAR2(50),
 LOCATION                    NVARCHAR2(50) NOT NULL,
 ASSET_NO_PARENT             VARCHAR2(20),
 ASSIGNEE_OLD                VARCHAR2(10),
 ASSIGNEE                    VARCHAR2(10) NOT NULL,
 AMOUNT                      INTEGER,
 LM_TIME                     DATE NOT NULL,
 LM_USER                     VARCHAR2(10) NOT NULL,
 CONSTRAINT ASSET_HISTORY_FK FOREIGN KEY (ASSET_NO) REFERENCES ASSET (ASSET_NO),
 CONSTRAINT ASSET_HISTORY_PK PRIMARY KEY (LM_TIME));
 
 

-- ============================================================
--資產詳細
CREATE TABLE ATTACH_DOC (
 ASSET_NO                    VARCHAR2(20) NOT NULL,
 SEQ                         INTEGER NOT NULL,
 DOC_NAME                    VARCHAR2(50) NOT NULL,
 PATH                        VARCHAR2(100),
 ASSET_DESC                  NVARCHAR2(50),
 LM_TIME                     DATE NOT NULL,
 LM_USER                     VARCHAR2(10) NOT NULL,
 CONSTRAINT ATTACH_DOC_FK FOREIGN KEY (ASSET_NO) REFERENCES ASSET (ASSET_NO),
 CONSTRAINT ATTACH_DOC_PK PRIMARY KEY (ASSET_NO, SEQ));
 

-- ============================================================
--系統預設值
CREATE TABLE SYSTEM_CONFIG (
 SYS_ITEM_ID                 VARCHAR2(20) NOT NULL,
 SYS_ITEM_NAME               NVARCHAR2(50) NOT NULL,
 SEQ                         INTEGER NOT NULL,
 SYS_ITEM_VALUE1             NVARCHAR2(50) NOT NULL,
 SYS_ITEM_VALUE2             NVARCHAR2(50),
 SYS_ITEM_VALUE3             NVARCHAR2(50),
 LM_TIME                     DATE NOT NULL,
 LM_USER                     VARCHAR2(10) NOT NULL,

 CONSTRAINT SYSTEM_CONFIG_PK PRIMARY KEY (SYS_ITEM_ID, SEQ));

--資產類別
 CREATE SEQUENCE asset_type_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;

--預設保管人
 CREATE SEQUENCE assignee_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;
 
-- 預設存放地點
 CREATE SEQUENCE location_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;
 
-- 報廢方式
  CREATE SEQUENCE retirement_type_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;
 
INSERT INTO SYSTEM_CONFIG VALUES ('asset_type', '資產類別', TO_CHAR(asset_type_seq.NEXTVAL), 'IT', '資訊設備：PC, NB…', '', SYSDATE, 'is0240');
INSERT INTO SYSTEM_CONFIG VALUES ('asset_type', '資產類別', TO_CHAR(asset_type_seq.NEXTVAL), 'OA', '辦公室設備：桌子，椅子…', '', SYSDATE, 'is0240');

INSERT INTO SYSTEM_CONFIG VALUES ('asset_no_end', '資產流水號', 1, '1900000', '', '', SYSDATE, 'is0240');

INSERT INTO SYSTEM_CONFIG VALUES ('assignee', '預設保管人', TO_CHAR(assignee_seq.NEXTVAL), 'is0256', '', '', SYSDATE, 'is0240');
INSERT INTO SYSTEM_CONFIG VALUES ('location', '預設存放地點', TO_CHAR(location_seq.NEXTVAL), '新竹辦公室總務庫房', '', '', SYSDATE, 'is0240');

INSERT INTO SYSTEM_CONFIG VALUES ('retirement_type', '報廢方式', TO_CHAR(retirement_type_seq.NEXTVAL), '回收', '', '', SYSDATE, 'is0240');
INSERT INTO SYSTEM_CONFIG VALUES ('retirement_type', '報廢方式', TO_CHAR(retirement_type_seq.NEXTVAL), '丟棄', '', '', SYSDATE, 'is0240');
INSERT INTO SYSTEM_CONFIG VALUES ('retirement_type', '報廢方式', TO_CHAR(retirement_type_seq.NEXTVAL), '銷毀', '', '', SYSDATE, 'is0240');
INSERT INTO SYSTEM_CONFIG VALUES ('retirement_type', '報廢方式', TO_CHAR(retirement_type_seq.NEXTVAL), '遺失', '', '', SYSDATE, 'is0240');

--SELECT * FROM SYSTEM_CONFIG;


-- ============================================================
--組織資料
CREATE TABLE ORG_DATA (
 ORG_ID                 VARCHAR2(20) NOT NULL,
 ORG_NAME               NVARCHAR2(50) NOT NULL,
 BOSS_ID                VARCHAR2(10) NOT NULL,
 BOSS_NAME              NVARCHAR2(20) NOT NULL,
 ORG_LEVEL              VARCHAR2(10) NOT NULL,
 AREA                   NVARCHAR2(20),
 ORG_ID_PARENT          VARCHAR2(20),
 ORG_SHORTNAME          NVARCHAR2(50),
 ORG_LEVEL_NAME         NVARCHAR2(50),
 BOSS_TITLE             NVARCHAR2(20),
 BOSS_ID_PARENT         VARCHAR2(10),
 COMPANY                NVARCHAR2(20),
 LM_TIME                DATE NOT NULL,
 LM_USER                VARCHAR2(10) NOT NULL,
 
 CONSTRAINT ORG_DATA_PK PRIMARY KEY (ORG_ID));

 CREATE SEQUENCE org_data_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;

 -- ============================================================
--員工資料
CREATE TABLE EMP_DATA (
 EMP_ID           VARCHAR2(10) NOT NULL,
 PASSWORD         NVARCHAR2(20) NOT NULL,
 EMP_NAME         NVARCHAR2(20) NOT NULL,
 ENTRY_DATE       DATE NOT NULL,
 JOB_TITLE        NVARCHAR2(20) NOT NULL,
 ORG_ID           VARCHAR2(20) NOT NULL,
 ORG_NAME         NVARCHAR2(50) NOT NULL,
 BOSS_ID          VARCHAR2(10) NOT NULL,
 BOSS_NAME        NVARCHAR2(20) NOT NULL,
 COMPANY          NVARCHAR2(20),
 INSERVICE        CHAR(1) NOT NULL,
 EMAIL           VARCHAR2(20),
 TEL_NO           NVARCHAR2(50),
 LEVEL_DATE       DATE,
 LM_TIME          DATE NOT NULL,
 LM_USER          VARCHAR2(10) NOT NULL,
-- CONSTRAINT EMP_DATA_FK FOREIGN KEY (ORG_ID) REFERENCES ORG_DATA (ORG_ID),
 CONSTRAINT EMP_DATA_PK PRIMARY KEY (EMP_ID));

 CREATE SEQUENCE emp_data_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;