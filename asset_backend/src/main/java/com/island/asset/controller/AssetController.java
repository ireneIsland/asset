package com.island.asset.controller;

import com.island.asset.domain.Asset;
import com.island.asset.domain.AssetQueryForm;
import com.island.asset.domain.ResultVO;
import com.island.asset.handler.FileUtilsHandler;
import com.island.asset.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetSvc;
    
    @Autowired
    private FileUtilsHandler fileUtils;
    
    @PostMapping(value = "/regAsset",produces = "application/json;charset=UTF-8")
    public ResultVO regAsset(@RequestBody Asset asset) throws Exception {
        return assetSvc.regAsset(asset);
    }

    
    @PostMapping(value = "/queryAsset",produces = "application/json;charset=UTF-8")
    public ResultVO queryAsset(@RequestBody AssetQueryForm assetQueryForm) throws Exception {
    	return assetSvc.compoundQueryAsset(assetQueryForm);
    }
    
    @GetMapping(value = "/getAssetDetail")
    public ResultVO getAssetDetail(@RequestParam("assetNo") String assetNo) throws Exception {
    	return assetSvc.getAssetDetail(assetNo);
    }

    @PostMapping(value = "/upload")
    public ResultVO uploadFile(@RequestParam("file") MultipartFile[] files,
    		@RequestParam("seq") String[] seqs,
    		@RequestParam("assetNo") String assetNo) throws Exception {
    	
    	ResultVO result = new ResultVO();
    	
    	result.setResult(fileUtils.uploadFileList(files,seqs, assetNo));        	
    	
    	return result;
    }
    
    
}

