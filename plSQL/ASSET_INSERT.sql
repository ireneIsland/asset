--登入資產
CREATE OR REPLACE FUNCTION regAsset
(
 assetNo ASSET.ASSET_NO %TYPE,
 assetType ASSET.ASSET_TYPE %TYPE,
 assetDesc ASSET.ASSET_DESC %TYPE,
 loc ASSET.LOCATION %TYPE,
 assetNoParent ASSET.ASSET_NO_PARENT %TYPE,
 assignee ASSET.ASSIGNEE %TYPE,
 amount ASSET.AMOUNT %TYPE,
 lmUser ASSET.LM_USER %TYPE
) RETURN VARCHAR2
IS
 status ASSET.STATUS %TYPE := '庫存';
 p_date date := SYSDATE; 
 p_status varchar2(20);
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
  p_date, 
  p_date, 
  lmUser);
    
--    新增完畢後更新資產編號
        UPDATE SYSTEM_CONFIG SET sys_item_value1 = assetNo WHERE  SYS_ITEM_ID = 'asset_no_end';
--     新增一筆歷史紀錄
        insert into ASSET_HISTORY values (assetNo, '庫存', assetDesc,  '',  loc, loc, assetNoParent, assignee, assignee, amount, p_date, lmUser);

  RETURN 'success';
EXCEPTION
    WHEN OTHERS THEN
    RETURN 'fail';
END regAsset;





--新增資產文件
CREATE OR REPLACE PROCEDURE add_attach_doc
(
 assetNo VARCHAR2,
 seq ATTACH_DOC.SEQ %TYPE,
 doc_name ATTACH_DOC.DOC_NAME %TYPE,
 path ATTACH_DOC.PATH %TYPE,
 asset_Desc ATTACH_DOC.ASSET_DESC %TYPE,
 lmUser ATTACH_DOC.LM_USER %TYPE
) 
IS
p_assetNo ASSET.ASSET_NO %TYPE;
BEGIN

   INSERT INTO ATTACH_DOC VALUES (
  assetNo,
  seq, 
  doc_name, 
  path, 
  asset_Desc,
  SYSDATE,
  lmUser 
  );
  COMMIT;
  
END add_attach_doc;



--新增歷史紀錄
CREATE OR REPLACE FUNCTION add_assetHist
(
 assetNo ASSET.asset_no %TYPE,
 changeType asset_history.CHANGE_TYPE %TYPE,
 assetDesc ASSET.ASSET_DESC %TYPE,
 retirementType VARCHAR2,
 loc ASSET.LOCATION %TYPE,
 assetNoParent ASSET.ASSET_NO_PARENT %TYPE,
 assignee ASSET.ASSIGNEE %TYPE,
 amount ASSET.AMOUNT %TYPE,
 useDate Date,
 lmUser ASSET.LM_USER %TYPE
) RETURN VARCHAR2
IS
p_assetNo ASSET.ASSET_NO %TYPE;
old_location ASSET.location %TYPE;
old_assignee ASSET.assignee %TYPE;
BEGIN

SELECT A.asset_no, A.location, A.assignee INTO p_assetNo, old_location, old_assignee
FROM ASSET_HISTORY A INNER JOIN 
(SELECT asset_no, max(LM_TIME) as maxdate FROM ASSET_HISTORY GROUP BY asset_no) B On A.asset_no=B.ASSET_NO And A.LM_TIME=B.maxdate where A.asset_no = assetNo;

 INSERT INTO ASSET_HISTORY VALUES (
  p_assetNo,
  changeType, 
  assetDesc, 
  retirementType, 
  old_location,
  loc,
  assetNoParent,
  old_assignee,
  assignee,
  amount,
  useDate,
  lmUser 
  );

RETURN 'success';
END add_assetHist;





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





