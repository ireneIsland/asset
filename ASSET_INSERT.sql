--登入資產
CREATE OR REPLACE PROCEDURE regAsset
(
 status ASSET.STATUS %TYPE,
 assetType ASSET.ASSET_TYPE %TYPE,
 assetDesc ASSET.ASSET_DESC %TYPE,
 loc ASSET.LOCATION %TYPE,
 assetNoParent ASSET.ASSET_NO_PARENT %TYPE,
 assignee ASSET.ASSIGNEE %TYPE,
 amount ASSET.AMOUNT %TYPE,
 lmUser ASSET.LM_USER %TYPE
) 
IS
 assetNo ASSET.ASSET_NO %TYPE;
 chk_assetNo ASSET.ASSET_NO %TYPE;
 
BEGIN
  
  SELECT sys_item_value1 INTO assetNo FROM SYSTEM_CONFIG WHERE SYS_ITEM_ID = 'asset_no_end';
  
  assetNo := CHK_ASSET_NO(assetNo);
  
   INSERT INTO ASSET VALUES (
  assetNo,
  status, 
  assetType, 
  assetDesc, 
  loc, 
  assetNoParent, 
  assignee, 
  amount, 
  SYSDATE, 
  SYSDATE, 
  lmUser);
  
--  確認是否更新成功
  SELECT ASSET_NO INTO chk_assetNo FROM ASSET WHERE ASSET_NO = assetNo;
  
  IF(chk_assetNo IS NOT NULL) THEN
    UPDATE SYSTEM_CONFIG SET sys_item_value1 = chk_assetNo WHERE  SYS_ITEM_ID = 'asset_no_end';
  END IF;

END regAsset;





--新增資產文件 (未完成)
CREATE OR REPLACE PROCEDURE add_attach_doc
(
 assetNo ATTACH_DOC.ASSET_NO %TYPE,
 seq ATTACH_DOC.SEQ %TYPE,
 doc_name ATTACH_DOC.DOC_NAME %TYPE,
 path ATTACH_DOC.PATH %TYPE,
 asset_Desc ATTACH_DOC.ASSET_DESC %TYPE,
 lmUser ATTACH_DOC.LM_USER %TYPE
) 
IS
 
BEGIN
  
   INSERT INTO ASSET VALUES (
  assetNo,
  status, 
  assetType, 
  assetDesc, 
  loc, 
  assetNoParent, 
  assignee, 
  amount, 
  SYSDATE, 
  SYSDATE, 
  lmUser);
  
--  確認是否更新成功
  SELECT ASSET_NO INTO chk_assetNo FROM ASSET WHERE ASSET_NO = assetNo;
  
  IF(chk_assetNo IS NOT NULL) THEN
    UPDATE SYSTEM_CONFIG SET sys_item_value1 = chk_assetNo WHERE  SYS_ITEM_ID = 'asset_no_end';
  END IF;

END regAsset;


--開啟命令檔 輸出
SET SERVEROUTPUT ON;
BEGIN
--  DBMS_OUTPUT.put_line(TO_CHAR(SYSDATE,'YY'));
--  DBMS_OUTPUT.put_line(SUBSTR('1900001', 0, 2));
  DBMS_OUTPUT.put_line(LPAD(1,5,0));
END;

--檢查資產編號
CREATE OR REPLACE FUNCTION CHK_ASSET_NO( 
assetNo ASSET.ASSET_NO %TYPE
) RETURN VARCHAR2
IS
BEGIN

--檢查今年西元年
IF(TO_CHAR(SYSDATE,'YY') = SUBSTR(assetNo, 0, 2)) THEN
 RETURN assetNo+1;
ELSIF(TO_CHAR(SYSDATE,'YY') > SUBSTR(assetNo, 0, 2)) THEN
 RETURN TO_CHAR(sysdate, 'YY') || LPAD(1,5,0);
END IF;
END;





