package com.erl.pageflow.approval.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.approval.model.service.ApprovalService;
import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approval.model.vo.Draft;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.common.ApprovalKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.common.Search;

@Controller
public class ApprovalController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	//**************나의 전자결재****************
	//기안서 검색
	public void setDraft(Approval appr, Paging paging) {
		Draft draft = approvalService.selectDraft(
		new ApprovalKeyword(appr.getApprId(), appr.getDraftType(), 
		paging.getStartRow(), paging.getEndRow()));

		appr.setDetail(draft.getDetail());
		appr.setEmergency(draft.getEmergency());
		appr.setStartDate(draft.getStartDate());
		appr.setEndDate(draft.getEndDate());
		
	}
	
	//나의 전자결재 리스트 조회
	@RequestMapping("aplist.do")
	public String selectBoardListMethod(Model model,
			@RequestParam(name="apType") String apTypeStr,
			@RequestParam(name="empId", required=false) String empIdStr,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		logger.info("empIdStr : " + empIdStr);
		int empId = (empIdStr != null) ? Integer.parseInt(empIdStr) : -1;
		
		//if(empId == -1) return "approval/appr_listall";
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		String apType = (apTypeStr != null) ? apTypeStr : "all";
		int listCount = 0;
		if(apType == "my") {
			listCount = approvalService.selectApprovalListCount(empId);
		}else {
			listCount = approvalService.selectApprovalListAllCount();
		}	
		
		logger.info("listCount : " + listCount);
		
		Paging paging = new Paging(listCount, currentPage, limit, "aplist.do?apType="+apType+"&empId="+empId);
		paging.calculator();
		ArrayList<Approval> list = null;
		
		if(apType == "my") {
			list = approvalService.selectApprovalList(
					new Search(empId, 0, paging.getStartRow(), paging.getEndRow()));
		}else {
			list = approvalService.selectApprovalList(
					new Search(paging.getStartRow(), paging.getEndRow()));
		}
		
		LocalDate today = LocalDate.now();
		//model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1)); // 일주일 전부터
		model.addAttribute("end", today); // 오늘까지
		model.addAttribute("paging", paging);
		model.addAttribute("empId", empId);
		model.addAttribute("apType", apType);
		//model.addAttribute("firstType", "first");
		
		if(list != null && list.size() > 0) {
			model.addAttribute("approvalList", list);
			
			for(Approval a : list) {
				setDraft(a, paging);
			}
			logger.info("list : " + list);
			//return "approval/appr_list";
		}else {
			//model.addAttribute("message", paging + " 전자결재게시판 조회 실패!");
			//return "common/error";
		}
		return "approval/appr_list";
		
	}
	
	//----------------날짜-----------------
	//나의 전자결재 필터링된 리스트 조회
	@RequestMapping("aplistdate.do")
	public String selectApprovalListDateMethod(Search search, Model model,
			@RequestParam(name="apType") String apTypeStr,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		int listCount = 0;
		String apType = (apTypeStr != null) ? apTypeStr : "all";
		if(apType == "my") {
			listCount = approvalService.selectApprovalListDateCount(search);
		}else {
			listCount = approvalService.selectApprovalListAllDateCount(search);
		}
		
		logger.info("listCount : " + listCount);
		Paging paging = new Paging(listCount, currentPage, limit, "aplistdate.do?apType="+apType);
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		ArrayList<Approval> list = null;
		
		if(apType == "my") {
			list = approvalService.selectApprovalListDate(search);
		}else {
			list = approvalService.selectApprovalListAllDate(search);
		}
		
		logger.info("list : " + list);
		for(Approval a : list) {
			setDraft(a, paging);
		}
		model.addAttribute("empId", search.getEmpId());
		model.addAttribute("paging", paging);
		model.addAttribute("searchType", search.getSearchType());
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		model.addAttribute("apType", apType);
		//model.addAttribute("firstType", "date");
		
		if(list != null && list.size() > 0) {
			model.addAttribute("approvalList", list);
		}else {
			//model.addAttribute("message", paging + " 나의 전자결재 날짜로 조회 실패!");
			//return "common/error";
		}
		return "approval/appr_list";
	}
	
	//----------------서치-----------------
	//전자결재 검색
	@RequestMapping(value = "apsearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchApprovalMethod(Search search, Model model,
			@RequestParam(name="apType") String apTypeStr,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		String searchType = search.getSearchType();
		int listCount = 0;
		String apType = (apTypeStr != null) ? apTypeStr : "all";
		
		logger.info("searchType : " + searchType);
		if(searchType != null) {
			switch (searchType) {
				case "complete":
					if(apType == "my") listCount = approvalService.selectApprSearchCountComplete(search);
					else listCount = approvalService.selectApprSearchAllCountComplete(search);
					break;
				case "continue":
					if(apType == "my") listCount = approvalService.selectApprSearchCountContinue(search);
					else listCount = approvalService.selectApprSearchAllCountContinue(search);
					break;
				case "companion":
					if(apType == "my") listCount = approvalService.selectApprSearchCountCompanion(search);
					else listCount = approvalService.selectApprSearchAllCountCompanion(search);
					break;
				case "approver":
					if(apType == "my") listCount = approvalService.selectApprSearchCountApprover(search);
					else listCount = approvalService.selectApprSearchAllCountApprover(search);
					break;
				case "drafter":
					if(apType == "my") listCount = approvalService.selectApprSearchCountDrafter(search);
					else listCount = approvalService.selectApprSearchAllCountDrafter(search);
					break;
			}
			
		}
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		logger.info("listCount : " + listCount);
		Paging paging = new Paging(listCount, currentPage, limit, 
				"apsearch.do?apType="+apType+"&empId="+search.getEmpId() + "&searchType=" + searchType);
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Approval> list = null;
		if(searchType != null) {
			switch (searchType) {
				case "complete":
					if(apType == "my") list = approvalService.selectApprSearchComplete(search);
					else list = approvalService.selectApprSearchAllComplete(search);
					break;
				case "continue":
					if(apType == "my") list = approvalService.selectApprSearchContinue(search);
					else list = approvalService.selectApprSearchAllContinue(search);
					break;
				case "companion":
					if(apType == "my") list = approvalService.selectApprSearchCompanion(search);
					else list = approvalService.selectApprSearchAllCompanion(search);
					break;
				case "approver":
					if(apType == "my") list = approvalService.selectApprSearchApprover(search);
					else list = approvalService.selectApprSearchAllApprover(search);
					break;
				case "drafter":
					if(apType == "my") list = approvalService.selectApprSearchDrafter(search);
					else list = approvalService.selectApprSearchAllDrafter(search);
					break;
			}
		}
		
		logger.info("apsearch.do list : " + list);
		
		model.addAttribute("keyword", search.getKeyword());
		model.addAttribute("paging", paging);
		model.addAttribute("empId", search.getEmpId());
		model.addAttribute("searchType", searchType);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		model.addAttribute("apType", apType);
		//model.addAttribute("firstType", "first");
		
		if(list != null && list.size() > 0) {
			
			for(Approval a : list) {
				setDraft(a, paging);
			}
			model.addAttribute("approvalList", list);
		}else {
			//model.addAttribute("message", paging + " 업무게시판 서치 실패!");
			//return "common/error";
		}
		
		return "approval/appr_list";
	}
	
	
}
