package com.island.asset.controller;

import com.island.asset.domain.Asset;
import com.island.asset.domain.ResultVO;
import com.island.asset.handler.FileUtilsHandler;
import com.island.asset.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetSvc;
    
    @Autowired
    private FileUtilsHandler fileUtils;
    
    @GetMapping("/hello")
    public String hello() {

        return "HELLO WORLD!";
    }
    
    @PostMapping(value = "/regAsset",produces = "application/json;charset=UTF-8")
    public ResultVO regAsset(@RequestBody Asset asset) {

        ResultVO result = new ResultVO();

        result.setResult(assetSvc.regAsset(asset));

        return result;
    }

    @PostMapping(value = "/upload")
    public ResultVO uploadFile(@RequestParam("file") MultipartFile[] files,
    						   @RequestParam("seq") String[] seqs,
    						   @RequestParam("assetNo") String assetNo) throws IOException {
    	
        ResultVO result = new ResultVO();
        List<Object> list = new ArrayList<>();
        
        if(seqs.length != 0 && files.length != 0) {
        	for(int i=0; i<seqs.length; i++) {
        		Map<String, Object> map = new HashMap<>();
        		map.put("seq", seqs[i]);
        		map.put("path", fileUtils.uploadFile(files[i], assetNo));
        		list.add(map);
        	} 
        	result.setResult(list);        	
        }
        
        return result;
    }

}

