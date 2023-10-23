package com.erl.pageflow.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.erl.pageflow.book.model.service.BookService;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

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
	public String bkInsertMethod(HttpServletRequest request, Model model) {
		String[] bookNames = request.getParameterValues("bookName");
		String[] categorys = request.getParameterValues("category");
		String[] writerIds = request.getParameterValues("writerId");
		String[] isbns = request.getParameterValues("isbn");
		String[] bookPrices = request.getParameterValues("bookPrice");
		String[] engravings = request.getParameterValues("engraving");
		String[] bookStates = request.getParameterValues("bookState");

		// 주문번호 생성
		int bookId = bookService.selectMaxBookId() + 1;
		
		ArrayList<Book> books = new ArrayList<>();
		for(int i = 0; i < bookNames.length; i++) {
			Book book = new Book();
			
			book.setBookId(bookId);
			book.setBookName(bookNames[i]);
			book.setCategory(categorys[i]);
			book.setWriterId(Integer.parseInt(writerIds[i]));
			book.setIsbn(isbns[i]);
			book.setBookPrice(Integer.parseInt(bookPrices[i]));
			book.setEngraving(engravings[i]);
			book.setBookState(bookStates[i]);
			
			books.add(book);
		}
		
		for(Book book : books) {
			if(bookService.insertBook(book) == 0) {
				model.addAttribute("message", "도서 등록 실패!");
				return "common/error";
			}
		}
		return "redirect:bklist.do";
	}
	
	// 도서 정보 삭제 요청 처리
	@RequestMapping(value="bkdelete.do", method=RequestMethod.POST)
	public String bookDeleteMethod(@RequestParam("IDs") int[] bookIDs, Model model) {
		logger.info("bkdelete.do : " + bookIDs);
		
		for(int bookId : bookIDs) {
			if(bookService.deleteBook(bookId) == 0) {
				model.addAttribute("message", bookId + "번 도서 정보 삭제 실패!");
				return "common/error";
			}
		}
		return "redirect:bklist.do";
	}
	
	// 도서 정보 수정 요청 처리
	@RequestMapping(value="bkupdate.do", method=RequestMethod.POST)
	public void bookUpdateMethod(Book book, HttpServletResponse response) throws IOException {
		logger.info("bkupdate.do : " + book);
		
		String returnStr = null;
		if(bookService.updateBook(book) > 0) {
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
	
	// 도서 날짜 조회
	@RequestMapping("bklistdate.do")
	public String selectBookByDate(Search search,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, 
			Model model) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = bookService.selectBookCountByDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "bklistdate.do");
		
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Book> list = bookService.selectBookByDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {

			model.addAttribute("bookList", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "publish/book_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
	}
	
	// 키워드로 도서현황 검색
	@RequestMapping(value="bklistkwd.do", method={RequestMethod.GET, RequestMethod.POST})
	public String bookListByKeyword(Search search,
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr,
			Model model) {
		
		logger.info("bklistkwd.do : searchType=" + searchType);
		logger.info("bklistkwd.do : " + search);
		logger.info("bklistkwd.do : page=" + page + ", limit=" + limitStr);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = 0;
		
		switch(searchType) {
		case "book":
			listCount = bookService.selectBookCountByBook(search);
			break;
		case "category":
			listCount = bookService.selectBookCountByCategory(search);
			break;
		case "writer":
			listCount = bookService.selectBookCountByWriter(search);
			break;
		}
		
		Paging paging = new Paging(listCount, currentPage, limit, "bklistkwd.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Book> list = null;
		
		switch(searchType) {
		case "book":
			list = bookService.selectBookByBook(search);
			break;
		case "category":
			list = bookService.selectBookByCategory(search);
			break;
		case "writer":
			list = bookService.selectBookByWriter(search);
			break;
		}
		
		if(list != null && list.size() > 0) {
			
			model.addAttribute("bookList", list);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/book_list";
		} else {
			model.addAttribute("message", "도서현황 " + search.getKeyword() + " 검색 실패");
			return "common/error";
		}
	}
}
