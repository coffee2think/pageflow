package com.erl.pageflow.inventory.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;

public interface InventoryService {

	public int selectGetListCount();

	public ArrayList<Inventory> selectInventoryList(Paging paging);

	public String selectInventoryBookName(int bookId);

	public String selectInventoryClientName(int storageId);

	public int selectGetDateListCount(Search search);

	public int selectInventoryCountByDate(Search search);

	public ArrayList<Inventory> selectInventoryByDate(Search search);

	public ArrayList<Inventory> selectSearchKeyword(String keyword);

	public int selectInventoryCountBybookId(Search search);

	public int selectInventoryCountBybookName(Search search);

	public int selectInventoryCountBystorageName(Search search);

	public int selectInventoryCountBystore(Search search);

	public int selectInventoryCountByrelease(Search search);

	public int selectInventoryCountByrefund(Search search);

	public ArrayList<Inventory> selectInventoryBybookId(Search search);

	public ArrayList<Inventory> selectInventoryBybookName(Search search);

	public ArrayList<Inventory> selectInventoryBystorageName(Search search);

	public ArrayList<Inventory> selectInventoryBystore(Search search);

	public ArrayList<Inventory> selectInventoryByrelease(Search search);

	public ArrayList<Inventory> selectInventoryByrefund(Search search);
}
