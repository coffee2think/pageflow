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
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Controller
public class PrintCalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(PrintCalcController.class);
	
	@Autowired
	private PrintCalcService printCalcService;
	
	//*************************** 뷰 페이지 이동 *****************************
	
	@RequestMapping("pclist.do")
	public String movePrintCalcPage(Model model) {
		
		// 첫 이동시 해당 기간 데이터 조회
		LocalDate today = LocalDate.now();
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1));	//1주일 전부터
		model.addAttribute("end", today);	//오늘까지
		
		return "redirect:pclistdate.do";
	}
	
	// 날짜로 정산현황 검색 처리용
	@RequestMapping("pclistdate.do")
	public String printCalcList(Search search,
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
		
		int listCount = printCalcService.selectPrintCalcListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "pclistdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<PrintCalc> list = printCalcService.selectPrintCalcByDate(search);
		
		for(PrintCalc pc : list) {
			PrintOffice poffice  = printCalcService.selectPrintOffice(pc.getClientId());
			Book book = printCalcService.selectBook(pc.getBookId());
			
			pc.setClientName(poffice.getClientName());
			pc.setBookName(book.getBookName());
		}	
		
		model.addAttribute("list", list);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		
		return "print/calc_list";
	}
	
	//정산현황 조회 처리용
	@RequestMapping("printCacllist.do")
	public String printCalcList(
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
		
		int listCount = printCalcService.selectPrintCalcListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "printCalclist.do");
		paging.calculator();
		
		ArrayList<PrintCalc> list = printCalcService.selectPrintCalcList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/calc_list";
		} else {
			model.addAttribute("message", "발주 조회 실패 : 등록된 거래처가 없습니다");
			return "common/error";
		}
	}
	
	//정산 수정 요청
	@RequestMapping(value="pcupdate.do", method=RequestMethod.POST)
	public void printCalcUpdateMethod(HttpServletResponse response, PrintCalc printCalc) throws IOException {
		logger.info("pcupdate.do : " + printCalc);
		
		int resutl = printCalcService.updatePrintCalc(printCalc);
		
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


