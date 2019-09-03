package com.island.asset.service;

import com.island.asset.Repository.ISystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SystemService {

    @Autowired
    private ISystemRepository sysDao;

    public Map<String,Object> getAllSystemParam() {


        return sysDao.getAllSystemParam();
    }


}
