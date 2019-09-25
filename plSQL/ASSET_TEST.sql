select a.*, b.seq, b.doc_name, b.path, b.doc_desc from ASSET a INNER join ATTACH_DOC b  on a.asset_no = b.asset_no;
SELECT * FROM ASSET;
select * from ASSET where asset_no is not null and asset_no like '%1900001%';
select * from ASSET where asset_no is not null and to_char(create_time,'yyyy-MM-dd') >= '2019-11-03';
SELECT * FROM ATTACH_DOC;
SELECT * FROM EMP_DATA;

SELECT * FROM ASSET_HISTORY;
SELECT * FROM SYSTEM_CONFIG;

select * from EMP_DATA;
select emp_id, emp_name from EMP_DATA;

select a.*, b.seq, b.doc_name, b.path, b.doc_desc from ASSET a join ATTACH_DOC b  on a.asset_no = b.asset_no where a.asset_no = '1900001';

EXEC regAsset('庫存', 'OA', '測試', '新竹辦公室總務庫房', '', 'is0256', 2002, 'is0240');

EXEC add_attach_doc('1900001', 1, '物品A', 'C:/test', '測試', 'is0240');

DECLARE
    p_status varchar2(20);
BEGIN
    p_status := add_assetHist(1900001, '入帳', '', 'test', '新竹辦公室總務庫房', '', 'is0256', 3333, SYSDATE, 'is0240');
     sys.dbms_output.put_line(p_status);
END;



 insert into ASSET_HISTORY values ('1900001', '入帳',  'test',  '',  '新竹辦公室總務庫房', '新竹辦公室總務庫房', '', 'is0256', 'is0256', 3333, SYSDATE, 'is0240'  );
 
  select to_char(LM_TIME, 'MM-DD-YYYY HH24:MI:SS') from ASSET_HISTORY;
 select  MAX(to_char(LM_TIME, 'YYYY-MM-DD HH:mm:ss')) from ASSET_HISTORY;
 
-- 這種作法不能+ where
select max(LM_TIME) as lastestUpdate, asset_no, assignee
from ASSET_HISTORY
group by asset_no, assignee;

SELECT A.ASSET_NO,  to_char(LM_TIME, 'MM-DD-YYYY HH24:MI:SS')
 FROM ASSET_HISTORY A INNER JOIN 
     (SELECT ASSET_NO,max(LM_TIME) as maxdate FROM ASSET_HISTORY GROUP BY ASSET_NO) B On A.ASSET_NO=B.ASSET_NO And A.LM_TIME=B.maxdate where A.ASSET_NO = '1900001';
     

SELECT A.asset_no, a.location, a.assignee ,to_char(lm_time, 'MM-DD-YYYY HH24:MI:SS')
FROM ASSET_HISTORY A INNER JOIN 
(SELECT ASSET_NO,max(LM_TIME) as maxdate FROM ASSET_HISTORY GROUP BY ASSET_NO) B On A.ASSET_NO=B.ASSET_NO And A.LM_TIME=B.maxdate where A.ASSET_NO = '1900001';


--多筆 INSERT 測試
DECLARE
 TYPE innerType is TABLE of number
    index by binary_integer;
 TYPE outerType is TABLE of innerType
    index by binary_integer;
multiArray outerType;
cnter int:=1;
BEGIN
    for i in 1..5 loop
        for j in 1..5 loop
            multiArray(i)(j) := cnter;
        end loop;
    end Loop;

    for i in 1..5 loop
        for j in 1..5 loop
            DBMS_OUTPUT.put('[' || multiArray(i)(j) || ']' );
        end loop;
        DBMS_OUTPUT.put_line('');
    end loop;
END;









--設定區
--開啟命令檔 輸出
SET SERVEROUTPUT ON;

--測試
BEGIN
  DBMS_OUTPUT.put_line(TO_CHAR(sysdate, 'YY')||LPAD(TO_CHAR(asset_seq.NEXTVAL),5,0));
END;

--測試
BEGIN
  DBMS_OUTPUT.put_line(TO_DATE('1981-11-17','YYYY-MM-DD'));
END;