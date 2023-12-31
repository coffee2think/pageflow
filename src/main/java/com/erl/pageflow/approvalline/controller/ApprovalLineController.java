package com.erl.pageflow.approvalline.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
		logger.info("allinelist.do -> apprLineSaveList : " + apprLineSaveList);
		//검색
		ArrayList<Object> coList = null;
		
		int count = 0;
		if(apprLineSaveList.size() > 0) {
			
			coList = new ArrayList<Object>();
			ArrayList<Object> cList = new ArrayList<Object>();
			
			for(int i=0; i<apprLineSaveList.size(); i++) {
				//System.out.println("i : " + i);
				int nextDepth = (i < apprLineSaveList.size()-1) ? apprLineSaveList.get(i+1).getSavelineId() : -1;//다음번 뎁스
				
				cList.add(apprLineSaveList.get(i));
				
				if(nextDepth != apprLineSaveList.get(i).getSavelineId() || i == apprLineSaveList.size()-1) {//마지막이거나 다음 뎁스가 1일때
					
					ArrayList<Object> ctemp = (ArrayList<Object>) cList.clone();
					coList.add(ctemp);
					cList.clear();
					count ++;
				}
			}
		}
		
		logger.info("coList : " + coList);
		
		model.addAttribute("apprLineSaveList", coList);
		model.addAttribute("empId", empId);
		return "approval/appr_line";
	}
	
	@RequestMapping(value = "alinsert.do", method = RequestMethod.POST)
	public String insertApprovalLineMethod(
			HttpServletRequest request,
			Model model) throws Exception {
		logger.info("alinsert.do -> myEmpId : " + request.getParameter("myEmpId"));
		int savelineId = approvalLineService.selectApprovalSaveLineId();
		int empId = Integer.parseInt(request.getParameter("myEmpId"));
		String lineName = request.getParameter("lineName");
		String[] approverIds = request.getParameterValues("empId");
		String[] empNames = request.getParameterValues("empName");
		String[] posNames = request.getParameterValues("posName");
		String[] depNames = request.getParameterValues("depName");
		String[] orders = request.getParameterValues("order");
		logger.info("alinsert.do -> savelineId : " + savelineId);
		logger.info("alinsert.do -> approverIds : " + Arrays.toString(approverIds));
		logger.info("alinsert.do -> empNames : " + Arrays.toString(empNames));
		logger.info("alinsert.do -> posNames : " + Arrays.toString(posNames));
		logger.info("alinsert.do -> depNames : " + Arrays.toString(depNames));
		logger.info("alinsert.do -> orders : " + Arrays.toString(orders));
		
		ArrayList<Integer> countArr = new ArrayList<Integer>();
		for(int i=0; i<approverIds.length; i++) {
			logger.info("alinsert.do -> approverIds[i] : " + approverIds[i]);
			if(approverIds[i] != null && approverIds[i] != "") {
				countArr.add(i);
			}
		}
		logger.info("alinsert.do -> countArr : " + countArr);
		int count = 0;
		//ArrayList<ApprovalLineSave> saveLineList = new ArrayList<ApprovalLineSave>();
		for(int i=0; i<countArr.size(); i++) {
			ApprovalLineSave als = new ApprovalLineSave();
			als.setSavelineId(savelineId);
			als.setLineDepth(count+1);
			als.setEmpId(empId);
			als.setApproverId(Integer.parseInt(approverIds[countArr.get(i)]));
			als.setApproverName(empNames[countArr.get(i)]);
			als.setPosName(posNames[countArr.get(i)]);
			als.setDepName(depNames[countArr.get(i)]);
			als.setLineName(lineName);
			logger.info("alinsert.do -> als : " + als);
			if(approvalLineService.insertApprovalLineSave(als) > 0) {
				count ++;
			}
		}
		model.addAttribute("empId", empId);
		if(count < countArr.size()) {
			model.addAttribute("message", "결재라인 저장 실패");
			return "common/error";
		}
		
		return "redirect:allinelist.do";
	}
	
	//딜리트
	@RequestMapping("aldelete.do")
	public String deleteApprovalLineSaveMethod(
			@RequestParam(name="empId") int empId,
			@RequestParam(name="savelineId") int savelineId,
			Model model) throws Exception {
		
		if(approvalLineService.deleteApprovalLine(savelineId) <= 0) {
			model.addAttribute("message", "결재라인 삭제 실패");
			return "common/error";
		}
		model.addAttribute("empId", empId);
		return "redirect:allinelist.do";
	}
}
