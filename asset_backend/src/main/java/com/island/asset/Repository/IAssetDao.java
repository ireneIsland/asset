package com.island.asset.Repository;

import com.island.asset.domain.Asset;
import com.island.asset.domain.AssetQueryForm;
import com.island.asset.domain.ResultVO;

import java.util.List;

public interface IAssetDao {

    public ResultVO regAsset(Asset asset) throws Exception;
    
    public ResultVO compoundQueryAsset(AssetQueryForm assetQueryForm) throws Exception;

    public List<Asset> findAll();

    public ResultVO findAssetDetail(String assetNo) throws Exception;


}
