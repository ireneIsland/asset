package com.island.asset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.island.asset.Repository.IEmpDao;
import com.island.asset.domain.ResultVO;

@Service
public class EmpService {
	
	@Autowired
	private IEmpDao empDao;
	
	@Autowired
	private ResultVO result;
	
	public ResultVO getAllEmpIdAndName() {
		
		result.setResult(empDao.getAllEmpIdAndName());
		return result;
	}

}
