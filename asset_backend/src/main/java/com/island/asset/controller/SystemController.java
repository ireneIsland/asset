package com.island.asset.controller;

import com.island.asset.domain.ResultVO;
import com.island.asset.handler.AssetNoHandler;
import com.island.asset.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService SystemSvc;

    @GetMapping(value = "/presetparam")
    public ResultVO getAllSystemParam() {

        ResultVO result = new ResultVO();

        result.setResult(SystemSvc.getAllSystemParam());

        return result;
    }
    
    /**
     * 取得新的資產流水編號
     * @param assetNo
     * @return
     */
    @GetMapping(value = "/newassetno")
    public String getNewAssetNo(@RequestParam("assetNo") String assetNo) {

    	AssetNoHandler anh = new AssetNoHandler();
    	String newAssetNo = anh.serialNo(assetNo);
    	return newAssetNo;
    }


}
