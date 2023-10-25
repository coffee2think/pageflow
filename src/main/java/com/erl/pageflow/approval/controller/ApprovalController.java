package com.erl.pageflow.approval.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.approval.model.service.ApprovalService;
import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approval.model.vo.Draft;
import com.erl.pageflow.approvalline.model.service.ApprovalLineService;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.approvalline.model.vo.ApprovalLineSave;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.board.model.vo.BoardUpload;
import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.ApprovalKeyword;
import com.erl.pageflow.common.ApprovalLineKeyword;
import com.erl.pageflow.common.FileNameChange;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.service.EmployeeService;
import com.erl.pageflow.employee.model.vo.Employee;

@Controller
public class ApprovalController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private ApprovalLineService approvalLineService;
	
	@Autowired
	private EmployeeService employeeService;
	
	//**************나의 전자결재****************
	//기안서 검색
	public void setDraft(Approval appr) {
		Draft draft = approvalService.selectDraft(
		new ApprovalKeyword(appr.getApprId(), appr.getDraftType()));
		appr.setTitle(draft.getTitle());
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
					//내가 기안자일때
					//내가 결재자일때
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
		
		
		Approval approval = approvalService.selectMyApproval(approvalId);
		setDraft(approval);
		logger.info("approval : " + approval);
		//기안서의 결재라인 리스트
		ArrayList<ApprovalLine> lineList = approvalLineService.selectMyApprovalLineList(approval.getLineId());
		logger.info("lineList : " + lineList);
		
		//기안서에 저장된 결재라인 
		ArrayList<ApprovalLineSave> savelineList = approvalLineService.selectMyApprovalSaveLineList(approval.getLineId());
		logger.info("savelineList : " + savelineList);
		
		response.setContentType("application/json; charset=utf-8");
		JSONObject sendJson = new JSONObject();
		JSONArray sendArr =  new JSONArray();
		JSONObject jobj = new JSONObject();
		
		jobj.put("appr_id", approval.getApprId());
		jobj.put("emergency", approval.getEmergency());
		jobj.put("dep_name", approval.getDepName());
		jobj.put("job_name", approval.getJobName());
		jobj.put("pos_name", approval.getPosName());
		jobj.put("draft_type", approval.getDraftType());
		jobj.put("drafter_name", approval.getDrafterName());
		
		jobj.put("origin_file", approval.getOriginFile());
		jobj.put("rename_file", approval.getRenameFile());
		
		jobj.put("start_date", (approval.getStartDate() == null) ? "" : approval.getStartDate().toString());
		jobj.put("end_date", (approval.getEndDate() == null) ? "" : approval.getEndDate().toString());
		jobj.put("detail", approval.getDetail());
		jobj.put("title", approval.getTitle());
		jobj.put("appr_date", (approval.getApprDate() == null) ? "" : approval.getApprDate().toString());
		jobj.put("receipt_date", (approval.getReceiptDate() == null) ? "" : approval.getReceiptDate().toString());
		jobj.put("rejection_date", (approval.getRejectionDate() == null) ? "" : approval.getRejectionDate().toString());
		
		jobj.put("line_id", approval.getLineId());
		if(savelineList != null && savelineList.size() > 0) {
			jobj.put("line_name", savelineList.get(0).getLineName());
		}
		
		JSONArray jarr =  new JSONArray();
		if(lineList != null && lineList.size() > 0) {
			
			for(ApprovalLine line :lineList) {
				JSONObject job = new JSONObject();
				job.put("emp_id", line.getEmpId());
				job.put("line_id", line.getLineId());
				job.put("approver_id", line.getApproverId());
				job.put("approver_name", line.getApproverName());
				job.put("pos_name", line.getPosName());
				job.put("stamp_check", line.getStampCheck());
				job.put("stamp_date", (line.getStampDate() == null) ? "" : line.getStampDate().toString());
				jarr.add(job);
			}
		}
		
		//sendJson.put("approval", jobj);
		sendJson.put("approval", jobj);
		sendJson.put("list", jarr);
		logger.info("sendJson : " + sendJson);
		return sendJson.toJSONString();
		
	}
	
	//결재라인 정보 보내기
	@RequestMapping(value = "apsendapp.do", method = RequestMethod.POST)
	public void updateSendApp(
			ApprovalLineKeyword approvalLineKeyword,
			HttpServletResponse response) throws IOException {
		
		logger.info("approvalLineKeyword : " + approvalLineKeyword);
		String chk = "no";
		if(approvalLineService.updateApprLineStampCheck(approvalLineKeyword) > 0) {
			chk = "ok";
			
			//모두 결재가 완료되면 기안서의 결재 진행상태가 완료로 변경
			//만약 하나라도 반려가 되면 반려로 변경
			ArrayList<ApprovalLine> lineList = approvalLineService.selectMyApprovalLineList(approvalLineKeyword.getLineId());
			logger.info("lineList : " + lineList);
			
			String type = "continue";
			int count = 0;
			
			for(ApprovalLine appLine : lineList) {
				
				logger.info("  appLine.getStampCheck() : " + appLine.getStampCheck());
				
				if(appLine.getStampCheck().equals("N")) {
					type = "companion";
					break;
				}
				
				if(appLine.getStampCheck().equals("Y")) {
					count ++;
				}
			}
			
			logger.info("count : " + count + "  lineList.size() : " + lineList.size());
			 
			if(type != "companion") {
				if(count >= lineList.size()) {
					type = "complete";
				}
			}
			logger.info("type : " + type);
			if(approvalService.updateApprovalState(
					new ApprovalKeyword(approvalLineKeyword.getApprId(), type, null)) > 0) {
				//진행상태 업데이트
			}
			
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(chk);
		out.flush();
		out.close();
	}
	
	//인서트
	@RequestMapping("apmoveinsert.do")
	public String insertMoveApprovalMethod(@RequestParam(name="empId") int empId, Model model) {
		logger.info("empId : " + empId);
		Employee employee = employeeService.selectEmployeeApproval(empId);
		logger.info("employee : " + employee);
		//검색
		model.addAttribute("empId", empId);
		model.addAttribute("employee", employee);
		return "approval/appr_input";
	}
	
	@RequestMapping(value = "apinsert.do", method = RequestMethod.POST)
	public String insertApprovalMethod(
			Approval approval,
			@RequestParam(name = "approver_1", required = false) String approver_1, 
			@RequestParam(name = "approver_2", required = false) String approver_2, 
			@RequestParam(name = "approver_3", required = false) String approver_3, 
			@RequestParam(name = "approver_4", required = false) String approver_4, 
			@RequestParam(name = "upfile", required = false) MultipartFile mfile, 
			HttpServletRequest request, Model model) {
		logger.info("apinsert.do -> mfile : " + mfile);
		logger.info("apinsert.do -> approval1 : " + approval);
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/appr_upfiles");
		
		//첨부파일이 있을때 
		if(mfile != null) {
			if(!mfile.isEmpty()) {
				//전송온 파일이름 추출함
				String fileName = mfile.getOriginalFilename();
				String renameFileName = null;
				
				//저장폴더에는 변경된 이름을 저장 처리함
				//파일 이름 바꾸기함 : 년월일시분초.확장자
				if(fileName != null && fileName.length() > 0) {
					//바꿀 파일명에 대한 문자열 만들기
					renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
					logger.info("apinsert.do -> 첨부파일명 확인 : " + fileName + ", " + renameFileName);
					
					try {
						//저장폴더에 파일명 바꾸기 처리
						mfile.transferTo(new File(savePath + "\\" + renameFileName));
						
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "파일명 바꾸기 또는 첨부파일 저장 실패");
						return "common/error";
					}
				}
				
				//첨부파일 정보 저장 처리
				approval.setOriginFile(fileName);
				approval.setRenameFile(renameFileName);
			}
		}
		
		//최신 라인번호 추출 select max+1
		int lineId = approvalLineService.selectApprovalLineId();
		
		if(approval.getSavelineId() == -1) {
			//결재라인이 없을 경우
			String[] approvalArr = {approver_1, approver_2, approver_3, approver_4};
			logger.info("apinsert.do -> approvalArr : " + Arrays.toString(approvalArr));
			int l_count = 1;
			for(int i=0; i<approvalArr.length; i++) {
				if(approvalArr[i] != null && !approvalArr[i].equals("")) {
					ApprovalLine apprLine = new ApprovalLine();
					apprLine.setLineId(lineId);
					apprLine.setLineDepth(l_count);
					apprLine.setEmpId(approval.getDrafter());
					
					String[] strArr =  approvalArr[i].split("#%");
					
					apprLine.setApproverId(Integer.parseInt(strArr[0]));
					apprLine.setApproverName(strArr[1]);
					apprLine.setPosName(strArr[2]);
					
					l_count ++;
					logger.info("apinsert.do -> apprLine : " + apprLine);
					if(approvalLineService.insertApprovalLine(apprLine) > 0){
						logger.info("apinsert.do -> insertApprovalLine 1 : 성공");
					}
				}
			}
			
		}else {
			//결재라인이 있을경우 결재라인에 있는 아이디와 이름들을 사용함
			ArrayList<ApprovalLineSave> lineListSave = approvalLineService.selectMyApprovalSaveLineList(approval.getSavelineId());
			
			for(ApprovalLineSave als : lineListSave) {

				ApprovalLine apprLine2 = new ApprovalLine();
				apprLine2.setLineId(lineId);
				apprLine2.setLineDepth(als.getLineDepth());
				apprLine2.setEmpId(als.getEmpId());
				apprLine2.setApproverId(als.getApproverId());
				apprLine2.setApproverName(als.getApproverName());
				apprLine2.setPosName(als.getPosName());
				logger.info("apinsert.do -> apprLine2 : " + apprLine2);
				//logger.info("" + (approvalLineService.insertApprovalLine(apprLine2) > 0));
				if(approvalLineService.insertApprovalLine(apprLine2) > 0){
					logger.info("apinsert.do -> insertApprovalLine 2 : 성공");
				}
			}
		}
		
		approval.setApprId(approvalService.selectApprovalMaxId());
		approval.setApprState("continue");
		approval.setLineId(lineId);
		model.addAttribute("apType", "my");
		model.addAttribute("empId", approval.getDrafter());
		
		logger.info("apinsert.do -> approval2 : " + approval);
		int result = approvalService.insertApproval(approval);
		logger.info("apinsert.do -> result : " + result);
		
		//드래프트!! 
		if(result > 0) {
			switch(approval.getDraftType()) {
				case "annual":
					if(approvalService.insertDraftAnnual(approval) <= 0) {
						model.addAttribute("message", "드래프트 등록 실패!");
						return "common/error";
					}
					break;
			}
			
		}else {
			model.addAttribute("message", "새 전자결재 게시글 등록 실패!");
			return "common/error";
		}
		
		return "redirect:aplist.do";
	}
	
	//첨부파일 다운로드 요청 처리용 
	@RequestMapping("apdown.do")
	public ModelAndView fileDownMethod(
			ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, 
			@RequestParam("rfile") String renameFileName) {
		//파일다운 메소드는 ModelAndView로 리턴해야 함
		
		//게시글 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/appr_upfiles"); 
		
		//저장 폴더에서 읽을 파일에 대한 파일객체를 생성함
		File renameFile = new File(savePath + "\\" + renameFileName);
		
		//파일 다운 시 브라우저로 내보낼 원래 파일명에 대한 파일 객체 생성함
		File originFile = new File(originalFileName);
		
		//파일 다운로드용 뷰로 전달할 정보 저장 처리
		mv.setViewName("filedown");//등록된 파일다운로드용 뷰클래스의 id명
		mv.addObject("renameFile", renameFile);
		mv.addObject("originFile", originFile);
		
		return mv;
	}
	
	@RequestMapping("apmoveupdate.do")
	public String updateMoveApprovalMethod(@RequestParam("apprId") int apprId, Model model) {
		
		Approval approval = approvalService.selectMyApproval(apprId);
		setDraft(approval);
		logger.info("apmoveupdate.do -> approval : " + approval);
		
		model.addAttribute("pageType", "update");
		model.addAttribute("approval", approval);
		
		model.addAttribute("empId", approval.getDrafter());
		Employee employee = employeeService.selectEmployeeApproval(approval.getDrafter());
		model.addAttribute("employee", employee);
		
		return "approval/appr_input";
	}
	
	@RequestMapping(value = "allinemy.do", method = RequestMethod.POST)
	@ResponseBody // response body에 담아서 보냄
	public String selectApprovalLineMy(Approval approval,
		HttpServletResponse response) throws IOException {
		logger.info("allinemy.do -> getLineId : " + approval.getLineId());
		
		ArrayList<ApprovalLine> lineList = approvalLineService.selectMyApprovalLineList(approval.getLineId());
		logger.info("allinemy.do -> lineList : " + lineList);
		
		JSONObject sendJson = new JSONObject();
		JSONObject jobj = new JSONObject();
		
		JSONArray jarr =  new JSONArray();
		if(lineList != null && lineList.size() > 0) {
			
			for(ApprovalLine line :lineList) {
				JSONObject job = new JSONObject();
				job.put("emp_id", line.getEmpId());
				job.put("line_id", line.getLineId());
				job.put("approver_id", line.getApproverId());
				job.put("approver_name", line.getApproverName());
				job.put("pos_name", line.getPosName());
				job.put("stamp_check", line.getStampCheck());
				job.put("stamp_date", (line.getStampDate() == null) ? "" : line.getStampDate().toString());
				jarr.add(job);
			}
		}
		response.setContentType("application/json; charset=utf-8");
		sendJson.put("list", jarr);
		return sendJson.toJSONString();
	}
	
	//수정하기
	@RequestMapping(value = "apupdate.do", method = RequestMethod.POST)
	public String updateApprovalMethod(
			Approval approval,
			@RequestParam(name = "approver_1", required = false) String approver_1, 
			@RequestParam(name = "approver_2", required = false) String approver_2, 
			@RequestParam(name = "approver_3", required = false) String approver_3, 
			@RequestParam(name = "approver_4", required = false) String approver_4, 
			@RequestParam(name = "upfile", required = false) MultipartFile mfile, 
			HttpServletRequest request, Model model) {
		logger.info("apupdate.do -> mfile : " + mfile);
		logger.info("apupdate.do -> approval1 : " + approval);
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/appr_upfiles");
		
		//첨부파일이 있을때 
		if(mfile != null) {
			if(!mfile.isEmpty()) {
				new File(savePath + "\\" + approval.getRenameFile()).delete();
				//만약 첨부된 업데이트 파일이 있다면
				if(approval.getOriginFile() != null) {
					approval.setOriginFile(null);
					approval.setRenameFile(null);
				}
				
				//전송온 파일이름 추출함
				String fileName = mfile.getOriginalFilename();
				String renameFileName = null;
				
				//저장폴더에는 변경된 이름을 저장 처리함
				//파일 이름 바꾸기함 : 년월일시분초.확장자
				if(fileName != null && fileName.length() > 0) {
					//바꿀 파일명에 대한 문자열 만들기
					renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
					logger.info("apupdate.do -> 첨부파일명 확인 : " + fileName + ", " + renameFileName);
					
					try {
						//저장폴더에 파일명 바꾸기 처리
						mfile.transferTo(new File(savePath + "\\" + renameFileName));
						
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "파일명 바꾸기 또는 첨부파일 저장 실패");
						return "common/error";
					}
				}
				
				//첨부파일 정보 저장 처리
				approval.setOriginFile(fileName);
				approval.setRenameFile(renameFileName);
			}
		}
		
		//최신 라인번호 추출 select max+1
		int lineId = approvalLineService.selectApprovalLineId();
		
		if(approval.getSavelineId() == -1) {
			//결재라인이 없을 경우
			String[] approvalArr = {approver_1, approver_2, approver_3, approver_4};
			logger.info("apupdate.do -> approvalArr : " + Arrays.toString(approvalArr));
			int l_count = 1;
			for(int i=0; i<approvalArr.length; i++) {
				if(approvalArr[i] != null && !approvalArr[i].equals("")) {
					ApprovalLine apprLine = new ApprovalLine();
					apprLine.setLineId(lineId);
					apprLine.setLineDepth(l_count);
					apprLine.setEmpId(approval.getDrafter());
					
					String[] strArr =  approvalArr[i].split("#%");
					
					apprLine.setApproverId(Integer.parseInt(strArr[0]));
					apprLine.setApproverName(strArr[1]);
					apprLine.setPosName(strArr[2]);
					
					l_count ++;
					logger.info("apupdate.do -> apprLine : " + apprLine);
					if(approvalLineService.insertApprovalLine(apprLine) > 0){
						logger.info("apupdate.do -> insertApprovalLine 1 : 성공");
					}
				}
			}
			
		}else {
			//결재라인이 있을경우 결재라인에 있는 아이디와 이름들을 사용함
			ArrayList<ApprovalLineSave> lineListSave = approvalLineService.selectMyApprovalSaveLineList(approval.getSavelineId());
			
			for(ApprovalLineSave als : lineListSave) {
				
				ApprovalLine apprLine2 = new ApprovalLine();
				apprLine2.setLineId(lineId);
				apprLine2.setLineDepth(als.getLineDepth());
				apprLine2.setEmpId(als.getEmpId());
				apprLine2.setApproverId(als.getApproverId());
				apprLine2.setApproverName(als.getApproverName());
				apprLine2.setPosName(als.getPosName());
				logger.info("apupdate.do -> apprLine2 : " + apprLine2);
				//logger.info("" + (approvalLineService.insertApprovalLine(apprLine2) > 0));
				if(approvalLineService.insertApprovalLine(apprLine2) > 0){
					logger.info("apupdate.do -> insertApprovalLine 2 : 성공");
				}
			}
		}
		
		approval.setApprState("continue");
		approval.setLineId(lineId);
		model.addAttribute("apType", "my");
		model.addAttribute("empId", approval.getDrafter());
		
		logger.info("apupdate.do -> approval2 : " + approval);
		int result = approvalService.updateApproval(approval);
		logger.info("apupdate.do -> result : " + result);
		
		//드래프트!! 
		if(result > 0) {
			switch(approval.getDraftType()) {
				case "annual":
					if(approvalService.updateDraftAnnual(approval) <= 0) {
						model.addAttribute("message", "드래프트 등록 실패!");
						return "common/error";
					}
					break;
			}
			
		}else {
			model.addAttribute("message", "새 전자결재 게시글 등록 실패!");
			return "common/error";
		}
		
		return "redirect:aplist.do";
	}
}
