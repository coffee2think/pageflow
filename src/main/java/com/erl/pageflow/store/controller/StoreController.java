package com.erl.pageflow.store.controller;

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
import com.erl.pageflow.store.model.service.StoreService;
import com.erl.pageflow.store.model.vo.Store;

@Controller
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	// 뷰-----------------------------------------------------------------------------------------------

	// 입고등록
	@RequestMapping("storeinput.do")
	public String moveStoreInput() {
		return "inventory/store_input";
	}

	// 출고등록
	@RequestMapping("releaseinput.do")
	public String moveReleaseInput() {
		return "inventory/release_input";
	}

	// 값-----------------------------------------------------------------------------------------------

	// 출고현황
	@RequestMapping("releaselist.do")
	public String moveReleaseList(Model model) {
		int currentPage = 1;
		int limit = 10;

		int listCount = storeService.selectGetListCount();
		Paging paging = new Paging(listCount, currentPage, limit, "releaselist.do");
		paging.calculator();

		ArrayList<Store> list = storeService.selectReleaseList(paging);

		for (Store sto : list) {
			String bname = storeService.selectStoreBookName(sto.getBookId());
			String cname = storeService.selectStoreClientName(sto.getStorageId());
			int bprice = storeService.selectStoreBookPrice(sto.getBookId());

			logger.info("sto : " + sto);
			sto.setBookName(bname);
			sto.setClientName(cname);
			sto.setBookPrice(bprice);
		}

		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/release_list";
		} else {
			model.addAttribute("message", "출고현황 조회 실패");
			return "common/error";
		}

	}

	// 입고현황
	@RequestMapping("storelist.do")
	public String moveStoreList(Model model) {
		int currentPage = 1;
		int limit = 10;

		int listCount = storeService.selectGetListCount();
		Paging paging = new Paging(listCount, currentPage, limit, "releaselist.do");
		paging.calculator();

		ArrayList<Store> list = storeService.selectStoreList(paging);

		for (Store sto : list) {
			String bname = storeService.selectStoreBookName(sto.getBookId());
			String cname = storeService.selectStoreClientName(sto.getStorageId());
			int bprice = storeService.selectStoreBookPrice(sto.getBookId());

			logger.info("sto : " + sto);
			sto.setBookName(bname);
			sto.setClientName(cname);
			sto.setBookPrice(bprice);
		}

		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/store_list";
		} else {
			model.addAttribute("message", "입고조회 실패");
			return "common/error";
		}
	}

	// 입고 삭제
//	@RequestMapping("storedelete.do")
//	public String deleteStore(@RequestParam("storelist") ArrayList<String> storelist,
//			HttpServletRequest request, Model model){
//		logger.info("storelist : " + storelist);
//		if (storeService.deleteInventory(storelist) > 0) {
//
//			if (storeService.deleteStore(storelist) > 0) {
//				model.addAttribute("storeId", storelist);
//				return "redirect:storelist.do";
//			} else {
//				model.addAttribute("message", storelist + "번 입고 삭제 실패");
//				return "common/error";
//			}
//
//		} else {
//			model.addAttribute("message", storelist + "번 재고 삭제 실패");
//			return "common/error";
//		}
//	}

	// 입고 날짜로 조회
	@RequestMapping("storedate.do")
	public String selectStoreByDate(Search search, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {
		int currentPage = 1;
		int limit = 10;

		int listCount = storeService.selectStoreCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "storedate.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Store> list = storeService.selectStoreByDate(search);

		if (list != null && list.size() > 0) {
			for (Store sto : list) {
				String bname = storeService.selectStoreBookName(sto.getBookId());
				String cname = storeService.selectStoreClientName(sto.getStorageId());
				int bprice = storeService.selectStoreBookPrice(sto.getBookId());

				sto.setBookName(bname);
				sto.setClientName(cname);
				sto.setBookPrice(bprice);
			}
			logger.info("search : " + search);
			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limitStr", limit);

			return "inventory/store_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}

	}

}
