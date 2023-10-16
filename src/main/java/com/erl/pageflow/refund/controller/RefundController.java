package com.erl.pageflow.refund.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.refund.model.service.RefundService;
import com.erl.pageflow.refund.model.vo.Refund;

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
	// 반품현황 뷰
	@RequestMapping("refundlist.do")
	public String moveRefundList(Model model) {

		int currentPage = 1;
		int limit = 10;

		int listCount = refundService.selectGetListCount();
		
		Paging paging = new Paging(listCount, currentPage, limit, "refundlist.do");
		paging.calculator();

		ArrayList<Refund> list = refundService.selectRefundList(paging);

		for(Refund ref : list) {
			String bname = refundService.selectRefundBookName(ref.getBookId());
			String cname = refundService.selectRefundClientName(ref.getClientId());
			int bprice = refundService.selectRefundBookPrice(ref.getBookId());
			
			ref.setBookName(bname);
			ref.setClientName(cname);
			ref.setBookPrice(bprice);
		}

		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/refund_list";
		} else {
			model.addAttribute("message", "반품조회 실패");
			return "common/error";
		}
	}

}
