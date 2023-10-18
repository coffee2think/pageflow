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
import com.erl.pageflow.notice.model.vo.Notice;
import com.erl.pageflow.sales.controller.SalesController;

@Controller
public class PopupController {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired
	private PopupService popupService;
	
	// 팝업창 도서 현황 정보 조회
	@RequestMapping("popup_book.do")
	@ResponseBody // 리턴하는 jsonString을 response body에 담아서 보낸다는 의미
	public String popupBookMethod(
			@RequestParam(name="searchType") String searchType,
			@RequestParam(name="keyword") String keyword,
			HttpServletResponse response) throws IOException {
		
//		logger.info("popup_book.do : searchType=" + searchType + ", keyword=" + keyword);
//		
//		// 타입에 따라 값 초기화
//		int currentPage = 1;
//		int limit = 10;
//		
//		int listCount = 0;
//		
//		switch(searchType) {
//		case "bookId":
//			listCount = popupService.selectBookCountById(Integer.parseInt(keyword));
//			break;
//		case "bookName":
//			listCount = popupService.selectBookCountByName(keyword);
//			break;
//		}
//		
//		int bookId = (searchType.equals("bookId")) ? Integer.parseInt(keyword) : 0;
//		
//		// 
//		ArrayList<BookWithStock> list = popupService.selectBookById();
//		
//		// response에 내보낼 값에 대한 mimeType 설정
//		response.setContentType("application/json; charset=utf-8");
//		
//		// 리턴된 list 결과를 json 배열에 담아서 내보내는 처리 :
//		// 전송용 json 객체 생성
//		JSONObject sendJson = new JSONObject();
//		// list 담을 json 배열 객체 생성
//		JSONArray jarr = new JSONArray();
//		
//		// list를 json 배열에 옮기기
//		for(Notice notice : list) {
//			// notice 값을 저장할 json 객체 생성
//			JSONObject job = new JSONObject();
//			
//			job.put("noticeNo", notice.getNoticeNo());
//			// 문자열에 한글이 있다면, 반드시 인코딩해서 저장해야함
//			job.put("noticeTitle", URLEncoder.encode(notice.getNoticeTitle(), "utf-8"));
//			job.put("noticeWriter", notice.getNoticeWriter());
//			// 날짜데이터는 반드시 문자열로 바꿔서 저장해야함
//			job.put("noticeDate", notice.getNoticeDate().toString());
//			
//			// jarr에 job 저장함
//			jarr.add(job);
//		}
//		
//		// 전송용 객체에 jarr 저장
//		sendJson.put("list", jarr);
//		
//		return sendJson.toJSONString();
		return null;
	}
}
