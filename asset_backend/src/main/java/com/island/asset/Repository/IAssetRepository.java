package com.island.asset.Repository;

import com.island.asset.domain.Asset;

import java.util.List;

public interface IAssetRepository {

    public Asset regAsset(Asset asset);

    public List<Asset> findAll();

    public Asset findOne();


}
