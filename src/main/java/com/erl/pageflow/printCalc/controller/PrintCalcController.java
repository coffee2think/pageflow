package com.erl.pageflow.printCalc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printCalc.model.service.PrintCalcService;
import com.erl.pageflow.printCalc.model.vo.PrintCalc;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Controller
public class PrintCalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(PrintCalcController.class);
	
	@Autowired
	private PrintCalcService printCalcService;
	
	
	
	//*************************** 뷰 페이지 이동 *****************************
	
	// 정산현황 리스트 조회
	@RequestMapping("pclist.do")
	public String movePrintCalcPage(
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr, Model model) {
		
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if(limitStr != null) {
			limit = Integer.parseInt(limitStr);
		}
		
		int listCount = printCalcService.selectPrintCalcListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "pclist.do");
		paging.calculator();
		
		ArrayList<PrintCalc> list = printCalcService.selectPrintCalcList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/calc_list";
		} else {
			model.addAttribute("message", "정산 목록 조회 실패!");
			return "common/error";
		}	
	}
	
	//마감날짜로 정산현황 검색 처리용
		@RequestMapping("pclistEdate.do")
		public String selectPrintCalcByEDate(Search search,
				@RequestParam(name="page", required=false) String page,
				@RequestParam(name="limit", required=false) String limitStr,
				Model model) {
			
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		int limit = 10;
		if (limitStr != null) {
			limit = Integer.parseInt(limitStr);
		} 
		
		int listCount = printCalcService.selectPrintCalcCountByEDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "pclistEdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<PrintCalc> list = printCalcService.selectPrintCalcByEDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/calc_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
	}
	
	//키워드[정산코드,인쇄소명,도서코드,도서명]로 정산현황 검색		
	@RequestMapping(value="pckeyword.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String selectPrintCalcByKeyword(Search search,
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("pckeyword.do : searchType" + searchType);
		logger.info("pckeyword.do : page" + page);
		logger.info("pckeyword.do : limitStr" + limitStr);
		
		//검색결과에 대한 페이징 처리
		//출력할 페이지 지정
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//한 페이지당 출력할 목록 개수 지정
		int limit = 10;
		
		//전송 온 limit 값이 있다면
		if(limitStr != null) {
			limit = Integer.parseInt(limitStr);
		}
		
		int listCount = 0;
		
		switch(searchType) {
		case "printName":
			listCount = printCalcService.selectPrintCalcCountByPrintName(search);
			break;
		case "bookName":
			listCount = printCalcService.selectPrintCalcCountByBookName(search);
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "pckeyword.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<PrintCalc> list = null;
		
		switch(searchType) {
		case "printName":
			list = printCalcService.selectPrintCalcByPrintName(search);
			break;
		case "bookName":
			list = printCalcService.selectPrintCalcByBookName(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/calc_list";
		} else {
			model.addAttribute("message", search.getKeyword() + "정산현황 검색 실패");
			return "common/error";
		}
	}
		
	
	
	//정산 수정 요청
	@RequestMapping(value="pcupdate.do", method=RequestMethod.POST)
	public void printCalcUpdateMethod(HttpServletResponse response, PrintCalc printCalc) throws IOException {
		logger.info("pcupdate.do : " + printCalc);
		
		
		String returnStr = null;
		if(printCalcService.updatePrintCalc(printCalc) > 0) {
			returnStr = "success";
		} else {
			returnStr = "fail";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.append(returnStr);
		out.flush();
		out.close();
	}
	
}


