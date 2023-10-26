package com.erl.pageflow.inventory.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

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

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.service.InventoryService;
import com.erl.pageflow.inventory.model.vo.Inventory;

@Controller
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;

	// 뷰------------------------------------------------------------------------------------------

	// 값------------------------------------------------------------------------------------------

	// 재고 현황 들어갔을 때 보여지는 뷰
	@RequestMapping("inventorylist.do")
	public String moveInventoryList(Model model, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = inventoryService.selectGetListCount();
		Paging paging = new Paging(listCount, currentPage, limit, "inventorylist.do");
		paging.calculator();

		ArrayList<Inventory> list = inventoryService.selectInventoryList(paging);

		for (Inventory inv : list) {
			String bname = inventoryService.selectInventoryBookName(inv.getBookId());
			String cname = inventoryService.selectInventoryClientName(inv.getClientId());
			inv.setBookName(bname);
			inv.setClientName(cname);
		}

		if (list != null && list.size() > 0) {
			model.addAttribute("invenList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/inven_list";
		} else {
			model.addAttribute("message", "재고 조회 실패!!");
			return "common/error";
		}

	}

	// 재고 날짜 조회
	@RequestMapping("invlistdate.do")
	public String selectReleaseByDate(Search search, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = inventoryService.selectInventoryCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "invlistdate.do");

		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Inventory> list = inventoryService.selectInventoryByDate(search);
		logger.info("search : " + search);

		if (list != null && list.size() > 0) {

			model.addAttribute("invenList", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

		} 
		return "inventory/inven_list";
	}

	// 재고 키워드로 검색
	
	
	@RequestMapping(value = "invselectkeyword.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectInvenSearchKeyword(Search search, @RequestParam(name = "searchType") String searchType,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = 0;

		logger.info("searchType : " + searchType);

		switch (searchType) {
		case "bookId":
			listCount = inventoryService.selectInventoryCountBybookId(search);
			break;
		case "bookName":
			listCount = inventoryService.selectInventoryCountBybookName(search);
			break;
		case "clientName":
			listCount = inventoryService.selectInventoryCountBystorageName(search);
			break;
		case "store":
			listCount = inventoryService.selectInventoryCountBystore(search);
			break;
		case "release":
			listCount = inventoryService.selectInventoryCountByrelease(search);
			break;
		case "refund":
			listCount = inventoryService.selectInventoryCountByrefund(search);
			break;
		}

		Paging paging = new Paging(listCount, currentPage, limit, "selectkeyword.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		logger.info("search : " + search);
		ArrayList<Inventory> list = null;

		switch (searchType) {
		case "bookId":
			list = inventoryService.selectInventoryBybookId(search);
			break;
		case "bookName":
			list = inventoryService.selectInventoryBybookName(search);
			break;
		case "clientName":
			list = inventoryService.selectInventoryBystorageName(search);
			break;
		case "store":
			list = inventoryService.selectInventoryBystore(search);
			break;
		case "release":
			list = inventoryService.selectInventoryByrelease(search);
			break;
		case "refund":
			list = inventoryService.selectInventoryByrefund(search);
			break;
		}
		if (list != null && list.size() > 0) {

			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			model.addAttribute("invenList", list);

		} 
		return "inventory/inven_list";
	}
}