package com.erl.pageflow.writer.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.writer.model.service.WriterService;
import com.erl.pageflow.writer.model.vo.Writer;

@Controller
public class WriterController {
	private static final Logger logger = LoggerFactory.getLogger(WriterController.class);

	@Autowired
	private WriterService writerService;
	
	// 작가 리스트 조회
	@RequestMapping("wtlist.do")
	public String selectWriterListMethod(@RequestParam(name = "page", required = false) String page,
				@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		int listCount = writerService.selectWriterListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "wtlist.do");
		paging.calculator();
		
		ArrayList<Writer> list = writerService.selectWriterList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("writerList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/writer_list";
		} else {
			model.addAttribute("message", "작가 목록 조회 실패!");
			return "common/error";
		}
	}
}
