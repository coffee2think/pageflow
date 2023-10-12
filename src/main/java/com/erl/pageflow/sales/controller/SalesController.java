package com.erl.pageflow.sales.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.board.controller.BoardController;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.service.SalesService;

@Controller
public class SalesController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private SalesService salesService;
	
	//**************뷰페이지 이동****************
	@RequestMapping("borderinput.do")
	public String moveBookOrderInputPage() {
		return "sales/border_list";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	@RequestMapping("borderlist.do")
	public String bookOrderList(Search search, Model model,
			@RequestParam(name="code") String code) {
		if(search.getKeyword() == null && (search.getBegin() == null || search.getEnd() == null)) {
			
		} else if 
		
		ArrayList<BookOrder> list = salesService.selectBookOrderList();
		
		return "sales/border_list";
	}
}
