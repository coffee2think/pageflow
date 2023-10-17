package com.erl.pageflow.inventory.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.service.InventoryService;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.store.model.vo.Store;

@Controller
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;

	// 뷰------------------------------------------------------------------------------------------

	// 값------------------------------------------------------------------------------------------

	// 재고 현황 들어갔을 때 보여지는 뷰
	@RequestMapping("inventorylist.do")
	public String moveInventoryList(Model model) {

		int currentPage = 1;
		int limit = 10;

		int listCount = inventoryService.selectGetListCount();
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

//			logger.info("list" + list);

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
		int currentPage = 1;
		int limit = 10;

		int listCount = inventoryService.selectInventoryCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "invlistdate.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Inventory> list = inventoryService.selectInventoryByDate(search);
		logger.info("search : " + search);
		if (list != null && list.size() > 0) {
			for (Inventory inv : list) {
				String bname = inventoryService.selectInventoryBookName(inv.getBookId());
				String cname = inventoryService.selectInventoryClientName(inv.getStorageId());

				inv.setBookName(bname);
				inv.setStorageName(cname);
			}

			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/inven_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}

	}

}
