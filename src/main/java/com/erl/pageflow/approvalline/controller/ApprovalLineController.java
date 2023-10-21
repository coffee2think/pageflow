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
	@RequestMapping("allinelist.do")
	public String moveSelectApprovalLineMethod() {
		return "approval/appr_line";
	}
}
