package com.erl.pageflow.printOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printOrder.model.service.PrintOrderService;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Controller
public class PrintOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(PrintOrderController.class);
	
	@Autowired
	private PrintOrderService printOrderService;
	
	//*************************** 뷰 페이지 이동 *****************************
	
	//발주현황 페이지 이동 처리용
	@RequestMapping("polist.do")
	
	public String movePrintOrderPage(@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		
		
		// 첫 이동시 해당 기간 데이터 조회
		LocalDate today = LocalDate.now();
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1));	//1주일 전부터
		model.addAttribute("end", today);	//오늘까지
		model.addAttribute("page", page);
		model.addAttribute("limit", limitStr);
		
		return "redirect:polistdate.do";
	}
	
	// 날짜로 주문현황 검색 처리용
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
			PrintOffice poffice  = printOrderService.selectPrintOffice(po.getClientId());
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
	//키워드[인쇄코드,인쇄소명,도서코드,도서명]로 발주현황 검색		//도서명,서점명,지역
	@RequestMapping(value="pokeyword.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String selectPrintOrderByKeyword(Search search,
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("pokeyword.do : searchType" + searchType);
		logger.info("pokeyword.do : page" + page);
		logger.info("pokeyword.do : limit" + limitStr);
		
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
		
		//스위치문을 쓰는 이유 : ?
		switch(searchType) {
		case "clientId":
			listCount = printOrderService.selectPrintOrderCountByClientId(search);
			break;
		case "printName":
			listCount = printOrderService.selectPrintOrderCountByPrintName(search);
			break;
		case "bookId":
			listCount = printOrderService.selectPrintOrderCountByBookId(search);
			break;
		case "bookName":
			listCount = printOrderService.selectPrintOrderCountByBookName(search);
			break;	
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "pokeyword.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		
		ArrayList<PrintOrder> list = null;
		
		switch(searchType) {
		case "clientId":
			list = printOrderService.selectPrintOrderByClientId(search);
			break;
		case "printName":
			list = printOrderService.selectPrintOrderByPrintName(search);
			break;
		case "bookId":
			list = printOrderService.selectPrintOrderByBookId(search);
			break;
		case "bookName":
			list = printOrderService.selectPrintOrderByBookName(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", "발주현황 조회 실패");
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
		public String printOrderInsertMethod(Model model, HttpServletRequest request) {
			
			String[] clientIds = request.getParameterValues("clientId");
			String[] empIds = request.getParameterValues("empId");
			String[] empNames = request.getParameterValues("empName");
			String[] orderDates = request.getParameterValues("orderDate");
			String[] endDates = request.getParameterValues("endDate");
			String[] pubDates = request.getParameterValues("pubDate");
			String[] bookIds = request.getParameterValues("bookId");
			String[] units = request.getParameterValues("unit");
			String[] quantities = request.getParameterValues("quantity");
			String[] price = request.getParameterValues("price");
			String[] amounts = request.getParameterValues("amount");
			String[] states = request.getParameterValues("state");
			
			logger.info("poinsert.do ---------------------------- START");
			logger.info("clientIds : " + Arrays.toString(clientIds));
			logger.info("empIds : " + Arrays.toString(empIds));
			logger.info("empNames : " + Arrays.toString(empNames));
			logger.info("orderDates : " + Arrays.toString(orderDates));
			logger.info("endDates : " + Arrays.toString(endDates));
			logger.info("pubDates : " + Arrays.toString(pubDates));
			logger.info("bookIds : " + Arrays.toString(bookIds));
			logger.info("units : " + Arrays.toString(units));
			logger.info("quantities : " + Arrays.toString(quantities));
			logger.info("price : " + Arrays.toString(price));
			logger.info("amounts : " + Arrays.toString(amounts));
			logger.info("states : " + Arrays.toString(states));
			logger.info("poinsert.do ---------------------------- END");
			
			int orderId = printOrderService.selectMaxPrintOrderId() + 1;
			
			ArrayList<PrintOrder> list = new ArrayList<>();
			for(int i = 0; i < clientIds.length; i++) {
				PrintOrder printOrder = new PrintOrder();
				
				printOrder.setOrderId(orderId);
				printOrder.setEmpName(empNames[i]);
				printOrder.setEmpId(Integer.parseInt(empIds[i]));
				printOrder.setClientId(Integer.parseInt(clientIds[i]));
				printOrder.setOrderDate(Date.valueOf(orderDates[i]));
				printOrder.setEndDate(Date.valueOf(endDates[i]));
				printOrder.setPubDate(Date.valueOf(pubDates[i]));
				printOrder.setBookId(Integer.parseInt(bookIds[i]));
				printOrder.setUnit(units[i]);
				printOrder.setQuantity(Integer.parseInt(quantities[i]));
				printOrder.setPrice(Integer.parseInt(price[i]));
				printOrder.setAmount(Integer.parseInt(amounts[i]));
				printOrder.setState(states[i]);
				
				list.add(printOrder);
				
			}
			
			for(PrintOrder printOrder : list ) {
				if(printOrderService.insertPrintOrder(printOrder) == 0) {
					model.addAttribute("message", "발주 등록 실패!");
					return "common/error.jsp";
				}
			}
			
			return "redirect:polist.do";
			
		}
	
	//발주 수정 요청 처리용
	@RequestMapping(value="poupdate.do", method= RequestMethod.POST)
	public void printOrderUpdateMethod(HttpServletResponse response, PrintOrder printOrder) throws IOException {
		logger.info("poupdate.do : " + printOrder);
		
		int result = printOrderService.updatePrintOrder(printOrder);
		
		String returnStr = null;
		if(printOrderService.updatePrintOrder(printOrder) > 0) {
			returnStr = "success";
		} else {
			returnStr = "fail";
		}
		
		logger.info("returnStr : " + returnStr);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		out.append(returnStr);
		
		
		out.flush();
		out.close();
	}
	
	//발주 삭제 요청 처리용
	@RequestMapping(value= "podelete.do", method=RequestMethod.POST)
	public String deletePrintOrderMethod(@RequestParam("IDs") int[] IDs, Model model) {
		logger.info("podelete.do : " + IDs);
		
		for(int orderId : IDs) {
			
			
			
		if (printOrderService.deletePrintOrder(orderId) == 0) {
			model.addAttribute("message", orderId + "번 발주 정보 삭제 실패!");
			return "common/error";
		}
		
	}
	
		return "redirect:podelete.do";
	}
}
