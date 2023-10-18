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
	public ArrayList<Inventory> selectInventoryList(Paging paging) {
		return inventoryDao.selectInventoryList(paging);
	}

	@Override
	public int selectGetDateListCount(Search sarch) {
		return inventoryDao.selectGetDateListCount(sarch);
	}

	@Override
	public int selectInventoryCountByDate(Search search) {
		return inventoryDao.selectInventoryCountByDate(search);
	}

	@Override
	public ArrayList<Inventory> selectInventoryByDate(Search search) {
		return inventoryDao.selectInventoryByDate(search);
	}

	@Override
	public ArrayList<Inventory> selectSearchKeyword(String keyword) {
		return inventoryDao.selectSearchKeyword(keyword);
	}

	@Override
	public int selectInventoryCountBybookId(Search search) {
		return inventoryDao.selectInventoryCountBybookId(search);
	}

	@Override
	public int selectInventoryCountBybookName(Search search) {
		return inventoryDao.selectInventoryCountBybookId(search);
	}

	@Override
	public int selectInventoryCountBystorageName(Search search) {
		return inventoryDao.selectInventoryCountBybookId(search);
	}

	@Override
	public int selectInventoryCountBystore(Search search) {
		return inventoryDao.selectInventoryCountBybookId(search);
	}

	@Override
	public int selectInventoryCountByrelease(Search search) {
		return inventoryDao.selectInventoryCountBybookId(search);
	}

	@Override
	public int selectInventoryCountByrefund(Search search) {
		return inventoryDao.selectInventoryCountBybookId(search);
	}

	@Override
	public ArrayList<Inventory> selectInventoryBybookId(Search search) {
		return inventoryDao.selectInventoryBybookId(search);
		
	}

	@Override
	public ArrayList<Inventory> selectInventoryBybookName(Search search) {
		return inventoryDao.selectInventoryBybookName(search);
		
	}

	@Override
	public ArrayList<Inventory> selectInventoryBystorageName(Search search) {
		return inventoryDao.selectInventoryBystorageName(search);
	}

	@Override
	public ArrayList<Inventory> selectInventoryBystore(Search search) {
		return inventoryDao.selectInventoryBystore(search);
	}

	@Override
	public ArrayList<Inventory> selectInventoryByrelease(Search search) {
		return inventoryDao.selectInventoryByrelease(search);
	}

	@Override
	public ArrayList<Inventory> selectInventoryByrefund(Search search) {
		return inventoryDao.selectInventoryByrefund(search);
	}

}
