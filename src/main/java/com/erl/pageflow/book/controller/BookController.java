package com.erl.pageflow.book.controller;

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

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.book.model.service.BookService;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.sales.model.vo.BookOrder;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	// ****************** 뷰페이지 이동 **********************
	
	// 도서 등록 페이지 이동
	@RequestMapping("movebkinsert.do")
	public String moveBookInsertPage() {
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
			model.addAttribute("bookList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/book_list";
		} else {
			model.addAttribute("message", "도서 목록 조회 실패!");
			return "common/error";
		}
	}
	
	// 도서 정보 등록 요청 처리
	@RequestMapping(value="bkinsert.do", method=RequestMethod.POST)
	public String bookInsertMethod(Book book, Model model) {
		logger.info("bkinsert.do : " + book);
		
		if(bookService.insertBook(book) > 0) {
			return "redirect:bklist.do";
		} else {
			model.addAttribute("message", "도서 등록 실패!");
			return "common/error";
		}
	}
}
