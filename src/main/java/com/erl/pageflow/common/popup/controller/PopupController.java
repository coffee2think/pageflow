package com.erl.pageflow.common.popup.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.popup.model.service.PopupService;
import com.erl.pageflow.sales.controller.SalesController;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Controller
public class PopupController {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired
	private PopupService popupService;
	
	// 팝업창 도서 정보 조회
	@RequestMapping("popupBook.do")
	@ResponseBody // 리턴하는 jsonString을 response body에 담아서 보낸다는 의미
	public String popupBookMethod(
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="keyword") String keyword,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupBook.do : searchType=" + searchType + ", keyword=" + keyword);
		
		// 타입에 따라 값 초기화
		int currentPage = 1;
		int limit = 10;
		
		int listCount = 0;
		ArrayList<BookWithStock> list = null;
		
		switch(searchType) {
		case "bookId":
			int bookId = Integer.parseInt(keyword);
			listCount = popupService.selectBookCountById(bookId);
			list = popupService.selectBookById(bookId);
			break;
		case "bookName":
			String bookName = keyword;
			listCount = popupService.selectBookCountByName(bookName);
			list = popupService.selectBookByName(bookName);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내는 처리 :
		// 전송용 json 객체 생성
		JSONObject sendJson = new JSONObject();
		// list 담을 json 배열 객체 생성
		JSONArray jarr = new JSONArray();
		
		// list를 json 배열에 옮기기
		for(BookWithStock book : list) {
			// notice 값을 저장할 json 객체 생성
			JSONObject job = new JSONObject();
			
			job.put("bookId", book.getBookId());
			// 문자열에 한글이 있다면, 반드시 인코딩해서 저장해야함
			job.put("bookName", URLEncoder.encode(book.getBookName(), "utf-8"));
			job.put("bookPrice", book.getBookPrice());
			job.put("stock", book.getStock());
			
			// jarr에 job 저장함
			jarr.add(job);
		}
		
		// 전송용 객체에 jarr 저장
		sendJson.put("list", jarr);
		
		return sendJson.toJSONString();
	}
	
	// 팝업창 인쇄소 정보 조회
	@RequestMapping("popupPrintOffice.do")
	@ResponseBody // 리턴하는 jsonString을 response body에 담아서 보낸다는 의미
	public String popupMethod(
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="keyword") String keyword,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupPrintOffice.do : searchType=" + searchType + ", keyword=" + keyword);
		
		// 타입에 따라 값 초기화
		int currentPage = 1;
		int limit = 10;
		
		int listCount = 0;
		ArrayList<PrintOffice> list = null;
		
		switch(searchType) {
		case "clientId":
			int clientId = Integer.parseInt(keyword);
			listCount = popupService.selectPrintOfficeCountById(clientId);
			list = popupService.selectPrintOfficeById(clientId);
			break;
		case "clientName":
			String clientName = keyword;
			listCount = popupService.selectPrintOfficeCountByName(clientName);
			list = popupService.selectPrintOfficeByName(clientName);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내는 처리 :
		// 전송용 json 객체 생성
		JSONObject sendJson = new JSONObject();
		// list 담을 json 배열 객체 생성
		JSONArray jarr = new JSONArray();
		
		// list를 json 배열에 옮기기
		for(PrintOffice printOffice : list) {
			// notice 값을 저장할 json 객체 생성
			JSONObject job = new JSONObject();
			
			job.put("clientId", printOffice.getClientId());
			// 문자열에 한글이 있다면, 반드시 인코딩해서 저장해야함
			job.put("clientName", URLEncoder.encode(printOffice.getClientName(), "utf-8"));
			job.put("clientAddress", printOffice.getClientAddress());
			
			// jarr에 job 저장함
			jarr.add(job);
		}
		
		// 전송용 객체에 jarr 저장
		sendJson.put("list", jarr);
		
		return sendJson.toJSONString();
	}
}
