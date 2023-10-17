package com.erl.pageflow.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.notice.model.service.NoticeService;
import com.erl.pageflow.notice.model.vo.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	//공지사항 전체 목록보기 요청 처리용
		@RequestMapping("nlist.do")
		public String noticeListMethod(Notice notice,
				@RequestParam(name="page", required=false) String page, 
				@RequestParam(name="limit", required=false) String slimit, 
				Model model) {
			int currentPage = 1;
			if (page != null) {
				currentPage = Integer.parseInt(page);
			}
			
			//한 페이지 공지 10개씩 출력되게 한다면
			int limit = 10;
			if (slimit != null) {
				limit = Integer.parseInt(slimit);
			}
			
			//총 페이지 수 계산을 위한 공지글 총갯수 조회
			//int listCount = noticeService.selectListCount();
			int listCount = noticeService.selectListCount();
			//페이지 관련 항목 계산 처리
			Paging paging = new Paging(listCount, currentPage, limit, "nlist.do");
			paging.calculator();
			
			//페이지에 출력할 목록 조회해 옴
			ArrayList<Notice> list = noticeService.selectList(paging);
			
			if(list != null && list.size() > 0) {
				model.addAttribute("list", list);
				model.addAttribute("paging", paging);
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("limit", limit);
				
				return "work/notice_list";
			}else {
				model.addAttribute("message", currentPage + "페이지 목록 조회 실패!");
				return "common/error";
			}
		}
}
