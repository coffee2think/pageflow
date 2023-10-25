package com.erl.pageflow.printOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

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
import com.erl.pageflow.printOrder.model.service.PrintOrderService;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;

@Controller
public class PrintOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(PrintOrderController.class);
	
	@Autowired
	private PrintOrderService printOrderService;
	
	//*************************** 뷰 페이지 이동 *****************************
	
	//발주현황 리스트 조회
	@RequestMapping("polist.do")
	public String printOrderList(
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
		
		int listCount = printOrderService.selectPrintOrderListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "polist.do");
		paging.calculator();
		
		ArrayList<PrintOrder> list = printOrderService.selectPrintOrderList(paging);
	
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", "정산 목록 조회 실패!");
			return "common/error";
		}	
		
	}
	
	//시작날짜로 정산현황 검색 처리용
	@RequestMapping("polistSdate.do")
	public String selectPrintCalcBySDate(Search search,
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
		
		int listCount = printOrderService.selectPrintOrderCountBySDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "polistSdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<PrintOrder> list = printOrderService.selectPrintOrderBySDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
		
	}
	
	//마감날짜로 정산현황 검색 처리용
	@RequestMapping("polistEdate.do")
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
		
		int listCount = printOrderService.selectPrintOrderCountByEDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "polistEdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<PrintOrder> list = printOrderService.selectPrintOrderByEDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
	}
	
	//출간날짜로 정산현황 검색 처리용
	@RequestMapping("polistPdate.do")
	public String selectPrintCalcByPDate(Search search,
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
		
		int listCount = printOrderService.selectPrintOrderCountByPDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "polistPdate.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<PrintOrder> list = printOrderService.selectPrintOrderByPDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
		
	}
	//키워드[인쇄코드,인쇄소명,도서코드,도서명]로 발주현황 검색
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
		
		
		switch(searchType) {
		case "printName":
			listCount = printOrderService.selectPrintOrderCountByPrintName(search);
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
		case "printName":
			list = printOrderService.selectPrintOrderByPrintName(search);
			break;
		case "bookName":
			list = printOrderService.selectPrintOrderByBookName(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "print/porder_list";
		} else {
			model.addAttribute("message", search.getKeyword() + "발주현황 검색 실패");
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
	@RequestMapping(value="podelete.do", method= RequestMethod.POST)
	public String deletePrintOrderMethod(@RequestParam("IDs") int[] orderIDs, Model model) {
		logger.info("podelete.do : " + orderIDs);
		
		if(orderIDs != null) {
			for(int orderId : orderIDs) {
				
				if (printOrderService.deletePrintOrder(orderId) == 0) {
					model.addAttribute("message", orderId + "번 발주 정보 삭제 실패!");
					return "common/error";
				}
			}
		}

		return "redirect:polist.do";
	}
}
