package com.erl.pageflow.contract.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.contract.model.service.ContractService;
import com.erl.pageflow.contract.model.vo.Contract;

@Controller
public class ContractController {
	private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	private ContractService contractService;
	
	//계약 리스트 조회
	@RequestMapping("ctrlist.do")
	public String selectBoardListMethod(Model model) {
		int listCount = contractService.selectContractListCount();
		int limit = 10;
		Paging paging = new Paging(listCount, 1, limit, "ctrlist.do");
		paging.calculator();
		ArrayList<Contract> list = contractService.selectContractList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("paging", paging);
			model.addAttribute("contractList", list);
			return "publish/contr_list";
		}else {
			model.addAttribute("message", "계약 조회 실패!");
			return "common/error";
		}
	}
		
}
