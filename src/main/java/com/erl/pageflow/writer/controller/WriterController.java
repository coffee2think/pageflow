package com.erl.pageflow.writer.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.edit.model.vo.Edit;
import com.erl.pageflow.writer.model.service.WriterService;
import com.erl.pageflow.writer.model.vo.Writer;

@Controller
public class WriterController {
	private static final Logger logger = LoggerFactory.getLogger(WriterController.class);

	@Autowired
	private WriterService writerService;
	
	// ****************** 뷰페이지 이동 **********************
	
	// 작가 등록 페이지 이동
	@RequestMapping("movewtinsert.do")
	public String moveWriterInsertPage() {
		return "publish/writer_input";
	}
	

	// ****************** 요청 받아서 서비스로 넘기는 메소드 **********************
	
	// 작가 리스트 조회
	@RequestMapping("wtlist.do")
	public String selectWriterListMethod(@RequestParam(name = "page", required = false) String page,
				@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		int listCount = writerService.selectWriterListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "wtlist.do");
		paging.calculator();
		
		ArrayList<Writer> list = writerService.selectWriterList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("writerList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/writer_list";
		} else {
			model.addAttribute("message", "작가 목록 조회 실패!");
			return "common/error";
		}
	}

	// 작가 정보 등록 요청 처리
	@RequestMapping(value="wtinsert.do", method=RequestMethod.POST)
	public String wtInsertMethod(HttpServletRequest request, Model model) {
		String[] writerNames = request.getParameterValues("writerName");
		String[] phones = request.getParameterValues("phone");
		String[] writerBirths = request.getParameterValues("writerBirth");
		String[] emails = request.getParameterValues("email");
		String[] addresss = request.getParameterValues("address");
		String[] banks = request.getParameterValues("bank");
		String[] accounts = request.getParameterValues("account");
		
		// 주문번호 생성
		int writerId = writerService.selectMaxWriterId() + 1;
		
		ArrayList<Writer> writers = new ArrayList<>();
		for(int i = 0; i < writerNames.length; i++) {
			Writer writer = new Writer();
			
			writer.setWriterId(writerId);
			writer.setWriterName(writerNames[i]);
			writer.setPhone(phones[i]);
			writer.setWriterBirth(writerBirths[i]);
			writer.setEmail(emails[i]);
			writer.setAddress(addresss[i]);
			writer.setBank(banks[i]);
			writer.setAccount(accounts[i]);
			
			writers.add(writer);
		}
		
		for(Writer writer : writers) {
			if(writerService.insertWriter(writer) == 0) {
				model.addAttribute("message", "작가 등록 실패!");
				return "common/error";
			}
		}
		
		return "redirect:wtlist.do";
	}
	
	// 작가 정보 수정 요청 처리
	@RequestMapping(value="wtupdate.do", method=RequestMethod.POST)
	public void writerUpdateMethod(Writer writer, HttpServletResponse response) throws IOException {
		logger.info("wtupdate.do : " + writer);
		
		String returnStr = null;
		if(writerService.updateWriter(writer) > 0) {
			returnStr = "success";
		} else {
			returnStr = "fail";
		}
		
		// response를 이용해서 클라이언트와 출력스트림을 열어서 값 보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush(); 
	}
	
	// 도서 정보 삭제 요청 처리
	@RequestMapping(value="wtdelete.do", method=RequestMethod.POST)
	public String writerDeleteMethod(@RequestParam("IDs") int[] writerIDs, Model model) {
		logger.info("wtdelete.do : " + writerIDs);
		
		for(int writerId : writerIDs) {
			if(writerService.deleteWriter(writerId) == 0) {
				model.addAttribute("message", writerId + "번 작가 정보 삭제 실패!");
				return "common/error";
			}
		}
		
		return "redirect:wtlist.do";
	}
}
