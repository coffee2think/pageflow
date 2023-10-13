package com.erl.pageflow.approvalline.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erl.pageflow.approval.controller.ApprovalController;
import com.erl.pageflow.approval.model.service.ApprovalServiceImpl;

@Controller
public class ApprovalLineController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalServiceImpl approvalService;
	
	//**************뷰페이지 이동****************
	//결재라인 관리 이동
	@RequestMapping("appr_line.do")
	public String moveApprovalLine() {
		return "approval/appr_line";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	//결재라인 조회
	@RequestMapping(value = "appr_line.do")
	public String selectApprovalLineList() {
		return "";
	}
	
	//결재라인 등록
	@RequestMapping(value = "appr_line.do", method = RequestMethod.POST)
	public String insertApprovalLine() {
		return "";
	}
	
	//결재라인 수정
	@RequestMapping(value = "appr_line.do", method = RequestMethod.POST)
	public String updateApprovalLine() {
		return "";
	}
	
	//결재라인 삭제
	@RequestMapping(value = "appr_line.do", method = RequestMethod.POST)
	public String deleteApprovalLine() {
		return "";
	}
}
