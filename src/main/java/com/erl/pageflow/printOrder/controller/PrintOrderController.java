package com.erl.pageflow.printOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printOrder.model.service.PrintOrderService;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Controller
public class PrintOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(PrintOrderController.class);
	
	@Autowired
	private PrintOrderService printOrderService;
	
	//*************************** 뷰 페이지 이동 *****************************
	
	//발주현황 페이지 이동 처리용
	@RequestMapping("polist.do")
	public String movePrintOrderPage(Model model) {
		
		
		
		// 첫 이동시 해당 기간 데이터 조회
		LocalDate today = LocalDate.now();
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1));	//1주일 전부터
		model.addAttribute("end", today);	//오늘까지
		
		return "redirect:polistdate.do";
	}
	
	// 코드로 주문현황 검색 처리용
	@RequestMapping("polistdate.do")
	public String printOrderList(Search search,
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
		
		int listCount = printOrderService.selectPrintOrderListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "polistdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		
		ArrayList<PrintOrder> list = printOrderService.selectPrintOrderByDate(search);
		
		for(PrintOrder po : list) {
			PrintOffice poffice  = printOrderService.selectPrintOffice(po.getPrintId());
			Book book = printOrderService.selectBook(po.getBookId());
			
			po.setClientName(poffice.getClientName());
			po.setBookName(book.getBookName());
		}
		
		model.addAttribute("list", list);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		
		return "print/porder_list";
	}
	
	//발주현황 조회 처리용
	@RequestMapping("printOrderlist.do")
	public String printOrderList(
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
		
		int listCount = printOrderService.selectPrintOrderListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "printOrderlist.do");
		paging.calculator();
		
		ArrayList<PrintOrder> list = printOrderService.selectPrintOrderList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", "발주 조회 실패 : 등록된 거래처가 없습니다");
			return "common/error";
		}
	}
	
	// 발주등록 페이지
	@RequestMapping("poinsert.do")
	public String PrintOrderInsert() {
		return "print/porder_input";
	}
	
	//발주 등록 요청 처리용
	@RequestMapping(value="poinsert.do", method=RequestMethod.POST)
	public String printOrderInsertMethod(PrintOrder printOrder, Model model, 
			HttpServletRequest request) {
		logger.info("poinsert.do : " + printOrder);
		
		if(printOrderService.poinsert(printOrder) > 0) {
			
			return "redirect:polist.do";
		} else {
			model.addAttribute("message", "새 공지글 등록 실패");
			return "common/error";
		}
	}
	
	//발주 수정 요청 처리용
	@RequestMapping(value="poupdate.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void printOrderUpdateMethod(
			HttpServletResponse response,
			PrintOrder printOrder,
			Model model, HttpServletRequest request) throws IOException {
		logger.info("poupdate.do : " + printOrder);
		
		int result = printOrderService.poupdate(printOrder);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.append("ok");
		}
		
		out.flush();
		out.close();
	}
	
	//발주 삭제 요청 처리용
//	@RequestMapping('podelete.do')
//	public String printOrderDeleteMethod() {
//		
//	}
}
