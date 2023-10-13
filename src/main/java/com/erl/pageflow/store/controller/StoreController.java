package com.erl.pageflow.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erl.pageflow.store.model.service.StoreService;

@Controller
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	// 뷰-----------------------------------------------------------------------------------------------
	// 입고현황
	@RequestMapping("storelist.do")
	public String moveStoreList() {
		return "inventory/store_list";
	}

	// 입고등록
	@RequestMapping("storeinput.do")
	public String moveStoreInput() {
		return "inventory/store_input";
	}

	// 출고현황
	@RequestMapping("releaselist.do")
	public String moveReleaseList() {
		return "inventory/release_list";
	}

	// 출고등록
	@RequestMapping("releaseinput.do")
	public String moveReleaseInput() {
		return "inventory/release_input";
	}

	// 값-----------------------------------------------------------------------------------------------
}
