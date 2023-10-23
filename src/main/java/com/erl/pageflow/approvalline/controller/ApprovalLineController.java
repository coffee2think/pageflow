package com.erl.pageflow.approvalline.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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

import com.erl.pageflow.approval.controller.ApprovalController;
import com.erl.pageflow.approval.model.service.ApprovalService;
import com.erl.pageflow.approval.model.service.ApprovalServiceImpl;
import com.erl.pageflow.approvalline.model.service.ApprovalLineService;
import com.erl.pageflow.approvalline.model.vo.ApprovalLineSave;

@Controller
public class ApprovalLineController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private ApprovalLineService approvalLineService;
	
	//**************뷰페이지 이동****************
	@RequestMapping("allinelist.do")
	public String selectApprovalLineMethod(
			@RequestParam(name="empId") int empId, Model model) throws UnsupportedEncodingException {
		
		logger.info("empId : " + empId);
		ArrayList<ApprovalLineSave> apprLineSaveList = approvalLineService.selectMyApprovalSaveLineListEmpId(empId);
		logger.info("apprLineSaveList : " + apprLineSaveList);
		//검색
		JSONObject json = new JSONObject();
		JSONArray coList = null;
		
		for(ApprovalLineSave sLine : apprLineSaveList) {
			
		}
		
		
		int count = 0;
		if(apprLineSaveList.size() > 0) {
			
			coList = new JSONArray();
			JSONArray cList = new JSONArray();
			
			for(int i=0; i<apprLineSaveList.size(); i++) {
				//System.out.println("i : " + i);
				int nextDepth = (i < apprLineSaveList.size()-1) ? apprLineSaveList.get(i+1).getLineDepth() : -1;//다음번 뎁스
				
				cList.add(returnSaveLine(apprLineSaveList.get(i)));
				
				if(nextDepth == 1 || i == apprLineSaveList.size()-1) {//마지막이거나 다음 뎁스가 1일때
					
					JSONArray ctemp = (JSONArray) cList.clone();
					coList.add(ctemp);
					cList.clear();
					count ++;
				}
			}
		}
		
		logger.info("coList : " + coList);
		
		model.addAttribute("apprLineSaveList", apprLineSaveList);
		return "approval/appr_line";
	}
	
	public JSONObject returnSaveLine(ApprovalLineSave approvalLineSave) throws UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		json.put("saveLineId", approvalLineSave.getSavelineId());
		json.put("lineDepth", approvalLineSave.getLineDepth());
		json.put("empId", approvalLineSave.getEmpId());
		json.put("approverId", approvalLineSave.getApproverId());
		json.put("approverName", approvalLineSave.getApproverName());
		json.put("posName", approvalLineSave.getPosName());
		json.put("lineName", approvalLineSave.getLineName());
		return json;
	}
}
