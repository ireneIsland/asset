package com.island.asset.service;

import com.island.asset.Repository.ISystemDao;
import com.island.asset.domain.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SystemService {

    @Autowired
    private ISystemDao sysDao;
    
	@Autowired
	private ResultVO result;

    public Map<String,Object> getAllSystemParam() {
        return sysDao.getAllSystemParam();
    }


}
