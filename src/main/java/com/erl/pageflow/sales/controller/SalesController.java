package com.erl.pageflow.sales.controller;

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
	
	// 판매등록 페이지 이동
	@RequestMapping("movessinput.do")
	public String moveSalesInputPage() {
		return "sales/sales_input";
	}
	
	// 거래처 현황 페이지 이동
	@RequestMapping("moveclient.do")
	public String moveClientPage(
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limit,
			Model model) {
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		
		return "redirect:clientlist.do";
	}
	
	// 거래처 등록 페이지 이동
	@RequestMapping("moveclinput.do")
	public String moveClientInputPage() {
		return "sales/client_input";
	}
	
	// 매출현황 페이지 이동
	@RequestMapping("movestats.do")
	public String moveStatistics() {
		return "sales/sales_stats";
	}
	
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	// 날짜로 주문현황 검색
	@RequestMapping("bolistdate.do")
	public String bookOrderListByDate(Search search,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("bolistdate.do : page=" + page + ", limitStr=" + limitStr);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = salesService.selectBookOrderCountByDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "bolistdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<BookOrder> list = salesService.selectBookOrderByDate(search);
		
		if(list != null && list.size() > 0) {
			for(BookOrder bo : list) {
				bo.calcTotalPrice();
			}
	
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "sales/border_list";
		} else {
			model.addAttribute("message", "주문현황 조회 실패");
			return "common/error";
		}
	}
	
	// 키워드로 주문현황 검색
	@RequestMapping(value="bolistkw.do", method={RequestMethod.GET, RequestMethod.POST})
	public String bookOrderListByKeyword(Search search,
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("bolistkw.do : searchType=" + searchType);
		logger.info("bolistkw.do : " + search);
		logger.info("bolistkw.do : page=" + page + ", limit=" + limitStr);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = 0;
		
		switch(searchType) {
		case "book":
			listCount = salesService.selectBookOrderCountByBook(search);
			break;
		case "bookStore":
			listCount = salesService.selectBookOrderCountByBookStore(search);
			break;
		case "location":
			listCount = salesService.selectBookOrderCountByLocation(search);
			break;
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "bolistkw.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<BookOrder> list = null;
		
		switch(searchType) {
		case "book":
			list = salesService.selectBookOrderByBook(search);
			break;
		case "bookStore":
			list = salesService.selectBookOrderByBookStore(search);
			break;
		case "location":
			list = salesService.selectBookOrderByLocation(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			for(BookOrder bo : list) {
				BookForSales book = salesService.selectBook(bo.getBookId());
				BookStore bookStore = salesService.selectBookStore(bo.getClientId());
				
				bo.setBookName(book.getBookName());
				bo.setBookPrice(book.getBookPrice());
				bo.calcTotalPrice();
				bo.setBookStoreName(bookStore.getClientName());
			}
	
			model.addAttribute("list", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "sales/border_list";
		} else {
			model.addAttribute("message", "주문현황 조회 실패");
			return "common/error";
		}
	}
	
	// 날짜로 판매현황 검색
	@RequestMapping("sslistdate.do")
	public String salesListByDate(Search search,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("sslistdate.do : page=" + page + ", limitStr=" + limitStr);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = salesService.selectSalesCountByDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "sslistdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Sales> list = salesService.selectSalesListByDate(search);
		
		if(list != null && list.size() > 0) {
			for(Sales ss : list) {
				ss.calcTotalPrice();
			}
	
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "sales/sales_list";
		} else {
			model.addAttribute("message", "판매현황 조회 실패");
			return "common/error";
		}
	}
	
	// 주문 정보 등록 요청 처리
	@RequestMapping(value="boinsert.do", method=RequestMethod.POST)
	public String bookOrderInsertMethod(BookOrder bookOrder, Model model) {
		logger.info("boinsert.do : " + bookOrder);
		
		if(salesService.insertBookOrder(bookOrder) > 0) {
			return "redirect:movebolist.do";
		} else {
			model.addAttribute("message", "주문 등록 실패!");
			return "common/error";
		}
	}
	
	// 주문 정보 수정 요청 처리(ajax 통신)
	@RequestMapping(value="boupdate.do", method=RequestMethod.POST)
	public void bookOrderUpdateMethod(BookOrder bookOrder, HttpServletResponse response) throws IOException {
		logger.info("boupdate.do : " + bookOrder);
		
		String returnStr = null;
		if(salesService.updateBookOrder(bookOrder) > 0) {
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
	
	// 거래처 정보 삭제 요청 처리
	@RequestMapping(value="bodelete.do", method=RequestMethod.POST)
	public String bookOrderDeleteMethod(@RequestParam("IDs") int[] orderIDs, Model model) {
		logger.info("bodelete.do : " + orderIDs);
		
		for(int orderId : orderIDs) {
			if(salesService.deleteBookOrder(orderId) == 0) {
				model.addAttribute("message", orderId + "번 주문 정보 삭제 실패!");
				return "common/error";
			}
		}
		
		return "redirect:movebolist.do";
	}
	
	// 거래처 현황 조회 처리용
	@RequestMapping("clientlist.do")
	public String clientList(
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("clientlist.do : page=" + page + ", limitStr=" + limitStr);
		
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
			model.addAttribute("message", "거래처 조회 실패");
			return "common/error";
		}
	}
	
	// 날짜로 주문현황 검색
	@RequestMapping("cllistdate.do")
	public String clientListByDate(Search search,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			@RequestParam(name="dateType") String dateType,
			Model model) {
		
		logger.info("cllistdate.do : page=" + page + ", limitStr=" + limitStr + ", dateType=" + dateType);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = 0; 
		if(dateType.equals("startDate")) {
			listCount = salesService.selectClientCountByStartDate(search);
		} else {
			listCount = salesService.selectClientCountByEndDate(search);
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "cllistdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Client> list = null;
		if(dateType.equals("startDate")) {
			list = salesService.selectClientByStartDate(search);
		} else {
			list = salesService.selectClientByEndDate(search);
		}
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			model.addAttribute("dateType", dateType);
			
			return "sales/client_list";
		} else {
			model.addAttribute("message", "주문현황 조회 실패");
			return "common/error";
		}
	}
	
	// 거래처 정보 등록 요청 처리
	@RequestMapping(value="clinsert.do", method=RequestMethod.POST)
	public String clientInsertMethod(Client client, Model model) {
		logger.info("clinsert.do : " + client);
		
		if(salesService.insertClient(client) > 0) {
			return "redirect:moveclient.do";
		} else {
			model.addAttribute("message", "거래처 등록 실패!");
			return "common/error";
		}
	}
	
	// 거래처 정보 수정 요청 처리(ajax 통신)
	@RequestMapping(value="clupdate.do", method=RequestMethod.POST)
	public void clientUpdateMethod(Client client, HttpServletResponse response) throws IOException {
		logger.info("clupdate.do : " + client);
		
		String returnStr = null;
		if(salesService.updateClient(client) > 0) {
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
	
	// 거래처 정보 삭제 요청 처리
	@RequestMapping(value="cldelete.do", method=RequestMethod.POST)
	public String clientDeleteMethod(@RequestParam("IDs") int[] clientIDs, Model model) {
		logger.info("cldelete.do : " + clientIDs);
		
		if(clientIDs != null) {
			for(int clientId : clientIDs) {
				if(salesService.deleteClient(clientId) == 0) {
					model.addAttribute("message", clientId + "번 거래처 삭제 실패!");
					return "common/error";
				}
			}
		}
		
		return "redirect:moveclient.do";
	}
}
