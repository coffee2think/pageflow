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

}
