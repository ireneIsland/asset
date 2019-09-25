package com.island.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.island.asset.domain.ResultVO;
import com.island.asset.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpService empSvc;
	
	@GetMapping(value = "/getAllEmpData")
    public ResultVO getAllEmpData() {
        return empSvc.getAllEmpIdAndName();
    }
	
	@GetMapping(value = "/checkAccount")
	public ResultVO checkAccount() {
		ResultVO resultVO = new ResultVO();
		resultVO.setSuccess(false   );
		return resultVO;
	}

}
