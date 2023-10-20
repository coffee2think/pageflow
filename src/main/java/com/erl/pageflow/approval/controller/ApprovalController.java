package com.erl.pageflow.approval.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.erl.pageflow.approval.model.service.ApprovalService;
import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approval.model.vo.Draft;
import com.erl.pageflow.approvalline.model.service.ApprovalLineService;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.ApprovalKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.common.Search;

@Controller
public class ApprovalController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private ApprovalLineService approvalLineService;
	
	//**************나의 전자결재****************
	//기안서 검색
	public void setDraft(Approval appr) {
		Draft draft = approvalService.selectDraft(
		new ApprovalKeyword(appr.getApprId(), appr.getDraftType()));
		
		appr.setDetail(draft.getDetail());
		appr.setEmergency(draft.getEmergency());
		appr.setStartDate(draft.getStartDate());
		appr.setEndDate(draft.getEndDate());
		appr.setDetailType(draft.getDetailType());
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
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		String apType = (apTypeStr != null) ? apTypeStr : "all";
		logger.info("apType : " + apType);
		int listCount = 0;
		
		switch (apType) {
			case "my"://상신한 기안서 
				listCount = approvalService.selectApprovalListCount(empId);
				break;
			case "ap"://결재할 기안서
				listCount = approvalService.selectApprovalListCount_A(empId);
				break;
			default://모든 기안서
				listCount = approvalService.selectApprovalListAllCount();
		}
		logger.info("===========listCount : " + listCount);
		
		Paging paging = new Paging(listCount, currentPage, limit, "aplist.do?apType="+apType+"&empId="+empId);
		paging.calculator();
		ArrayList<Approval> list = null;
		
		switch (apType) {
			case "my"://상신한 기안서 
				list = approvalService.selectApprovalList(
						new Search(empId, 0, paging.getStartRow(), paging.getEndRow()));
				break;
			case "ap"://결재할 기안서
				list = approvalService.selectApprovalList_A(
						new Search(empId, 0, paging.getStartRow(), paging.getEndRow()));
				break;
			default://모든 기안서
				list = approvalService.selectApprovalListAll(
						new Search(paging.getStartRow(), paging.getEndRow()));
		}
		
		LocalDate today = LocalDate.now();
		//model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1)); // 일주일 전부터
		model.addAttribute("end", today); // 오늘까지
		model.addAttribute("paging", paging);
		model.addAttribute("empId", empId);
		model.addAttribute("apType", apType);
		model.addAttribute("firstType", "first");
		
		if(list != null && list.size() > 0) {
			model.addAttribute("approvalList", list);
			
			for(Approval a : list) {
				setDraft(a);
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
		
		switch (apType) {
			case "my"://상신한 기안서 
				listCount = approvalService.selectApprovalListDateCount(search);
				break;
			case "ap"://결재할 기안서
				listCount = approvalService.selectApprovalListDateCount_A(search);
				break;
			default://모든 기안서
				listCount = approvalService.selectApprovalListAllDateCount(search);
		}
		
		logger.info("apType : " + apType);
		logger.info("listCount : " + listCount);
		logger.info("aplistdate.do search : " + search);
		Paging paging = new Paging(listCount, currentPage, limit, "aplistdate.do?apType="+apType);
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		ArrayList<Approval> list = null;
		
		switch (apType) {
			case "my"://상신한 기안서 
				list = approvalService.selectApprovalListDate(search);
				break;
			case "ap"://결재할 기안서
				list = approvalService.selectApprovalListDate_A(search);
				break;
			default://모든 기안서
				list = approvalService.selectApprovalListAllDate(search);
		}
		
		logger.info("list : " + list);
		for(Approval a : list) {
			setDraft(a);
		}
		model.addAttribute("empId", search.getEmpId());
		model.addAttribute("paging", paging);
		model.addAttribute("searchType", search.getSearchType());
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		model.addAttribute("apType", apType);
		model.addAttribute("firstType", "date");
		
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
		logger.info("apsearch.do search : " + search);
		logger.info("searchType : " + searchType);
		logger.info("apType : " + apType);
		if(searchType != null) {
			switch (searchType) {
				case "all":
				case "complete":
				case "continue":
				case "companion":
					if(apType.equals("my")) listCount = approvalService.selectApprSearchCountKeyword(search);
					else if(apType.equals("ap")) listCount = approvalService.selectApprSearchCountKeyword_A(search);
					else listCount = approvalService.selectApprSearchAllCountKeyword(search);
					break;
				case "approver":
					if(apType.equals("my")) listCount = approvalService.selectApprSearchCountApprover(search);
					else if(apType.equals("ap")) listCount = approvalService.selectApprSearchCountApprover_A(search);
					else listCount = approvalService.selectApprSearchAllCountApprover(search);
					break;
				case "drafter":
					if(apType.equals("my")) listCount = approvalService.selectApprSearchCountDrafter(search);
					else if(apType.equals("ap")) listCount = approvalService.selectApprSearchCountDrafter_A(search);
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
		
		model.addAttribute("keyword", search.getKeyword());
		model.addAttribute("paging", paging);
		model.addAttribute("empId", search.getEmpId());
		model.addAttribute("searchType", searchType);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		model.addAttribute("apType", apType);
		model.addAttribute("firstType", "first");
		
		if(listCount == 0) return "approval/appr_list";
		
		ArrayList<Approval> list = null;
		if(searchType != null) {
			switch (searchType) {
				case "all":
				case "complete":
				case "continue":
				case "companion":
					if(apType.equals("my")) list = approvalService.selectApprSearchKeyword(search);
					else if(apType.equals("ap")) list = approvalService.selectApprSearchKeyword_A(search);
					else list = approvalService.selectApprSearchAllKeyword(search);
					break;
				case "approver":
					if(apType.equals("my")) list = approvalService.selectApprSearchApprover(search);
					else if(apType.equals("ap")) list = approvalService.selectApprSearchApprover_A(search);
					else list = approvalService.selectApprSearchAllApprover(search);
					break;
				case "drafter":
					if(apType.equals("my")) list = approvalService.selectApprSearchDrafter(search);
					else if(apType.equals("ap")) list = approvalService.selectApprSearchDrafter_A(search);
					else list = approvalService.selectApprSearchAllDrafter(search);
					break;
			}
		}
		
		logger.info("apsearch.do list : " + list);
		
		if(list != null && list.size() > 0) {
			
			for(Approval a : list) {
				setDraft(a);
			}
			model.addAttribute("approvalList", list);
		}else {
			//model.addAttribute("message", paging + " 업무게시판 서치 실패!");
			//return "common/error";
		}
		
		return "approval/appr_list";
	}
	
	
	@RequestMapping(value = "apapprovalpop.do", method = RequestMethod.POST)
	@ResponseBody//문자값 응답시에는 생략해도 됨
	public String selectApprovalPopup(
			@RequestParam("approvalId") int approvalId,
			HttpServletResponse response) throws IOException {
		logger.info("approvalId : " + approvalId);
		response.setContentType("application/json; charset=utf-8");
		
		Approval approval = approvalService.selectMyApproval(approvalId);
		setDraft(approval);
		logger.info("approval : " + approval);
		ApprovalLine approvalLine = approvalLineService.selectMyApprovalLine(approval.getLineId());
		logger.info("approvalLine : " + approvalLine);
		
		JSONObject jobj = new JSONObject();
		
		jobj.put("appr_id", approval.getApprId());
		jobj.put("emergency", approval.getEmergency());
		jobj.put("dep_name", approval.getDepName());
		jobj.put("job_name", approval.getJobName());
		jobj.put("pos_name", approval.getPosName());
		jobj.put("draft_type", approval.getDraftType());
		jobj.put("drafter_name", approval.getDrafterName());
		jobj.put("approver_name", approval.getApprover());
		jobj.put("start_date", (approval.getStartDate() == null) ? "" : approval.getStartDate().toString());
		jobj.put("end_date", (approval.getEndDate() == null) ? "" : approval.getEndDate().toString());
		jobj.put("detail", approval.getDetail());
		jobj.put("title", approval.getTitle());
		jobj.put("appr_date", (approval.getApprDate() == null) ? "" : approval.getApprDate().toString());
		jobj.put("receipt_date", (approval.getReceiptDate() == null) ? "" : approval.getReceiptDate().toString());
		jobj.put("rejection_date", (approval.getRejectionDate() == null) ? "" : approval.getRejectionDate().toString());
		jobj.put("line_id", approvalLine.getLineId());
		jobj.put("line_name", approvalLine.getLineName());
		
		
		
		jobj.put("emp_id1", approvalLine.getEmpId1());
		jobj.put("emp_id2", approvalLine.getEmpId2());
		jobj.put("emp_id3", approvalLine.getEmpId3());
		jobj.put("emp_id4", approvalLine.getEmpId4());
		
		jobj.put("pos_name1", approvalLine.getPosName1());
		jobj.put("pos_name2", approvalLine.getPosName2());
		jobj.put("pos_name3", approvalLine.getPosName3());
		jobj.put("pos_name4", approvalLine.getPosName4());
		
		//jobj.put("stamp_date1", approvalLine.getStampDate1());
		//jobj.put("stamp_date2", approvalLine.getStampDate2());
		//jobj.put("stamp_date3", approvalLine.getStampDate3());
		//jobj.put("stamp_date4", approvalLine.getStampDate4());
		
		jobj.put("emp_name1", approvalLine.getEmpName1());
		jobj.put("emp_name2", approvalLine.getEmpName2());
		jobj.put("emp_name3", approvalLine.getEmpName3());
		jobj.put("emp_name4", approvalLine.getEmpName4());
		
		return jobj.toJSONString();
		
	}
	
	//인서트
	@RequestMapping("apmoveinsert.do")
	public String insertMoveApprovalMethod() {
		return "approval/appr_input";
	}
	
	@RequestMapping(value = "apinsert.do", method = RequestMethod.POST)
	public String insertApprovalMethod(Approval approval, Model model,
			HttpServletRequest request, 
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		
		return "approval/appr_input";
	}
}
