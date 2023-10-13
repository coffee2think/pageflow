package com.erl.pageflow.refund.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.refund.model.service.RefundService;

@Controller
public class RefundController {
	private static final Logger logger = LoggerFactory.getLogger(RefundController.class);

	@Autowired
	private RefundService refundService;

	// 뷰------------------------------------------------------------------------------------------------------------------

	// 반품등록
	@RequestMapping("refundinput.do")
	public String moveRefundInput() {
		return "inventory/release_input";
	}

	// 값------------------------------------------------------------------------------------------------------------------
	// 반품현황
//	@RequestMapping("refundlist.do")
//	public String moveRefundList(Model model) {
//		
//		int currentPage = 1;
//		int limit = 10;
//
//		// 총 페이지 수 계산을 위한 공지글 총갯수 조회
//		int listCount = refundService.selectGetListCount();
//		// 페이지 관련 항목 계산 처리
//		Paging paging = new Paging(listCount, currentPage, limit, "inventorylist.do");
//		paging.calculator();
//
//		ArrayList<Inventory> list = refundService.selectRefundList(paging); 
//
//		
//	}

}
