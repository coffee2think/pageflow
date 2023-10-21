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
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.common.popup.model.service.PopupService;
import com.erl.pageflow.employee.model.vo.Employee;
import com.erl.pageflow.sales.controller.SalesController;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.PrintOffice;
import com.erl.pageflow.sales.model.vo.Storage;

@Controller
public class PopupController {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired
	private PopupService popupService;
	
	// 팝업창 도서 정보 조회
	@RequestMapping("popupBook.do")
	@ResponseBody // response body에 담아서 보냄
	public String popupBookMethod(Search search,
			@RequestParam(name="popupPage", required=false) String page,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupBook.do : " + search);
		
		// 타입에 따라 값 초기화
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;
		
		// 페이징 처리를 위한 개수 조회
		int listCount = 0;
		switch(search.getSearchType()) {
		case "bookId":
			listCount = popupService.selectBookCountById(Integer.parseInt(search.getKeyword()));
			break;
		case "bookName":
			listCount = popupService.selectBookCountByName(search.getKeyword());
			break;
		}
		
		// 페이징 처리
		Paging paging = new Paging(listCount, currentPage, limit, "popupBook.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		// 서비스로 목록 요청
		ArrayList<BookWithStock> list = null;
		switch(search.getSearchType()) {
		case "bookId":
			list = popupService.selectBookById(search);
			break;
		case "bookName":
			list = popupService.selectBookByName(search);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내기
		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(BookWithStock book : list) {
			JSONObject job = new JSONObject();
			
			job.put("bookId", book.getBookId());
			job.put("bookName", URLEncoder.encode(book.getBookName(), "utf-8")); // 문자열에 한글이 있을 경우 인코딩 처리
			job.put("bookPrice", book.getBookPrice());
			job.put("stock", book.getStock());
			
			jarr.add(job);
		}
		
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}
	
	// 팝업창 인쇄소 정보 조회
	@RequestMapping("popupPrintOffice.do")
	@ResponseBody // response body에 담아서 보냄
	public String popupPrintOfficeMethod(Search search,
			@RequestParam(name="page", required=false) String page,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupPrintOffice.do : " + search);
		
		// 타입에 따라 값 초기화
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;
		
		// 페이징 처리를 위한 개수 조회
		int listCount = 0;
		switch(search.getSearchType()) {
		case "clientId":
			listCount = popupService.selectPrintOfficeCountById(Integer.parseInt(search.getKeyword()));
			break;
		case "clientName":
			listCount = popupService.selectPrintOfficeCountByName(search.getKeyword());
			break;
		}
		
		// 페이징 처리
		Paging paging = new Paging(listCount, currentPage, limit, "popupPrintOffice.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		// 서비스로 목록 요청
		ArrayList<PrintOffice> list = null;
		switch(search.getSearchType()) {
		case "clientId":
			list = popupService.selectPrintOfficeById(search);
			break;
		case "clientName":
			list = popupService.selectPrintOfficeByName(search);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내기
		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(PrintOffice printOffice : list) {
			JSONObject job = new JSONObject();
			
			job.put("clientId", printOffice.getClientId());
			job.put("clientName", URLEncoder.encode(printOffice.getClientName(), "utf-8"));
			job.put("clientAddress", URLEncoder.encode(printOffice.getClientAddress(), "utf-8"));
			job.put("clientContact", printOffice.getClientContact());
			job.put("eid", printOffice.getEid());
			job.put("clientUrl", printOffice.getClientUrl());
			job.put("manager", printOffice.getManager());
			job.put("managerContact", printOffice.getManagerContact());
			job.put("managerEmail", printOffice.getManagerEmail());
			
			jarr.add(job);
		}
		
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}
	
	// 팝업창 서점 정보 조회
	@RequestMapping("popupBookStore.do")
	@ResponseBody // response body에 담아서 보냄
	public String popupBookStoreMethod(Search search,
			@RequestParam(name="page", required=false) String page,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupBookStore.do : " + search);
		
		// 타입에 따라 값 초기화
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;
		
		// 페이징 처리를 위한 개수 조회
		int listCount = 0;
		switch(search.getSearchType()) {
		case "clientId":
			listCount = popupService.selectBookStoreCountById(Integer.parseInt(search.getKeyword()));
			break;
		case "clientName":
			listCount = popupService.selectBookStoreCountByName(search.getKeyword());
			break;
		}
		
		// 페이징 처리
		Paging paging = new Paging(listCount, currentPage, limit, "popupBookStore.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		// 서비스로 목록 요청
		ArrayList<BookStore> list = null;
		switch(search.getSearchType()) {
		case "clientId":
			list = popupService.selectBookStoreById(search);
			break;
		case "clientName":
			list = popupService.selectBookStoreByName(search);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내기
		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(BookStore bookStore : list) {
			JSONObject job = new JSONObject();
			
			job.put("clientId", bookStore.getClientId());
			job.put("clientName", URLEncoder.encode(bookStore.getClientName(), "utf-8"));
			job.put("clientAddress", URLEncoder.encode(bookStore.getClientAddress(), "utf-8"));
			job.put("clientContact", bookStore.getClientContact());
			job.put("eid", bookStore.getEid());
			job.put("clientUrl", bookStore.getClientUrl());
			job.put("manager", bookStore.getManager());
			job.put("managerContact", bookStore.getManagerContact());
			job.put("managerEmail", bookStore.getManagerEmail());
			
			jarr.add(job);
		}
		
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}
	
	// 팝업창 서점 정보 조회
	@RequestMapping("popupStorage.do")
	@ResponseBody // response body에 담아서 보냄
	public String popupStorageMethod(Search search,
			@RequestParam(name="page", required=false) String page,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupStorage.do : " + search);
		
		// 타입에 따라 값 초기화
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;
		
		// 페이징 처리를 위한 개수 조회
		int listCount = 0;
		switch(search.getSearchType()) {
		case "clientId":
			listCount = popupService.selectStorageCountById(Integer.parseInt(search.getKeyword()));
			break;
		case "clientName":
			listCount = popupService.selectStorageCountByName(search.getKeyword());
			break;
		}
		
		// 페이징 처리
		Paging paging = new Paging(listCount, currentPage, limit, "popupStorage.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		// 서비스로 목록 요청
		ArrayList<Storage> list = null;
		switch(search.getSearchType()) {
		case "clientId":
			list = popupService.selectStorageById(search);
			break;
		case "clientName":
			list = popupService.selectStorageByName(search);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내기
		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Storage storage : list) {
			JSONObject job = new JSONObject();
			
			job.put("clientId", storage.getClientId());
			job.put("clientName", URLEncoder.encode(storage.getClientName(), "utf-8"));
			job.put("clientAddress", URLEncoder.encode(storage.getClientAddress(), "utf-8"));
			job.put("clientContact", storage.getClientContact());
			job.put("eid", storage.getEid());
			job.put("clientUrl", storage.getClientUrl());
			job.put("manager", storage.getManager());
			job.put("managerContact", storage.getManagerContact());
			job.put("managerEmail", storage.getManagerEmail());
			
			jarr.add(job);
		}
		
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}
	
	// 팝업창 직원 정보 조회
	@RequestMapping("popupEmployee.do")
	@ResponseBody // response body에 담아서 보냄
	public String popupEmployeeMethod(Search search,
			@RequestParam(name="page", required=false) String page,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupEmployee.do : " + search);
		
		// 타입에 따라 값 초기화
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;
		
		// 페이징 처리를 위한 개수 조회
		int listCount = 0;
		switch(search.getSearchType()) {
		case "empName":
			listCount = popupService.selectEmployeeCountByEmpName(search.getKeyword());
			break;
		case "depName":
			listCount = popupService.selectEmployeeCountByDepName(search.getKeyword());
			break;
		}
		
		// 페이징 처리
		Paging paging = new Paging(listCount, currentPage, limit, "popupEmployee.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		// 서비스로 목록 요청
		ArrayList<Employee> list = null;
		switch(search.getSearchType()) {
		case "empName":
			list = popupService.selectEmployeeByEmpName(search);
			break;
		case "depName":
			list = popupService.selectEmployeeByDepName(search);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내기
		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Employee employee : list) {
			JSONObject job = new JSONObject();
			
			job.put("empId", employee.getEmpId());
			job.put("empName", URLEncoder.encode(employee.getEmpName(), "utf-8"));
			job.put("phone", employee.getPhone());
			job.put("email", employee.getEmail());
			
			jarr.add(job);
		}
		
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}

	// 팝업창 주문 정보 조회
	@RequestMapping("popupBookOrder.do")
	@ResponseBody // response body에 담아서 보냄
	public String popupBookOrderMethod(Search search,
			@RequestParam(name="page", required=false) String page,
			HttpServletResponse response) throws IOException {
		
		logger.info("popupBookOrder.do : " + search);
		
		// 타입에 따라 값 초기화
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;
		
		// 페이징 처리를 위한 개수 조회
		int listCount = 0;
		switch(search.getSearchType()) {
		case "empName":
			listCount = popupService.selectEmployeeCountByEmpName(search.getKeyword());
			break;
		case "depName":
			listCount = popupService.selectEmployeeCountByDepName(search.getKeyword());
			break;
		}
		
		// 페이징 처리
		Paging paging = new Paging(listCount, currentPage, limit, "popupBookOrder.do");
		paging.calculator();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		// 서비스로 목록 요청
		ArrayList<Employee> list = null;
		switch(search.getSearchType()) {
		case "empName":
			list = popupService.selectEmployeeByEmpName(search);
			break;
		case "depName":
			list = popupService.selectEmployeeByDepName(search);
			break;
		}
		
		logger.info("list : " + list.toString());
		
		// response에 내보낼 값에 대한 mimeType 설정
		response.setContentType("application/json; charset=utf-8");
		
		// 리턴된 list 결과를 json 배열에 담아서 내보내기
		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Employee employee : list) {
			JSONObject job = new JSONObject();
			
			job.put("empId", employee.getEmpId());
			job.put("empName", URLEncoder.encode(employee.getEmpName(), "utf-8"));
			job.put("phone", employee.getPhone());
			job.put("email", employee.getEmail());
			
			jarr.add(job);
		}
		
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}
}
