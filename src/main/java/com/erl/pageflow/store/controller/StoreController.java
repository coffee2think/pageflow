package com.erl.pageflow.store.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.erl.pageflow.store.model.service.StoreService;
import com.erl.pageflow.store.model.vo.Store;

@Controller
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreService inventoryService;

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

			// logger.info("sto : " + sto);
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

			// logger.info("sto : " + sto);
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
		// logger.info("search : " + search);
		if (list != null && list.size() > 0) {
			for (Store sto : list) {
				String bname = storeService.selectStoreBookName(sto.getBookId());
				String cname = storeService.selectStoreClientName(sto.getStorageId());
				int bprice = storeService.selectStoreBookPrice(sto.getBookId());

				sto.setBookName(bname);
				sto.setClientName(cname);
				sto.setBookPrice(bprice);
			}

			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/store_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}

	}

	// 출고 날짜로 조회
	@RequestMapping("releasedate.do")
	public String selectReleaseByDate(Search search, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {
		int currentPage = 1;
		int limit = 10;

		int listCount = storeService.selectReleaseCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "releasedate.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Store> list = storeService.selectReleaseByDate(search);
		// logger.info("search : " + search);
		if (list != null && list.size() > 0) {
			for (Store sto : list) {
				String bname = storeService.selectStoreBookName(sto.getBookId());
				String cname = storeService.selectStoreClientName(sto.getStorageId());
				int bprice = storeService.selectStoreBookPrice(sto.getBookId());

				sto.setBookName(bname);
				sto.setClientName(cname);
				sto.setBookPrice(bprice);
			}

			model.addAttribute("list", list);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "inventory/release_list";
		} else {
			model.addAttribute("message", "날짜 검색 실패");
			return "common/error";
		}

	}

	// 입고 삭제
	@RequestMapping(value = "storedelete.do", method = RequestMethod.POST)
	@ResponseBody
	public void deleteStore(@RequestParam(name = "selectedStoreIds") String selectedStoreIds,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		int count = 0;
		String[] storeArray = selectedStoreIds.split(",");
		logger.info("selectedStoreIds" + selectedStoreIds);
		logger.info("storeArray length : " + storeArray.length);
		for (int i = 0; i < storeArray.length; i++) {

			int sId = Integer.parseInt(storeArray[i].toString());

			if (storeService.deleteInventory(sId) > 0) {

				logger.info("deleteInventory " + i);
				if (storeService.deleteStore(sId) > 0) {
					logger.info("deleteStore " + i);
					count++;
				} else {
					model.addAttribute("message", sId + "번 입고 삭제 실패");
					// return "common/error";
				}
			} else {
				model.addAttribute("message", sId + "번 재고 삭제 실패");
				// return "common/error";
			}
		}

		// return "redirect:storelist.do";
		if (count >= storeArray.length) {
			response.getWriter().append(String.valueOf(count)).flush();
		}

	}

	// 출고 삭제
	@RequestMapping(value = "releasedelete.do", method = RequestMethod.POST)
	@ResponseBody
	public void deleteRelease(@RequestParam(name = "selectedStoreIds") String selectedStoreIds,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		int count = 0;
		String[] storeArray = selectedStoreIds.split(",");
		// logger.info("selectedStoreIds" + selectedStoreIds);
		// logger.info("storeArray length : " + storeArray.length);
		for (int i = 0; i < storeArray.length; i++) {

			int sId = Integer.parseInt(storeArray[i].toString());

			if (storeService.deleteInventory(sId) > 0) {

				// logger.info("deleteInventory " + i);
				if (storeService.deleteRelease(sId) > 0) {
					// logger.info("deleteStore " + i);
					count++;
				} else {
					model.addAttribute("message", sId + "번 입고 삭제 실패");
					// return "common/error";
				}
			} else {
				model.addAttribute("message", sId + "번 재고 삭제 실패");
				// return "common/error";
			}
		}

		// return "redirect:storelist.do";
		if (count >= storeArray.length) {
			response.getWriter().append(String.valueOf(count)).flush();
		}

	}

}
