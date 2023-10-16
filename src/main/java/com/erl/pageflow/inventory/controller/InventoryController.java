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
	
//	@RequestMapping(value="invendate.do")
//	public ModelAndView selectInventoryByDate(Search search, @RequestParam("action") String action) {
//		int currentPage = 1;
//		int limit = 10;
//		
//		int listCount = inventoryService.selectGetDateListCount(search);
//		
//		
//	}


}
