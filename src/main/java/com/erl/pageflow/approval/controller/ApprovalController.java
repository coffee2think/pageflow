package com.erl.pageflow.approval.controller;

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
	
	//**************뷰페이지 이동****************
	//업무게시판 게시글 리스트 조회
//	@RequestMapping("aplist.do")
//	public String selectBoardListFirstMethod() {
//		return "redirect:aplistmy.do";
//	}
	//업무게시판 게시글 리스트 조회
	@RequestMapping("aplist.do")
	public String selectBoardListMethod(Model model,
			@RequestParam(name="empId", required=false) String empIdStr,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		logger.info("empIdStr : " + empIdStr);
		int empId = (empIdStr != null) ? Integer.parseInt(empIdStr) : -1;
		
		//if(empId == -1) return "approval/appr_listall";
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = approvalService.selectApprovalListCount(empId);
		
		logger.info("listCount : " + listCount);
		
		Paging paging = new Paging(listCount, currentPage, limit, "aplist.do");
		//"bdlistdate.do?begin="+search.getBegin().toString()+"&end="+search.getEnd().toString());
		paging.calculator();
		ArrayList<Approval> list = approvalService.selectApprovalList(
				new Search(empId, 0, paging.getStartRow(), paging.getEndRow()));
		
		LocalDate today = LocalDate.now();
		//model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1)); // 일주일 전부터
		model.addAttribute("end", today); // 오늘까지
		
		
		model.addAttribute("paging", paging);
		model.addAttribute("empId", empId);
		model.addAttribute("firstType", "first");
		
		if(list != null && list.size() > 0) {
			model.addAttribute("approvalList", list);
			
			for(Approval appr : list) {
				Draft draft = approvalService.selectDraft(
						new ApprovalKeyword(appr.getApprId(), appr.getDraftType(), 
						paging.getStartRow(), paging.getEndRow()));
				
				appr.setDetail(draft.getDetail());
				appr.setEmergency(draft.getEmergency());
				appr.setStartDate(draft.getStartDate());
				appr.setEndDate(draft.getEndDate());
			}
			
			//return "work/work_list";
		}else {
			//model.addAttribute("message", paging + " 전자결재게시판 조회 실패!");
			//return "common/error";
		}
		return "approval/appr_list";
		
	}
	
	@RequestMapping("aplistall.do")
	public String selectBoardListAllMethod() {
		return "approval/appr_listall";
	}
	
}
