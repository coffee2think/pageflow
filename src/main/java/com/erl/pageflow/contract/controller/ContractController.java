package com.erl.pageflow.contract.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.contract.model.service.ContractService;
import com.erl.pageflow.contract.model.vo.Contract;
import com.erl.pageflow.edit.model.vo.Edit;

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
	public String selectBoardListMethod(@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		int listCount = contractService.selectContractListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "ctrlist.do");
		paging.calculator();
		
		ArrayList<Contract> list = contractService.selectContractList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("contractList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/contr_list";
		} else {
			model.addAttribute("message", "계약 목록 조회 실패!");
			return "common/error";
		}
	}

	// 계약 정보 수정 요청 처리
	@RequestMapping(value="ctrupdate.do", method=RequestMethod.POST)
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
	public String contractInsertMethod(HttpServletRequest request, Model model) {
		String[] empIds = request.getParameterValues("empId");
		String[] writerIds = request.getParameterValues("writerId");
		String[] bookNames = request.getParameterValues("bookName");
		String[] category = request.getParameterValues("category");
		
		// 주문번호 생성
		int contrId = contractService.selectMaxContrId() + 1;
		
		ArrayList<Contract> contracts = new ArrayList<>();
		for(int i = 0; i < empIds.length; i++) {
			Contract contract = new Contract();
			
			contract.setContrId(contrId);
			contract.setEmpId(Integer.parseInt(empIds[i]));
			contract.setWriterId(Integer.parseInt(writerIds[i]));
			contract.setBookName(bookNames[i]);
			contract.setCategory(category[i]);
			
			contracts.add(contract);
		}
		
		for(Contract contract : contracts) {
			if(contractService.insertContract(contract) == 0) {
				model.addAttribute("message", "계약 등록 실패!");
				return "common/error";
			}
		}
		
		return "redirect:ctrlist.do";
	}
	
	// 계약 날짜 조회
	@RequestMapping("ctrlistdate.do")
	public String selectContractByDate(Search search,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, 
			Model model) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = contractService.selectContractCountByDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "ctrlistdate.do");
		
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Contract> list = contractService.selectContractByDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {

			model.addAttribute("contractList", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "publish/contr_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
	}
	
	// 계약 정보 삭제 요청 처리
	@RequestMapping(value="ctrdelete.do", method=RequestMethod.POST)
	public String contractDeleteMethod(@RequestParam("IDs") int[] contrIDs, Model model) {
		logger.info("ctrdelete.do : " + contrIDs);
		
		if(contrIDs != null) {
			for(int contrId : contrIDs) {
				if(contractService.deleteContract(contrId) == 0) {
					model.addAttribute("message", contrId + "번 계약 삭제 실패!");
					return "common/error";
				}
			}
		}
		return "redirect:ctrlist.do";
	}
	
	// 키워드로 계약현황 검색
	@RequestMapping(value="ctrlistkwd.do", method={RequestMethod.GET, RequestMethod.POST})
	public String contractListByKeyword(Search search,
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("ctrlistkwd.do : searchType=" + searchType);
		logger.info("ctrlistkwd.do : " + search);
		logger.info("ctrlistkwd.do : page=" + page + ", limit=" + limitStr);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = 0;
		
		switch(searchType) {
		case "book":
			listCount = contractService.selectContractCountByBook(search);
			break;
		case "category":
			listCount = contractService.selectContractCountByCategory(search);
			break;
		case "writer":
			listCount = contractService.selectContractCountByWriter(search);
			break;
		case "employee":
			listCount = contractService.selectContractCountByEmployee(search);
			break;
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "ctrlistkwd.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Contract> list = null;
		
		switch(searchType) {
		case "book":
			list = contractService.selectContractByBook(search);
			break;
		case "category":
			list = contractService.selectContractByCategory(search);
			break;
		case "writer":
			list = contractService.selectContractByWriter(search);
			break;
		case "employee":
			list = contractService.selectContractByEmployee(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			
			model.addAttribute("contractList", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/contr_list";
		} else {
			model.addAttribute("message", "계약현황 " + search.getKeyword() + " 검색 실패");
			return "common/error";
		}
	}
	
	// 상태별로 계약현황 검색
	@RequestMapping(value="ctrliststs.do", method={RequestMethod.GET, RequestMethod.POST})
	public String contractListByStatus(Search search,
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("ctrliststs.do : searchType=" + searchType);
		logger.info("ctrliststs.do : " + search);
		logger.info("ctrliststs.do : page=" + page + ", limit=" + limitStr);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = 0;
		
		switch(searchType) {
		case "all":
			listCount = contractService.selectContractCountByStatusAll(search);
			break;
		case "ing":
			listCount = contractService.selectContractCountByStatusIng(search);
			break;
		case "finish":
			listCount = contractService.selectContractCountByStatusFinish(search);
			break;
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "ctrliststs.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Contract> list = null;
		
		switch(searchType) {
		case "all":
			list = contractService.selectContractByStatusAll(search);
			break;
		case "ing":
			list = contractService.selectContractByStatusIng(search);
			break;
		case "finish":
			list = contractService.selectContractByStatusFinish(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			
			model.addAttribute("contractList", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/contr_list";
		} else {
			model.addAttribute("message", "계약현황 " + searchType.getClass() + " 검색 실패");
			return "common/error";
		}
	}
}