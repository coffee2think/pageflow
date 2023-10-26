package com.erl.pageflow.notice.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.common.FileNameChange;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

import com.erl.pageflow.employee.model.service.EmployeeService;
import com.erl.pageflow.employee.model.vo.Department;
import com.erl.pageflow.employee.model.vo.Employee;

import com.erl.pageflow.notice.model.service.NoticeService;
import com.erl.pageflow.notice.model.vo.Notice;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private EmployeeService employeeService;

	// 뷰 페이지 내보내기용 --------------------------------------------------------
	// 새 공지글 등록 페이지 이동 처리용
	@RequestMapping("nemoveinsert.do")
	public String moveWritePage(Model model) {
		ArrayList<Department> depList = employeeService.selectDepartmentList();
		
		if(depList != null && depList.size() > 0) {
			model.addAttribute("depList", depList);
		}
		
		return "work/notice_input";
	}

	// 공지글 수정페이지로 이동 처리용
	@RequestMapping("nmoveup.do")
	public ModelAndView moveUpdatePage(@RequestParam("noticeId") int noticeId, ModelAndView mv) {
		// 수정페이지에 출력할 공지글 조회해 옴
		Notice notice = noticeService.selectOne(noticeId);

		if (notice != null) {
			mv.addObject("notice", notice);
			mv.setViewName("work/notice_update");
		} else {
			mv.addObject("message", noticeId + "번 공지글 수정페이지로 이동 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 공지사항 전체 목록보기 요청 처리용
	@RequestMapping("nlist.do")
	public String noticeListMethod(Notice notice, HttpServletRequest request,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String slimit, Model model) {
		
		HttpSession session = request.getSession();
	    Employee loginMember = (Employee) session.getAttribute("loginMember");
		
		// 페이징
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;
		int listCount = noticeService.selectListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "nlist.do");
		paging.calculator();
		
		Search search = new Search();
		
		search.setKeyword(loginMember.getAdminYN());
		search.setEmpId(loginMember.getEmpId());
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		logger.info("nlist.do : " + search);
		
		// 페이지에 출력할 목록 조회해 옴
		ArrayList<Notice> list = noticeService.selectNoticeList(search);
		ArrayList<Notice> importantList = noticeService.selectImportantNoticeList(search);

		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
		}
		
		if (importantList != null && importantList.size() > 0) {
			model.addAttribute("importantList", importantList);
		}
		
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("limit", limit);
		
		return "work/notice_list";
	}

	// 공지 글 등록 (첨부파일 첨부)
	@RequestMapping(value="noinsert.do", method=RequestMethod.POST)
	public String noticeInsertMethod(Model model, HttpServletRequest request,
			@RequestParam(name = "nofile", required = false) MultipartFile mfile) {

		logger.info("noinsert.do : mfile - " + mfile);
		
		Notice notice = new Notice();
		notice.setNoticeTitle(request.getParameter("noticeTitle"));
		notice.setNoticeDetail(request.getParameter("noticeDetail"));
		
		String classify = request.getParameter("classify");
		if(classify.equals("dept")) {
			classify += request.getParameter("depType");
		}
		notice.setClassify(classify);
		logger.info("classify : " + notice.getClassify());
		
		notice.setEmpId(Integer.parseInt(request.getParameter("empId")));
		
		String importance = request.getParameter("importance");
		if(importance != null) {
			notice.setImportance(importance);
			notice.setImportanceDate(Date.valueOf(request.getParameter("importanceDate")));
		}
		
		// 첨부파일이 있을때
		if (mfile != null && !mfile.isEmpty()) {
			// 전송온 파일이름 추출함
			String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함
			// 파일 이름 바꾸기함 : 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);

				try {
					// 저장폴더에 파일명 바꾸기 처리
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "파일명 바꾸기 또는 첨부파일 저장 실패");
					return "common/error";
				}
			}
			
			notice.setNoticeOriginalFileName(fileName);
			notice.setNoticeRenameFileName(renameFileName);
		}
		
		if (noticeService.insertNotice(notice) > 0) {
			int result = 0;
			
			switch(request.getParameter("classify")) {
			case "all":
				result = noticeService.updateNoticeAlarmAll();
				break;
			case "dept":
				result = noticeService.updateNoticeAlarmDept(Integer.parseInt(request.getParameter("depType")));
				break;
			case "emp":
				String[] empListStr = request.getParameterValues("empList");
				Integer[] empList = new Integer[empListStr.length];
				for(int i = 0; i < empList.length; i++) {
					empList[i] = Integer.parseInt(empListStr[i]);
				}
				List<Integer> empIdList = Arrays.asList(empList);
				result = noticeService.updateNoticeAlarmEmp(empIdList);
				break;
			}
			
			if(result > 0) {
				return "redirect:nlist.do";
			} else {
				model.addAttribute("message", "알람 업데이트 실패");
				return "common/error";
			}
		} else {
			model.addAttribute("message", "새 게시글 등록 실패!");
			return "common/error";
		}
	}

	// 첨부파일 다운로드 요청 처리용
	@RequestMapping("nfdown.do")
	public ModelAndView fileDownMethod(ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName,
			@RequestParam("rfile") String renameFileName) {
		// 파일 다운 메소드는 리턴 타입이 ModelAndView 로 정해져 있음

		logger.info("nfdown.do : originalFileName - " + originalFileName);
		logger.info("nfdown.do : renameFileName - " + renameFileName);

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");

		// 저장 폴더에서 읽을 파일에 대한 파일 객체 생성함
		File renameFile = new File(savePath + "\\" + renameFileName);
		// 파일 다운시 브라우저 내보낼 원래 파일이름에 대한 파일 객체 생성함
		File originFile = new File(originalFileName);

		// 파일 다운로드용 뷰로 전달할 정보 저장 처리
		mv.setViewName("filedown"); // 등록된 파일다운로드용 뷰클래스의 id명
		mv.addObject("originFile", originFile);
		mv.addObject("renameFile", renameFile);

		return mv;
	}

	// 공지글 상세보기 요청 처리용
	@RequestMapping("ndetail.do")
	public ModelAndView noticeDetailMethod(
			@RequestParam("noticeId") int noticeId,
			ModelAndView mv, HttpSession session) {

		int loginMemberId = ((Employee) session.getAttribute("loginMember")).getEmpId();
		
		// 조회수 증가 처리
		noticeService.updateReadCount(noticeId);
		
		// 읽음 처리 
		Notice refNotice = new Notice();
		refNotice.setNoticeId(noticeId);
		refNotice.setEmpId(loginMemberId);
		
		// 읽은 기록이 없다면 읽은 기록을 추가하고 공지알림 개수를 -1함
		if(noticeService.selectReferenceNotice(refNotice) == 0) {
			noticeService.insertReferenceNotice(refNotice);
			noticeService.updateMinusNoticeAlarm(loginMemberId);
		}
		
		Notice notice = noticeService.selectOne(noticeId);
		

		if (notice != null) {
			int readEmpCount = noticeService.selectReadEmpCount(noticeId);

			mv.addObject("notice", notice);
			mv.addObject("readEmpCount", readEmpCount);
			mv.setViewName("work/notice_notice");
		} else {
			mv.addObject("message", noticeId + "번 공지글 상세보기 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 공지글 수정 요청 처리용 (파일 업로드 기능 사용
	@RequestMapping(value = "noupdate.do", method = RequestMethod.POST)
	public String noticeUpdateMethod(Notice notice, Model model, HttpServletRequest request,
			@RequestParam(name = "deleteFlag", required = false) String delFlag,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		logger.info("noupdate.do : " + notice);
		logger.info("upfile : " + mfile);

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");

		// 첨부파일이 변경된 경우의 처리 --------------------------------------------------------
		// 1. 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		// 또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if (notice.getNoticeOriginalFileName() != null
				&& ((delFlag != null && delFlag.equals("Y")) || (mfile != null && !mfile.isEmpty()))) {
			// 저장 폴더에서 파일 삭제함

			new File(savePath + "\\" + notice.getNoticeRenameFileName()).delete();
			// notice 안의 파일정보도 제거함
			notice.setNoticeOriginalFileName(null);
			notice.setNoticeRenameFileName(null);
		}

		// 2. 새로운 첨부파일이 있을 때 (공지글 첨부파일은 1개임)
		if (mfile != null && !mfile.isEmpty()) {
			// 전송온 파일이름 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함
			// 파일 이름바꾸기함 : 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				try {
					// 저장 폴더에 파일명 바꾸기 처리
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
					logger.info("첨부파일명 확인 : " + savePath + ", " + renameFileName);
				} catch (Exception e) {
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			} // 파일명 바꾸기
				// notice 객체에 첨부파일 정보 저장 처리
			notice.setNoticeOriginalFileName(fileName);
			notice.setNoticeRenameFileName(renameFileName);
		} // 첨부파일 있을 때

		if (noticeService.updateNotice(notice) > 0) {
			// 공지글 수정 성공시 목록 보기 페이지로 이동
			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", notice.getNoticeId() + "번 공지 수정 실패!");
			return "common/error";
		}
	}

	// 공지글 삭제 요청 처리용
	@RequestMapping("ndelete.do")
	public String noticeDeleteMethod(@RequestParam("noticeId") int noticeId,
			@RequestParam(name = "rfile", required = false) String renameFileName, HttpServletRequest request,
			Model model) {

		if(noticeService.deleteReferenceNotice(noticeId) == 0) {
			model.addAttribute("message", noticeId + "번 공지 삭제 실패!");
			return "common/error";
		}
		
		if (noticeService.deleteNotice(noticeId) > 0) {
			// 공지글 삭제 성공시 저장 폴더에 있는 첨부파일도 삭제함
			if (renameFileName != null) {
				// 공지사항 첨부파일 저장 폴더 경로 지정
				String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");
				// 저장 폴더에서 파일 삭제함
				new File(savePath + "\\" + renameFileName).delete();
			}

			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", noticeId + "번 공지 삭제 실패!");
			return "common/error";
		}
	}

	// ----------------서치-----------------

	@RequestMapping(value = "nsearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView noticeSearchContentMethod(Search search,
			@RequestParam(name = "limit", required = false) String slimit,
			@RequestParam(name = "page", required = false) String page, ModelAndView mv) {
		
		logger.info("nsearh.do : " + search);
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;
		
		int listCount = 0;
		switch(search.getSearchType()) {
		case "title":
			listCount = noticeService.selectSearchTitleCount(search.getKeyword());
			break;
		case "writer":
			listCount = noticeService.selectSearchWriterCount(search.getKeyword());
			break;
		}

		Paging paging = new Paging(listCount, currentPage, limit, "nsearch.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Notice> list = null;
		switch (search.getSearchType()) {
		case "title":
			list = noticeService.selectSearchTitle(search);
			break;
		case "writer":
			list = noticeService.selectSearchWriter(search);
			break;
		}

		// 받은 결과에 따라 성공/실패 페이지 내보내기
		if (list != null && list.size() > 0) {

			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currentPage", currentPage);
			mv.addObject("limit", limit);
			mv.addObject("searchType", search.getSearchType());
			mv.addObject("keyword", search.getKeyword());

			mv.setViewName("work/notice_list");
		} else {
			mv.addObject("message", search.getSearchType() + "에 대한 " + search.getKeyword() + " 검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	@RequestMapping("ndate.do")
	public String selectNoticeByDate(Search search, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = noticeService.selectNoticeCountByDate(search);

		logger.info("ndate.do : " + listCount);

		Paging paging = new Paging(listCount, currentPage, limit, "ndate.do");

		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Notice> list = noticeService.selectNoticeByDate(search);

		if (list != null && list.size() > 0) {
			logger.info("ndate.do : " + list);
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "work/notice_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}

	}
	// 메인페이지 ajax 통신
	// ******************************************************************

	@RequestMapping(value = "ntop.do", method = RequestMethod.POST)
	@ResponseBody
	public String bookOrderNewTop3Method() throws UnsupportedEncodingException {
		ArrayList<Notice> list = noticeService.selectNewTop();

		JSONObject sendJson = new JSONObject();

		JSONArray jarr = new JSONArray();

		for (Notice notice : list) {
			JSONObject job = new JSONObject();

			job.put("ndate", notice.getNoticeCreateDate().toString());
			job.put("noticeTitle", URLEncoder.encode(notice.getNoticeTitle(), "utf-8"));

			jarr.add(job);

		}
		sendJson.put("nlist", jarr);

		return sendJson.toJSONString();
	}
}