package com.erl.pageflow.sales.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
import com.erl.pageflow.sales.model.service.SalesService;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.Rank;
import com.erl.pageflow.sales.model.vo.Sales;
import com.erl.pageflow.sales.model.vo.SalesStatistics;

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
		return "redirect:statslist.do";
	}
	
	@RequestMapping("moverank.do")
	public String moveBookRankingPage() {
		return "redirect:runCrawling.do";
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
				bo.calcTotalPrice();
			}
	
			model.addAttribute("list", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "sales/border_list";
		} else {
			model.addAttribute("message", "주문현황 " + search.getKeyword() + " 검색 실패");
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
	
	// 판매 정보 등록 요청 처리
	@RequestMapping(value="ssinsert.do", method=RequestMethod.POST)
	public String salesInsertMethod(HttpServletRequest request, Model model) {
		String[] orderIds = request.getParameterValues("orderId");
		String[] bookIds = request.getParameterValues("bookId");
		String[] collectedAmounts = request.getParameterValues("collectedAmount");
		
		logger.info("ssinsert.do ---------------------------- START");
		logger.info("orderIds : " + Arrays.toString(orderIds));
		logger.info("bookIds : " +  Arrays.toString(bookIds));
		logger.info("collectedAmounts : " + Arrays.toString(collectedAmounts));
		logger.info("ssinsert.do ---------------------------- END");
		
		ArrayList<Sales> salesList = new ArrayList<>();
		for(int i = 0; i < orderIds.length; i++) {
			Sales sales = new Sales();
			
			sales.setOrderId(Integer.parseInt(orderIds[i]));
			sales.setBookId(Integer.parseInt(bookIds[i]));
			sales.setCollectedAmount(Integer.parseInt(collectedAmounts[i]));
			
			salesList.add(sales);
		}
		
		for(Sales sales : salesList) {
			if(salesService.insertSales(sales) == 0) {
				model.addAttribute("message", sales + "판매 등록 실패!");
				return "common/error";
			}
		}
		
		return "redirect:movesales.do";
	}
	
	// 판매 정보 수정 요청 처리(ajax 통신)
	@RequestMapping(value="ssupdate.do", method=RequestMethod.POST)
	public void salesUpdateMethod(Sales sales, HttpServletResponse response) throws IOException {
		logger.info("ssupdate.do : " + sales);
		
		String returnStr = null;
		if(salesService.updateSales(sales) > 0) {
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
	
	// 판매 정보 삭제 요청 처리
	@RequestMapping(value="ssdelete.do", method=RequestMethod.POST)
	public String salesDeleteMethod(@RequestParam("IDs") int[] IDs, Model model) {
		logger.info("ssdelete.do : " + IDs);
		
		for(int salesId : IDs) {
			if(salesService.deleteSales(salesId) == 0) {
				model.addAttribute("message", salesId + "번 판매 정보 삭제 실패!");
				return "common/error";
			}
		}
		
		return "redirect:movesales.do";
	}
	
	// 주문 정보 등록 요청 처리
	@RequestMapping(value="boinsert.do", method=RequestMethod.POST)
	public String bookOrderInsertMethod(HttpServletRequest request, Model model) {
		String[] bookIds = request.getParameterValues("bookId");
		String[] clientIds = request.getParameterValues("clientId");
		String[] empIds = request.getParameterValues("empId");
		String[] orderQuantities = request.getParameterValues("orderQuantity");
		String[] states = request.getParameterValues("state");
		
		logger.info("boinsert.do ---------------------------- START");
		logger.info("bookIds : " + Arrays.toString(bookIds));
		logger.info("clientIds : " +  Arrays.toString(clientIds));
		logger.info("empIds : " + Arrays.toString(empIds));
		logger.info("orderQuantities : " + Arrays.toString(orderQuantities));
		logger.info("states : " + Arrays.toString(states));
		logger.info("boinsert.do ---------------------------- END");
		
		// 주문번호 생성
		
		int orderId = salesService.selectMaxOrderId() + 1;
		
		ArrayList<BookOrder> bookOrders = new ArrayList<>();
		for(int i = 0; i < bookIds.length; i++) {
			BookOrder bookOrder = new BookOrder();
			
			bookOrder.setOrderId(orderId);
			bookOrder.setBookId(Integer.parseInt(bookIds[i]));
			bookOrder.setClientId(Integer.parseInt(clientIds[i]));
			bookOrder.setEmpId(Integer.parseInt(empIds[i]));
			bookOrder.setOrderQuantity(Integer.parseInt(orderQuantities[i]));
			bookOrder.setState(states[i]);
			
			bookOrders.add(bookOrder);
		}
		
		for(BookOrder bookOrder : bookOrders) {
			if(salesService.insertBookOrder(bookOrder) == 0) {
				model.addAttribute("message", "주문 등록 실패!");
				return "common/error";
			}
		}
		
		return "redirect:movebolist.do";
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
	public String bookOrderDeleteMethod(@RequestParam("IDs") String[] IDs, Model model) {
		logger.info("bodelete.do : " + IDs);
		
		for(String id : IDs) {
			String[] splitId = id.split("_");
			BookOrder bookOrder = new BookOrder();
			bookOrder.setOrderId(Integer.parseInt(splitId[0]));
			bookOrder.setBookId(Integer.parseInt(splitId[1]));
			
			if(salesService.deleteBookOrder(bookOrder) == 0) {
				model.addAttribute("message", bookOrder.getOrderId() + "-" + bookOrder.getBookId() + "번 주문 정보 삭제 실패!");
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
	
	// 매출통계 조회 요청 처리
	@RequestMapping("statslist.do")
	public String salesStatisticsMethod(
			@RequestParam(name="year", required=false) String yearStr,
			Model model) {
		
		int year = (yearStr != null) ? Integer.parseInt(yearStr) : LocalDate.now().getYear();
		ArrayList<Integer> years = salesService.selectYears();
		ArrayList<SalesStatistics> list = salesService.selectSalesForStats(year);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("year", year);
			model.addAttribute("years", years);
			return "sales/sales_stats";
		} else {
			model.addAttribute("message", "매출통계 조회 실패");
			return "common/error";
		}
	}
	
	// 매출통계 조회 요청 처리
	@RequestMapping("showrank.do")
	public String showRankMethod(Model model) {
		
		ArrayList<Rank> list = salesService.selectRank();
		
		HashMap<String, Integer> categories = new HashMap<>();
		
		for(Rank book : list) {
			categories.put(book.getCategory(), categories.getOrDefault(book.getCategory(), 0) + 1);
		}
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("map", categories);
			return "sales/trend_list";
		} else {
			model.addAttribute("message", "거래처 조회 실패");
			return "common/error";
		}
	}
	
}
