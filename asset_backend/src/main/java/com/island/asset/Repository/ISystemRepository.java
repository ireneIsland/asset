package com.island.asset.Repository;

import com.island.asset.domain.SystemConfig;

import java.util.Map;

public interface ISystemRepository {

    public String addSystemParam(SystemConfig systemConfig);

    public String moddifySystemParam(SystemConfig systemConfig);

    public String deleteSystemParam(String sysItemID, int req);

    public Map<String,Object> getAllSystemParam();
}
