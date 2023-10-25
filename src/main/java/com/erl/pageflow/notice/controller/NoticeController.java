package com.erl.pageflow.notice.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

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
import com.erl.pageflow.notice.model.service.NoticeService;
import com.erl.pageflow.notice.model.vo.Notice;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	// 뷰 페이지 내보내기용 --------------------------------------------------------
	// 새 공지글 등록 페이지 이동 처리용
	@RequestMapping("nemoveinsert.do")
	public String moveWritePage() {
		return "work/notice_input";
	}

	// 공지글 수정페이지로 이동 처리용
	@RequestMapping("ndmoveupdate.do")
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
	public String noticeListMethod(Notice notice, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지 공지 10개씩 출력되게 한다면
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 총 페이지 수 계산을 위한 공지글 총갯수 조회
		// int listCount = noticeService.selectListCount();
		int listCount = noticeService.selectListCount();
		// 페이지 관련 항목 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "nlist.do");
		paging.calculator();

		// 페이지에 출력할 목록 조회해 옴
		ArrayList<Notice> list = noticeService.selectList(paging);

		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "work/notice_list";
		} else {
			model.addAttribute("message", currentPage + "페이지 목록 조회 실패!");
			return "common/error";
		}
	}

	// 공지 글 등록 (첨부파일 첨부)
	@RequestMapping(value = "noinsert.do", method = RequestMethod.POST)
	public String noticeInsertMethod(Notice notice, Model model, HttpServletRequest request,
			@RequestParam(name = "nofile", required = false) MultipartFile mfile) {

		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");
		logger.info("mfile : " + mfile);

		// 첨부파일이 있을때
		if (!mfile.isEmpty()) {

			// 전송온 파일이름 추출함
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
			model.addAttribute("empId", notice.getEmpId());
			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", "새 게시글 등록 실패!");
			return "common/error";
		}

	}

	// 첨부파일 다운로드 요청 처리용

	@RequestMapping("nfdown.do")
	public ModelAndView fileDownMethod(ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, @RequestParam("rfile") String renameFileName) {
		// 파일 다운 메소드는 리턴 타입이 ModelAndView 로 정해져 있음

		logger.info(renameFileName);

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");

		// 저장 폴더에서 읽을 파일에 대한 파일 객체 생성함
		File renameFile = new File(savePath + "\\" + renameFileName);
		// 파일 다운시 브라우저 내보낼 원래 파일이름에 대한 파일 객체 생성함
		File originFile = new File(originalFileName);

		// 파일 다운로드용 뷰로 전달할 정보 저장 처리
		mv.setViewName("filedown"); // 등록된 파일다운로드용 뷰클래스의 id명
		mv.addObject("renameFileName", renameFileName);
		mv.addObject("originalFilName", originFile);

		return mv;
	}

	// 공지글 상세보기 요청 처리용
	@RequestMapping("nolist.do")
	public ModelAndView noticeDetailMethod(@RequestParam("noticeId") int noticeId, ModelAndView mv,
			HttpSession session) {

		// 조회수 1증가 처리
		noticeService.updateReadCount(noticeId);

		Notice notice = noticeService.selectOne(noticeId);

		if (notice != null) {
			mv.addObject("notice", notice);
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

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");

		// 첨부파일이 변경된 경우의 처리 --------------------------------------------------------
		// 1. 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		// 또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if (notice.getNoticeOriginalFileName() != null && (delFlag != null && delFlag.equals("yes"))
				|| (mfile != null && !mfile.isEmpty())) {
			// 저장 폴더에서 파일 삭제함

			new File(savePath + "\\" + notice.getNoticeRenameFileName()).delete();
			// notice 안의 파일정보도 제거함
			notice.setNoticeOriginalFileName(null);
			notice.setNoticeRenameFileName(null);
		}

		// 2. 새로운 첨부파일이 있을 때 (공지글 첨부파일은 1개임)
		if ((mfile != null) && !mfile.isEmpty()) {
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
	public String noticeDeleteMethod(@RequestParam("noticeId") int noticeid,
			@RequestParam(name = "rfile", required = false) String renameFileName, HttpServletRequest request,
			Model model) {

		if (noticeService.deleteNotice(noticeid) > 0) {
			// 공지글 삭제 성공시 저장 폴더에 있는 첨부파일도 삭제함
			if (renameFileName != null) {
				// 공지사항 첨부파일 저장 폴더 경로 지정
				String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");
				// 저장 폴더에서 파일 삭제함
				new File(savePath + "\\" + renameFileName).delete();
			}

			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", noticeid + "번 공지 삭제 실패!");
			return "common/error";
		}
	}

	// ----------------서치-----------------

	@RequestMapping(value = "nsearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView noticeSearchContentMethod(@RequestParam("action") String action,
			@RequestParam("keyword") String keyword, @RequestParam(name = "limit", required = false) String slimit,
			@RequestParam(name = "page", required = false) String page, ModelAndView mv) {
		logger.info("nsearh.do : " + action);
		logger.info("nsearh.do : " + keyword);
		// 검색결과에 대한 페이징 처리
		// 출력할 페이지 지정
		int currentPage = 1;
		// 전송온 페이지 값이 있다면 추출함
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지당 출력할 목록 갯수 지정
		int limit = 10;
		// 전송 온 limit 값이 있다면
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		int listCount = 0;

		switch (action) {
		case "title":
			listCount = noticeService.selectSearchTitleCount(keyword);
			break;
		case "writer":
			listCount = noticeService.selectSearchWriterCount(keyword);
			break;

		}

		// 총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회

		// 뷰 페이지에 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "nsearch.do");
		paging.calculator();

		// 서비스 메소드 호출하고 리턴결과 받기
		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setKeyword(keyword);

		ArrayList<Notice> list = null;
		switch (action) {
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
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);

			mv.setViewName("work/notice_list");
		} else {
			mv.addObject("message", action + "에 대한 " + keyword + " 검색 결과가 존재하지 않습니다.");
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