package com.erl.pageflow.book.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.book.model.service.BookService;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.contract.model.vo.Contract;
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
				model.addAttribute("message", bookId + "번 주문 정보 삭제 실패!");
				return "common/error";
			}
		}
		
		return "redirect:bklist.do";
	}
	
//	@RequestMapping(value = "storedelete.do", method = RequestMethod.POST)
//	@ResponseBody
//	public void deleteStore(@RequestParam(name = "selectedStoreIds") String selectedStoreIds,
//			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
//
//		int count = 0;
//		String[] storeArray = selectedStoreIds.split(",");
//
//		for (int i = 0; i < storeArray.length; i++) {
//
//			int sId = Integer.parseInt(storeArray[i].toString());
//			logger.info("sId  : "+ sId);
//			if (storeService.deleteInventory(sId) > 0) {
//				logger.info("deleteInventory : "+ sId);
//				if (storeService.deleteStore(sId) > 0) {
//					logger.info("deleteStore : "+ sId);
//					count++;
//				} else {
//					model.addAttribute("message", sId + "번 입고 삭제 실패");
//				}
//			} else {
//				model.addAttribute("message", sId + "번 재고 삭제 실패");
//			}
//		}
//
//		if (count >= storeArray.length) {
//			response.getWriter().append(String.valueOf(count)).flush();
//		}
//
//	}
}
