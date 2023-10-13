package com.erl.pageflow.sales.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erl.pageflow.board.controller.BoardController;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.service.SalesService;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;

@Controller
public class SalesController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private SalesService salesService;
	
	//**************뷰페이지 이동****************
	
	// 주문현황 페이지 이동
	@RequestMapping("moveboList.do")
//	@RequestMapping("border_list.do")
	public String moveBookOrderList(Model model) {
		
		// 첫 이동 시 해당 월의 데이터 조회
		LocalDate today = LocalDate.now();
		model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("end", today); // 오늘까지
		
		return "redirect:bolistDate.do";
	}
	
	// 주문등록 페이지 이동
	@RequestMapping("moveboInput.do")
	public String moveBookOrderInputPage() {
		return "sales/border_input";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	// 날짜로 주문현황 검색
	@RequestMapping("bolistDate.do")
	public String bookOrderList(Search search, Model model) {
		
		ArrayList<BookOrder> list = salesService.selectBookOrderDate(search);
		for(BookOrder bo : list) {
			Book book = salesService.selectBook(bo.getBookId());
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
}
