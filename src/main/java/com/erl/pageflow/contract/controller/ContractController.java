package com.erl.pageflow.contract.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.contract.model.service.ContractService;
import com.erl.pageflow.contract.model.vo.Contract;
import com.erl.pageflow.sales.model.vo.BookOrder;

@Controller
public class ContractController {
	private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	private ContractService contractService;
	
	// ****************** 뷰페이지 이동 **********************
	
	// 계약 등록 페이지 이동
	@RequestMapping("movectrinsert.do")
	public String moveContractInsertPage() {
		return "publish/contr_input";
	}
	

	// ****************** 요청 받아서 서비스로 넘기는 메소드 **********************
	
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
		
	//계약 수정 요청 처리
	@RequestMapping(value = "ctrupdate.do", method = RequestMethod.POST)
	public void contractUpdateMethod(Contract contract, HttpServletResponse response) throws IOException {
		logger.info("ctrupdate.do : " + contract);
		
		String returnStr = null;
		if(contractService.updateContract(contract) > 0) {
			returnStr = "success";
		} else {
			returnStr = "fail";
		}
		
		// response를 이용해서 클라이언트와 출력스트림을 열어서 값 보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}
	
	// 계약 정보 등록 요청 처리
	@RequestMapping(value="ctrinsert.do", method=RequestMethod.POST)
	public String contractInsertMethod(Contract contract, Model model) {
		logger.info("ctrinsert.do : " + contract);
		
		if(contractService.insertContract(contract) > 0) {
			return "redirect:ctrlist.do";
		} else {
			model.addAttribute("message", "계약 등록 실패!");
			return "common/error";
		}
	}
}
