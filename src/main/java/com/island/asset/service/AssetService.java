package com.island.asset.service;

import com.island.asset.Repository.AssetRepository;
import com.island.asset.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetDao;

    /**
     * 登記一筆資產
     */
    public void regAsset(Asset asset) {
        assetDao.regAsset(asset);
    }

    public List<Asset> findAll() {
        return null;
    }

    public Asset findOne() {
        return null;
    }


}
