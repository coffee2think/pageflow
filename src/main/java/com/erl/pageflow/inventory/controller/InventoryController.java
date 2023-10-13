package com.erl.pageflow.inventory.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String moveWritePage(Model model) {

		int currentPage = 1;
		int limit = 10;

		// 총 페이지 수 계산을 위한 공지글 총갯수 조회
		int listCount = inventoryService.selectGetListCount();
		// 페이지 관련 항목 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "inventorylist.do");
		paging.calculator();

		ArrayList<Inventory> list = inventoryService.selectInventoryList(paging);

		for (Inventory inv : list) {
			String bname = inventoryService.selectInventoryBookName(inv.getBookId());
			String cname = inventoryService.selectInventoryClientName(inv.getStorageId());
			inv.setBookName(bname);
			inv.setStorageName(cname);
		}

		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			logger.info("list" + list);

			return "inventory/inven_list";
		} else {
			model.addAttribute("message", "재고 조회 실패!!");
			return "common/error.jsp";
		}

	}

	// 첫번째 필터 검색용 메소드
	@RequestMapping(value = "invenfirstfilter.do", method = RequestMethod.POST)
	public ModelAndView inventorfirstSearch(Inventory inventory,
			@RequestParam(name = "storeNum", required = false) String storeNum,
			@RequestParam(name = "releaseNum", required = false) String releaseNum,
			@RequestParam("keyword") String keyword, @RequestParam(name = "limit", required = false) String slimit,
			@RequestParam(name = "paging", required = false) String page, ModelAndView mv) {

		int currentPage = 1;

		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		int limit = 10;

		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		int listCount = inventoryService.selectInventorfirstSearchCount(keyword);

		Paging paging = new Paging(listCount, currentPage, limit, "invenfirstfilter.do");
		paging.calculator();

		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setKeyword(keyword);

		ArrayList<Inventory> list = inventoryService.selectInventorfirst(search);

		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currentPage", list);
			mv.addObject("limit", list);
			mv.addObject("action", list);
			mv.addObject("keyword", list);
			mv.addObject("storeNum", storeNum);
			mv.addObject("releaseNum", releaseNum);

			mv.setViewName("inventory/inven_list");
		} else {
			mv.addObject("message", keyword + " : 첫번쩨 검색 실패!");

			mv.setViewName("common/error");
		}
		return mv;
	}
}
