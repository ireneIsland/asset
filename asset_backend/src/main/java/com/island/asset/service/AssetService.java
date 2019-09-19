package com.island.asset.service;

import com.island.asset.Repository.IAssetDao;
import com.island.asset.domain.Asset;
import com.island.asset.domain.AssetQueryForm;
import com.island.asset.domain.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private IAssetDao assetDao;
    
	@Autowired
	private ResultVO result;

    /**
     * 新增一筆資產&相關資料
     * @throws SQLException 
    */
    public ResultVO regAsset(Asset asset) throws Exception {
    	result = null;
        return assetDao.regAsset(asset);

    }
    
    public ResultVO compoundQueryAsset(AssetQueryForm assetQueryForm) throws Exception {
    	result = null;
    	return assetDao.compoundQueryAsset(assetQueryForm);
    }
    
    public ResultVO getAssetDetail(String assetNo) throws Exception {
    	result = null;
    	return assetDao.findOneAssetDetail(assetNo);
    }
    

    public List<Asset> findAll() {
        return null;
    }

    public Asset findOne() {
        return null;
    }


}
