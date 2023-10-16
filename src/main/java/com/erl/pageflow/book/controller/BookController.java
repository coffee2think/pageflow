package com.erl.pageflow.book.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.book.model.service.BookService;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	// ****************** 뷰페이지 이동 **********************
	
	
	// 도서 등록 이동
	@RequestMapping("bkinsert.do")
	public String moveBookInput() {
		return "publish/book_input";
	}
	
	
	// ****************** 요청 받아서 서비스로 넘기는 메소드 **********************
	// 도서 리스트 조회
	@RequestMapping("bklist.do")
	public String selectBookListMethod(@RequestParam(name = "page", required = false) String page,
				@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		int listCount = bookService.selectBookListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "bklist.do");
		paging.calculator();
		
		ArrayList<Book> list = bookService.selectBookList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/book_list";
		} else {
			model.addAttribute("message", "도서 목록 조회 실패!");
			return "common/error";
		}
	}
}
