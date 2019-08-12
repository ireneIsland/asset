package com.island.asset.controller;

import com.island.asset.domain.Asset;
import com.island.asset.domain.AttachDoc;
import com.island.asset.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AssetController {

    @Autowired
    private AssetService assetSvc;

    @GetMapping("/test1")
    public String test() {
        return "test!!!!!!";
    }

    @GetMapping("/test2")
    public String regAsset() {
        Asset asset = new Asset();
        asset.setStatus("庫存");
        asset.setAssetType("OA");
        asset.setDesc("測試3");
        asset.setLocation("新竹辦公室總務庫房");
        asset.setAssignee("is0256");
        asset.setAmount(8888);
        asset.setLmUser("is0240");

        List<AttachDoc> list = new ArrayList<AttachDoc>();

        AttachDoc attachDoc = new AttachDoc();
        attachDoc.setDocName("A物");
        attachDoc.setPath("A路徑");
        list.add(attachDoc);


        AttachDoc attachDoc2 = new AttachDoc();
        attachDoc2.setDocName("B物");
        attachDoc2.setPath("B路徑");
        list.add(attachDoc2);


        for(AttachDoc att: list){
            System.out.println(att.getDocName());
            System.out.println(att.getPath());
        }


        assetSvc.regAsset(asset);
        return "success";
    }






}

