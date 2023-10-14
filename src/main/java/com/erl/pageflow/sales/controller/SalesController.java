package com.erl.pageflow.sales.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.board.controller.BoardController;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.service.SalesService;
import com.erl.pageflow.sales.model.vo.BookForSales;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.Sales;

@Controller
public class SalesController {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired
	private SalesService salesService;
	
	//**************뷰페이지 이동****************
	
	// 주문현황 페이지 이동
	@RequestMapping("movebolist.do")
	public String moveBookOrderPage(Model model) {
		
		// 첫 이동 시 해당 월의 데이터 조회
		LocalDate today = LocalDate.now();
//		model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1)); // 일주일 전부터
		model.addAttribute("end", today); // 오늘까지
		
		return "redirect:bolistdate.do";
	}
	
	// 주문등록 페이지 이동
	@RequestMapping("moveboinput.do")
	public String moveBookOrderInputPage() {
		return "sales/border_input";
	}
	
	// 판매현황 페이지 이동
	@RequestMapping("movesales.do")
	public String moveSalesPage(Model model) {
		
		// 첫 이동 시 해당 월의 데이터 조회
		LocalDate today = LocalDate.now();
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1)); // 일주일 전부터
		model.addAttribute("end", today); // 오늘까지
		
		return "redirect:sslistdate.do";
	}
	
	// 거래처 현황 페이지 이동
	@RequestMapping("moveclient.do")
	public String moveClientPage(Model model) {
		return "redirect:clientlist.do";
	}
	
	// 거래처 등록 페이지 이동
	@RequestMapping("moveclinput.do")
	public String moveClientInputPage() {
		return "sales/client_input";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	// 날짜로 주문현황 검색
	@RequestMapping("bolistdate.do")
	public String bookOrderList(Search search, Model model) {
		
		ArrayList<BookOrder> list = salesService.selectBookOrderByDate(search);
		
		for(BookOrder bo : list) {
			BookForSales book = salesService.selectBook(bo.getBookId());
			BookStore bookStore = salesService.selectBookStore(bo.getClientId());
			
			bo.setBookName(book.getBookName());
			bo.setBookPrice(book.getBookPrice());
			bo.calcTotalPrice();
			bo.setBookStoreName(bookStore.getClientName());
		}

		model.addAttribute("list", list);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		
		return "sales/border_list";
	}
	
	// 날짜로 주문현황 검색
	@RequestMapping("sslistdate.do")
	public String salesList(Search search, Model model) {
		
		ArrayList<Sales> list = salesService.selectSalesByDate(search);
		
		for(Sales ss : list) {
			BookForSales book = salesService.selectBook(ss.getBookId());
			BookStore bookStore = salesService.selectBookStore(ss.getClientId());
			
			ss.setBookName(book.getBookName());
			ss.setBookPrice(book.getBookPrice());
			ss.calcTotalPrice();
			ss.setBookStoreName(bookStore.getClientName());
		}

		model.addAttribute("list", list);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		
		return "sales/sales_list";
	}
	
	// 거래처 현황 조회 처리용
	@RequestMapping("clientlist.do")
	public String clientList(
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = salesService.selectClientListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "clientlist.do");
		paging.calculator();
		
		ArrayList<Client> list = salesService.selectClientList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "sales/client_list";
		} else {
			model.addAttribute("message", "거래처 조회 실패 : 등록된 거래처가 없습니다");
			return "common/error";
		}
	}
	
	@RequestMapping(value="boinsert.do", method=RequestMethod.POST)
	public String bookOrderInsertMethod(BookOrder bookOrder, Model model) {
		logger.info("boinsert.do" + bookOrder);
		
		if(salesService.insertBookOrder(bookOrder) > 0) {
			return "redirect:movebolist.do";
		} else {
			model.addAttribute("message", "주문 등록 실패!");
			return "common/error";
		}
	}
	
	@RequestMapping(value="boupdate.do", method=RequestMethod.POST)
	public String bookOrderUpdateMethod(BookOrder bookOrder, Model model) {
		logger.info("boupdate.do" + bookOrder);
		
		if(salesService.updateBookOrder(bookOrder) > 0) {
			return "redirect:movebolist.do";
		} else {
			model.addAttribute("message", "주문 수정 실패!");
			return "common/error";
		}
	}
}
