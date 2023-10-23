package com.erl.pageflow.edit.controller;

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
import com.erl.pageflow.common.Search;
import com.erl.pageflow.contract.model.vo.Contract;
import com.erl.pageflow.edit.model.service.EditService;
import com.erl.pageflow.edit.model.vo.Edit;
import com.erl.pageflow.sales.model.vo.BookOrder;

@Controller
public class EditController {
	private static final Logger logger = LoggerFactory.getLogger(EditController.class);

	@Autowired
	private EditService editService;
	
	// ****************** 뷰페이지 이동 **********************
	
	// 편집 등록 페이지 이동
	@RequestMapping("moveedinsert.do")
	public String moveEditInsertPage() {
		return "publish/edit_input";
	}
	

	// ****************** 요청 받아서 서비스로 넘기는 메소드 **********************
	
	// 편집 리스트 조회
	@RequestMapping("edlist.do")
	public String selectEditListMethod(@RequestParam(name = "page", required = false) String page,
				@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		int listCount = editService.selectEditListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "edlist.do");
		paging.calculator();
		
		ArrayList<Edit> list = editService.selectEditList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("editList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/edit_list";
		} else {
			model.addAttribute("message", "편집 목록 조회 실패!");
			return "common/error";
		}
	}
	
	// 편집 정보 등록 요청 처리
	@RequestMapping(value="edinsert.do", method=RequestMethod.POST)
	public String editInsertMethod(HttpServletRequest request, Model model) {
		String[] depIds = request.getParameterValues("depId");
		String[] empIds = request.getParameterValues("empId");
		String[] bookNames = request.getParameterValues("bookName");
		String[] editStates = request.getParameterValues("editState");
		
		// 주문번호 생성
		int editId = editService.selectMaxEditId() + 1;
		
		ArrayList<Edit> edits = new ArrayList<>();
		for(int i = 0; i < depIds.length; i++) {
			Edit edit = new Edit();
			
			edit.setEditId(editId);
			edit.setDepId(Integer.parseInt(depIds[i]));
			edit.setEmpId(Integer.parseInt(empIds[i]));
			edit.setBookName(bookNames[i]);
			edit.setEditState(editStates[i]);
			
			edits.add(edit);
		}
		
		for(Edit edit : edits) {
			if(editService.insertEdit(edit) == 0) {
				model.addAttribute("message", "편집 등록 실패!");
				return "common/error";
			}
		}
		
		return "redirect:edlist.do";
	}
	
	// 편집 정보 수정 요청 처리
	@RequestMapping(value="edupdate.do", method=RequestMethod.POST)
	public void editUpdateMethod(Edit edit, HttpServletResponse response) throws IOException {
		logger.info("edupdate.do : " + edit);
		
		String returnStr = null;
		if(editService.updateEdit(edit) > 0) {
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
	
	// 거래처 정보 삭제 요청 처리
	@RequestMapping(value="eddelete.do", method=RequestMethod.POST)
	public String editDeleteMethod(@RequestParam("IDs") int[] editIDs, Model model) {
		logger.info("eddelete.do : " + editIDs);
		
		for(int editId : editIDs) {
			if(editService.deleteEdit(editId) == 0) {
				model.addAttribute("message", editId + "번 편집 정보 삭제 실패!");
				return "common/error";
			}
		}
		
		return "redirect:edlist.do";
	}
	
	// 편집 시작날짜 조회
	@RequestMapping("edlistSdate.do")
	public String selectEditBySDate(Search search,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, 
			Model model) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = editService.selectEditCountBySDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "edlistSdate.do");
		
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Edit> list = editService.selectEditBySDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {

			model.addAttribute("editList", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "publish/edit_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
	}
	
	// 편집 마감날짜 조회
	@RequestMapping("edlistEdate.do")
	public String selectEditByEDate(Search search,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, 
			Model model) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = editService.selectEditCountByEDate(search);
		
		Paging paging = new Paging(listCount, currentPage, limit, "edlistEdate.do");
		
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Edit> list = editService.selectEditByEDate(search);
		logger.info("search : " + search);
		
		if (list != null && list.size() > 0) {

			model.addAttribute("editList", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "publish/edit_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}
	}
	
}
