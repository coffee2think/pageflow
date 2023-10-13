package com.erl.pageflow.inventory.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.dao.InventoryDao;
import com.erl.pageflow.inventory.model.vo.Inventory;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public int selectGetListCount() {
		return inventoryDao.selectGetListCount();
	}
	
	@Override
	public String selectInventoryBookName(int bookId) {
		return inventoryDao.selectInventoryBookName(bookId);
	}
	
	@Override
	public String selectInventoryClientName(int storageId) {
		return inventoryDao.selectInventoryClientName(storageId);
	}
	
	@Override
	public ArrayList<Inventory> selectInventoryList(Paging paging){
		return inventoryDao.selectInventoryList(paging);
	}
	
	@Override
	public int selectInventorfirstSearchCount(String keyword) {
		return inventoryDao.selectInventorfirstSearchCount(keyword);
	}

	@Override
	public ArrayList<Inventory> selectInventorfirst(Search search) {
		return inventoryDao.selectInventorfirstSearch(search);
	}
}
