package com.erl.pageflow.refund.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.refund.model.service.RefundService;
import com.erl.pageflow.refund.model.vo.Refund;
import com.erl.pageflow.store.model.vo.Store;

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
	public String moveRefundList(Model model, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

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
			model.addAttribute("refundList", list);
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
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = refundService.selectRefundCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "refunddate.do");

		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Refund> list = refundService.selectRefundByDate(search);
		logger.info("search : " + search);

		if (list != null && list.size() > 0) {
			model.addAttribute("refundList", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			
		} else {
			model.addAttribute("message", "날짜 검색 실패");
		}
		return "inventory/refund_list";
	}

	// 반품 삭제
	@RequestMapping(value = "redelect.do", method = RequestMethod.POST)
	/* @ResponseBody */
	public void deleteRefund(HttpServletResponse response, @RequestBody String param)
			throws ParseException, IOException {
		JSONParser jparser = new JSONParser();
		JSONArray jarr = (JSONArray) jparser.parse(param);
		String[] jst = null;

		int count = 0;

		JSONObject job = (JSONObject) jarr.get(0);
		JSONArray scbkeyArray = (JSONArray) job.get("scbkey");

		for (int j = 0; j < scbkeyArray.size(); j++) {
			String scbkey = (String) scbkeyArray.get(j);
			int scbkeyInt = Integer.parseInt(scbkey);

			if (refundService.deleteInventory(scbkeyInt) > 0) {
				if (refundService.deleteRefund(scbkeyInt) > 0) {
					System.out.println("출고 삭제 : " + scbkeyInt);
					count++;
				}
			}
		}

		String str = "no";
		if (count >= scbkeyArray.size()) {
			str = "ok";
		}

		PrintWriter out = response.getWriter();
		out.append(str);
		out.flush();
		out.close();
	}

	// 반품 키워드 검색

	@RequestMapping(value = "refselectkeyword.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectInvenSearchKeyword(Search search, @RequestParam(name = "searchType") String searchType,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = 0;

		logger.info("searchType : " + searchType);

		switch (searchType) {
		case "bookId":
			listCount = refundService.selectrefundCountByBookId(search);
			break;
		case "bookName":
			listCount = refundService.selectrefundCountByBookName(search);
			break;
		case "empName":
			listCount = refundService.selectrefundCountByEmpName(search);
			break;
		case "clientName":
			listCount = refundService.selectrefundCountByClientName(search);
			break;
		}

		Paging paging = new Paging(listCount, currentPage, limit, "refselectkeyword.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		logger.info("search : " + search);
		ArrayList<Refund> list = null;

		switch (searchType) {
		case "bookId":
			list = refundService.selectrefundBybookId(search);
			break;
		case "bookName":
			list = refundService.selectrefundBybookName(search);
			break;
		case "empName":
			list = refundService.selectrefundByEmpName(search);
			break;
		case "clientName":
			list = refundService.selectrefundByClientName(search);
			break;

		}
		if (list != null && list.size() > 0) {

			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			model.addAttribute("refundList", list);

		} else {
			model.addAttribute("message", "키워드 검색 실패");

		}
		return "inventory/refund_list";
	}

	// 반품등록
	@RequestMapping(value = "reinput.do", method = RequestMethod.POST)
	public String insertRefund(HttpServletRequest request, Model model) {
		String[] bookIds = request.getParameterValues("bookId");
		String[] clientIds = request.getParameterValues("clientId");
		String[] refundStates = request.getParameterValues("refundState");
		String[] refundDates = request.getParameterValues("refundDate");
		String[] refundNums = request.getParameterValues("refundNum");
		String[] refundAmounts = request.getParameterValues("refundAmount");
		String[] bookPrices = request.getParameterValues("bookPrice");
		String[] remarks = request.getParameterValues("remark");

		logger.info("bookIds : " + Arrays.toString(bookIds));
		logger.info("clientIds : " + Arrays.toString(clientIds));
		logger.info("refundStates : " + Arrays.toString(refundStates));
		logger.info("refundDates : " + Arrays.toString(refundDates));
		logger.info("refundNums : " + Arrays.toString(refundNums));
		logger.info("refundAmounts : " + Arrays.toString(refundAmounts));
		logger.info("bookPrices : " + Arrays.toString(bookPrices));
		logger.info("remarks : " + Arrays.toString(remarks));

		int refundId = refundService.selectMaxRefundId() + 1;

		ArrayList<Refund> refundList = new ArrayList<>();

		for (int i = 0; i < bookIds.length; i++) {
			Refund refund = new Refund();

			refund.setRefundId(refundId);
			refund.setBookId(Integer.parseInt(bookIds[i]));
			refund.setClientId(Integer.parseInt(clientIds[i]));
			refund.setRefundNum(Integer.parseInt(refundNums[i]));
			refund.setRefundDate(Date.valueOf(refundDates[i]));
			refund.setRefundAmount(Integer.parseInt(refundAmounts[i]));
			refund.setRefundState(refundStates[i]);
			refund.setRemark(remarks[i]);
			refund.setBookPrice(Integer.parseInt(bookPrices[i]));

			refundList.add(refund);
			logger.info("refundList : " + refundList);
		}

		for (Refund refund : refundList) {

			if (refundService.insertRefund(refund) > 0) {
			} else {
				model.addAttribute("message", "반품등록 실패! ");
				return "common/error";
			}
		}
		ArrayList<Inventory> temp = new ArrayList<Inventory>();

		for (Refund refund : refundList) {
			Inventory inventory = new Inventory();
			inventory.setBookId(refund.getBookId());
			inventory.setClientId(refund.getClientId());
			inventory.setRefundId(refundId);
			inventory.setIncrease(refund.getRefundNum());
			inventory.setInvenDate(refund.getRefundDate());
			inventory.setRemark(refund.getRemark());
			// 이전재고, 현재고

			temp.add(inventory);
			logger.info("temp : " + temp);
			if (refundService.insertInventory(refund) > 0) {

			} else {
				model.addAttribute("message", "재고 등록 실패!!");
				return "common/error";
			}

		}

//		return "";
		return "redirect:refundlist.do";

	}

	// 출고 수정
	@RequestMapping(value = "refupdate.do", method = RequestMethod.POST)
	public void updateRefund(Refund refund, HttpServletResponse response) throws IOException {
		String returnStr = null;

		if (refundService.updateRefund(refund) > 0) {
			logger.info("refund 수정 성공 : " + refund);
		} else {
			returnStr = "fail";
		}

		if (refundService.deleteInventory(refund.getRefundId()) > 0) {
			logger.info("refund 재고 삭제 성공 : " + refund);
		} else {
			returnStr = "fail";
		}

		if (refundService.insertInventory(refund) > 0) {
			returnStr = "success";
			logger.info("재고 등록 성공! " + refund);
		}
		logger.info("전부 수정 성공!!");
		response.setContentType("text/html; charset-utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

	}

}
