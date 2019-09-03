package com.island.asset.service;

import com.island.asset.Repository.IAssetRepository;
import com.island.asset.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private IAssetRepository assetDao;

    /**
     * 新增一筆資產&相關資料
    */
    public Asset regAsset(Asset asset) {

        return assetDao.regAsset(asset);

    }

    public List<Asset> findAll() {
        return null;
    }

    public Asset findOne() {
        return null;
    }


}
