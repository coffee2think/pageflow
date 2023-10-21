package com.erl.pageflow.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

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
	public String moveReleaseList(Model model,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr) {
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = storeService.selectGetReleaseListCount();
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
			model.addAttribute("relList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			logger.info("list : " + list);
			return "inventory/release_list";

		} else {
			model.addAttribute("message", "출고현황 조회 실패");
			return "common/error";
		}

	}

	// 입고현황
	@RequestMapping("storelist.do")
	public String moveStoreList(Model model, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr) {
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = storeService.selectGetListCount();
		Paging paging = new Paging(listCount, currentPage, limit, "storelist.do");
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
			model.addAttribute("storeList", list);
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
	@RequestMapping("stolistdate.do")
	public String selectStoreByDate(Search search, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = storeService.selectStoreCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "stolistdate.do");

		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Store> list = storeService.selectStoreByDate(search);
		logger.info("search : " + search);

		if (list != null && list.size() > 0) {

			model.addAttribute("storeList", list);
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
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = storeService.selectStoreCountByDate(search);

		Paging paging = new Paging(listCount, currentPage, limit, "releasedate.do");

		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Store> list = storeService.selectStoreByDate(search);
		logger.info("search : " + search);

		if (list != null && list.size() > 0) {

			model.addAttribute("relList", list);
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
	/* @ResponseBody */
	public void deleteStore(HttpServletResponse response, @RequestBody String param)
			throws ParseException, IOException {
		JSONParser jparser = new JSONParser();
		JSONArray jarr = (JSONArray) jparser.parse(param);
		String[] jst = null;
		System.out.println(jarr);
		int count = 0;

		JSONObject job = (JSONObject) jarr.get(0);
		JSONArray scbkeyArray = (JSONArray) job.get("scbkey");
		System.out.println("job : " + job);
		System.out.println("scbkeyArray : " + scbkeyArray);
		for (int j = 0; j < scbkeyArray.size(); j++) {
			String scbkey = (String) scbkeyArray.get(j);
			int scbkeyInt = Integer.parseInt(scbkey);
			System.out.println("scbkeyInt : " + scbkeyInt);
			if (storeService.deleteInventory(scbkeyInt) >= 0) {
				if (storeService.deleteStore(scbkeyInt) >= 0) {
					System.out.println("입고 삭제 : " + scbkeyInt);
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
		// 출력값은 버퍼에서 처리하니까 밀어내는 작업!
		out.flush();

		out.close();
		/*
		 * for (int i = 0; i < jarr.size(); i++) { JSONObject job = (JSONObject)
		 * jarr.get(i); System.out.println(job); JSONArray scbkeyArray = (JSONArray)
		 * job.get("scbkey"); System.out.println(scbkeyArray);
		 * 
		 * jst = new String[scbkeyArray.size()];
		 * 
		 * for (int j = 0; j < scbkeyArray.size(); j++) { jst[j] = (String)
		 * scbkeyArray.get(j); System.out.println("jst" + j + " : " + jst[j]);
		 * 
		 * for (int j2 = 0; j2 < jst.length; j2++) { String scbkey = (String)
		 * scbkeyArray.get(j2); int scbkeyInt = Integer.parseInt(scbkey); if
		 * (storeService.deleteInventory(scbkeyInt) >= 0) { if
		 * (storeService.deleteStore(scbkeyInt) >= 0) { System.out.println("입고 삭제 : " +
		 * scbkeyInt); } }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */
	}

	// 출고 삭제
	@RequestMapping(value = "releasedelete.do", method = RequestMethod.POST)
	/* @ResponseBody */
	public void deleteRelease(HttpServletResponse response, @RequestBody String param)
			throws ParseException, IOException {
		JSONParser jparser = new JSONParser();
		JSONArray jarr = (JSONArray) jparser.parse(param);
		String[] jst = null;
		System.out.println(jarr);
		int count = 0;

		JSONObject job = (JSONObject) jarr.get(0);
		JSONArray scbkeyArray = (JSONArray) job.get("scbkey");
		System.out.println("job : " + job);
		System.out.println("scbkeyArray : " + scbkeyArray);
		for (int j = 0; j < scbkeyArray.size(); j++) {
			String scbkey = (String) scbkeyArray.get(j);
			int scbkeyInt = Integer.parseInt(scbkey);
			System.out.println("scbkeyInt : " + scbkeyInt);
			if (storeService.deleteInventory(scbkeyInt) >= 0) {
				if (storeService.deleteRelease(scbkeyInt) >= 0) {
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
		// 출력값은 버퍼에서 처리하니까 밀어내는 작업!
		out.flush();

		out.close();
	}

	// 입고 키워드로 검색
	@RequestMapping(value = "stoselectkeyword.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectStoreSearchKeyword(Search search, @RequestParam(name = "searchType") String searchType,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = 0;

		logger.info("searchType : " + searchType);

		switch (searchType) {
		case "bookId":
			listCount = storeService.selectStoreCountByBookId(search);
			break;
		case "bookName":
			listCount = storeService.selectStoreCountByBookName(search);
			break;
		case "empName":
			listCount = storeService.selectStoreCountByEmpName(search);
			break;
		case "clientName":
			listCount = storeService.selectStoreCountByClientName(search);
			break;
		}

		Paging paging = new Paging(listCount, currentPage, limit, "stoselectkeyword.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		logger.info("search : " + search);
		ArrayList<Store> list = null;

		switch (searchType) {
		case "bookId":
			list = storeService.selectStoreByBookId(search);
			break;
		case "bookName":
			list = storeService.selectStoreByBookName(search);
			break;
		case "empName":
			list = storeService.selectStoreByEmpName(search);
			break;
		case "clientName":
			list = storeService.selectStoreByClientName(search);
			break;
		}
		if (list != null && list.size() > 0) {

			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			model.addAttribute("storeList", list);

			return "inventory/store_list";
		} else {
			model.addAttribute("message", "키워드 검색 실패");
			return "common/error";
		}

	}

	// 출고 키워드로 검색
	@RequestMapping(value = "relselectkeyword.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectReleaseSearchKeyword(Search search, @RequestParam(name = "searchType") String searchType,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String limitStr, Model model) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;

		int listCount = 0;

		logger.info("searchType : " + searchType);

		switch (searchType) {
		case "bookId":
			listCount = storeService.selectReleaseCountByBookId(search);
			break;
		case "bookName":
			listCount = storeService.selectReleaseCountByBookName(search);
			break;
		case "empName":
			listCount = storeService.selectReleaseCountByEmpName(search);
			break;
		case "clientName":
			listCount = storeService.selectReleaseCountByClientName(search);
			break;
		}

		Paging paging = new Paging(listCount, currentPage, limit, "relselectkeyword.do");
		paging.calculator();

		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		logger.info("search : " + search);
		ArrayList<Store> list = null;

		switch (searchType) {
		case "bookId":
			list = storeService.selectReleaseByBookId(search);
			break;
		case "bookName":
			list = storeService.selectReleaseByBookName(search);
			break;
		case "empName":
			list = storeService.selectReleaseByEmpName(search);
			break;
		case "clientName":
			list = storeService.selectReleaseByClientName(search);
			break;
		}
		if (list != null && list.size() > 0) {

			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", search.getKeyword());
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			model.addAttribute("relList", list);

			return "inventory/release_list";
		} else {
			model.addAttribute("message", "키워드 검색 실패");
			return "common/error";
		}

	}

	// 입고등록
	@RequestMapping(value = "stoinsert.do", method = RequestMethod.POST)
	public String insertStore(HttpServletRequest request, Model model) {
		String[] bookIds = request.getParameterValues("bookId");
		String[] storageIds = request.getParameterValues("storageId");
		String[] empIds = request.getParameterValues("empId");
		String[] empNames = request.getParameterValues("empName");
		String[] storeNums = request.getParameterValues("storeNum");
		String[] storePrice = request.getParameterValues("storePrice");
		String[] storeDate = request.getParameterValues("storeDate");
		// ----------------------------------------

		int storeId = storeService.selectMaxStoreId() + 1;

		ArrayList<Store> storeList = new ArrayList<>();

		for (int i = 0; i < bookIds.length; i++) {
			Store store = new Store();

			store.setStoreId(storeId);
			store.setBookId(Integer.parseInt(bookIds[i]));
			store.setStorageId(Integer.parseInt(storageIds[i]));
			store.setEmpId(Integer.parseInt(empIds[i]));
			store.setEmpName(empNames[i]);
			store.setStoreNum(Integer.parseInt(storeNums[i]));
			store.setStorePrice(Integer.parseInt(storePrice[i]));
			store.setStoreDate(Date.valueOf(storeDate[i]));

			storeList.add(store);

		}

		logger.info("storeList : " + storeList);
		for (Store store : storeList) {
			if (storeService.insertStore(store) > 0 && storeService.insertInventory(store) > 0) {

				int preinvenId = storeService.selectPreInvenId();
				store.setPrevInvenId(preinvenId);

				int currInven = storeService.selectCurrInven();
				store.setCurrInven(currInven);

				logger.info("currInven : " + currInven);

				return "redirect:storelist.do";
			}
		}
		model.addAttribute("message", "입고 등록 실패!");
		return "common/error";
	}

	// 출고 등록
	@RequestMapping(value = "releaseinput.do", method = RequestMethod.POST)
	public String insertRelease(HttpServletRequest request, Model model) {
		String[] bookIds = request.getParameterValues("bookId");
		String[] bookNames = request.getParameterValues("bookName");
		String[] empIds = request.getParameterValues("empId");
		String[] empNames = request.getParameterValues("empName");
		String[] clientNames = request.getParameterValues("clientName");
		String[] clientIds = request.getParameterValues("clientId");
		String[] storageIds = request.getParameterValues("storageId");
		String[] storeNums = request.getParameterValues("storeNum");
		String[] storeDates = request.getParameterValues("storeDate");
		String[] bookPrices = request.getParameterValues("bookPrice");
		String[] storePrices = request.getParameterValues("storePrice");

		int storeId = storeService.selectMaxStoreId() + 1;

		ArrayList<Store> releaseList = new ArrayList<>();

		for (int i = 0; i < bookIds.length; i++) {

			Store store = new Store();

			store.setStoreId(storeId);
			store.setBookId(Integer.parseInt(bookIds[i]));
			store.setBookName(bookNames[i]);
			store.setEmpId(Integer.parseInt(empIds[i]));
			store.setEmpName(empNames[i]);
			store.setClientName(clientNames[i]);
			store.setClientId(Integer.parseInt(clientIds[i]));
			store.setStoreNum(Integer.parseInt(storeNums[i]));
			store.setStoreDate(Date.valueOf(storeDates[i]));
			store.setBookPrice(Integer.parseInt(bookPrices[i]));
			store.setStorePrice(Integer.parseInt(storePrices[i]));
			store.setStorageId(Integer.parseInt(storageIds[i]));

			releaseList.add(store);
		}

		for (Store store : releaseList) {
			if (storeService.insertRelease(store) > 0 && storeService.insertInventory(store) > 0) {
				int preinvenId = storeService.selectPreInvenId();
				store.setPrevInvenId(preinvenId);

				int currInven = storeService.selectCurrInven();
				store.setCurrInven(currInven);

				return "redirect:releaselist.do";
			}
		}
		model.addAttribute("message", "출고등록 실패!");
		return "common/error";
	}

	// 입고 수정
	@RequestMapping(value = "stoupdate.do", method = RequestMethod.POST)
	public void updateStore(Store store, HttpServletResponse response) throws IOException {
		String returnStr = null;
		logger.info("storeNum : " + store.getStoreNum());
		logger.info("storePrice :" + store.getStorePrice());
		if (storeService.updateStore(store) > 0) {
			logger.info("updateStore : " + store);
			
			if (storeService.insertInventory(store) > 0) {
				
				logger.info("insertInventory : " + store);
				returnStr = "success";
			}
		} else {
			logger.info("store : " + store);
			returnStr = "fail";

		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

	}
}
