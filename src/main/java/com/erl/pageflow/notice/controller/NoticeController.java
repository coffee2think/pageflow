package com.erl.pageflow.notice.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erl.pageflow.common.FileNameChange;
import com.erl.pageflow.common.Paging;
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

	@RequestMapping(value = "noinsert.do", method = RequestMethod.POST)
	public String noticeInsertMethod(Notice notice, Model model, HttpServletRequest request,
			@RequestParam(name = "nfile", required = false) MultipartFile mfile) {

		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");
		logger.info("mfile : " + mfile);
		// 첨부파일이 있을때
		if ( !mfile.isEmpty()) {
			
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

			}

			if (noticeService.insertNotice(notice) > 0) {
				model.addAttribute("empId", notice.getEmpid());
				return "redirect:nlist.do";
			} else {
				model.addAttribute("message", "새 게시글 등록 실패!");
				return "common/error";
			}
				
		}
	
}
