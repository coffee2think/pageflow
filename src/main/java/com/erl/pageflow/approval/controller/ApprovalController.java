package com.erl.pageflow.approval.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erl.pageflow.approval.model.service.ApprovalService;

@Controller
public class ApprovalController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	//**************뷰페이지 이동****************
	//나의 결재 리스트 이동
	@RequestMapping("appr_list.do")
	public String moveApprovalList() {
		return "approval/appr_list";
	}
	
	//모든 결재 리스트 이동
	@RequestMapping("appr_listall.do")
	public String moveApprovalListAll() {
		return "approval/appr_listall";
	}
	
	//결재 기안서 작성 이동
	@RequestMapping("appr_input.do")
	public String moveApprovalInput() {
		return "approval/appr_input";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	//결재 리스트 갯수 조회
	@RequestMapping("appr_list.do")
	public String selectApprovalListCount() {
		return "";
	}
	
	//모든 결재 조회
	@RequestMapping("appr_listall.do")
	public String selectApprovalListAll() {
		return "";
	}
	
	//나의 결재 조회
	@RequestMapping("appr_list.do")
	public String selectApprovalList() {
		return "";
	}
	
	//결재 기안서 등록
	@RequestMapping(value = "appr_list.do", method = RequestMethod.POST)
	public String insertApproval() {
		return "";
	}
	
	//결재 기안서 수정
	@RequestMapping(value = "appr_input.do", method = RequestMethod.POST)
	public String updateApproval() {
		return "";
	}
	
	//결재 기안서 삭제
	@RequestMapping(value = "appr_list.do", method = RequestMethod.POST)
	public String deleteApproval() {
		return "";
	}
	
}
