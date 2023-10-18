package com.erl.pageflow.edit.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.edit.model.service.EditService;
import com.erl.pageflow.edit.model.vo.Edit;

@Controller
public class EditController {
	private static final Logger logger = LoggerFactory.getLogger(EditController.class);

	@Autowired
	private EditService editService;
	
	// 편집 리스트 조회
	@RequestMapping("edlist.do")
	public String selectEditListMethod(@RequestParam(name = "page", required = false) String page,
				@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page); 
		}
		
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		int listCount = editService.selectEditListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "edlist.do");
		paging.calculator();
		
		ArrayList<Edit> list = editService.selectEditList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("editList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "publish/edit_list";
		} else {
			model.addAttribute("message", "편집 목록 조회 실패!");
			return "common/error";
		}
	}
}
