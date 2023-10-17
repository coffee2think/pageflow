package com.erl.pageflow.refund.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
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
		return "inventory/refund_input";
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

		for (Refund ref : list) {
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

	// 반품 날짜로 조회
	@RequestMapping("refunddate.do")
	public String selectReleaseByDate(Search search, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {
		int currentPage = 1;
		int limit = 10;

		int listCount = refundService.selectRefundCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "refunddate.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Refund> list = refundService.selectRefundByDate(search);
		logger.info("search : " + search);
		if (list != null && list.size() > 0) {
			for (Refund ref : list) {
				String bname = refundService.selectRefundBookName(ref.getBookId());
				String cname = refundService.selectRefundClientName(ref.getClientId());
				int bprice = refundService.selectRefundBookPrice(ref.getBookId());

				ref.setBookName(bname);
				ref.setClientName(cname);
				ref.setBookPrice(bprice);
			}

			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/refund_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}

	}
	
	// 반품 삭제
		@RequestMapping(value = "redelect.do", method = RequestMethod.POST)
		@ResponseBody
		public void deleteRefund(@RequestParam(name = "selectedRefundIds") String selectedRefundIds,
				HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

			int count = 0;
			String[] refundArray = selectedRefundIds.split(",");
			// logger.info("selectedStoreIds" + selectedStoreIds);
			// logger.info("storeArray length : " + storeArray.length);
			for (int i = 0; i < refundArray.length; i++) {

				int sId = Integer.parseInt(refundArray[i].toString());

				if (refundService.deleteInventory(sId) > 0) {

					// logger.info("deleteInventory " + i);
					if (refundService.deleteRefund(sId) > 0) {
						// logger.info("deleteStore " + i);
						count++;
					} else {
						model.addAttribute("message", sId + "번 반품 삭제 실패");
						// return "common/error";
					}
				} else {
					model.addAttribute("message", sId + "번 재고 삭제 실패");
					// return "common/error";
				}
			}

			// return "redirect:storelist.do";
			if (count >= refundArray.length) {
				response.getWriter().append(String.valueOf(count)).flush();
			}

		}

}
