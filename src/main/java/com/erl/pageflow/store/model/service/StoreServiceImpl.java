package com.erl.pageflow.store.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.store.model.dao.StoreDao;
import com.erl.pageflow.store.model.vo.Store;

@Service("storeService")
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDao storeDao;

	@Override
	public int selectGetListCount() {
		return storeDao.selectGetListCount();
	}
	
	@Override
	public ArrayList<Store> selectReleaseList(Paging paging){
		return storeDao.selectReleaseList(paging);
	}
	
	@Override
	public String selectStoreBookName(int bookId) {
		return storeDao.selectStoreBookName(bookId);
	}
	
	@Override
	public String selectStoreClientName(int storageId) {
		return storeDao.selectStoreClientName(storageId);
	}
	
	@Override
	public int selectStoreBookPrice(int bookId) {
		return storeDao.selectStoreBookPrice(bookId);
	}
	
	@Override
	public ArrayList<Store> selectStoreList(Paging paging){
		return storeDao.selectStoreList(paging);
	}

	@Override
	public int deleteStore(ArrayList<String> storelist) {
		return storeDao.deleteStore(storelist);
	}

	@Override
	public int deleteInventory(ArrayList<String> storelist) {
		return storeDao.deleteInventory(storelist);
	}

	@Override
	public int selectStoreCountByDate(Search search) {
		return storeDao.selectStoreCountByDate(search);
	}

	@Override
	public ArrayList<Store> selectStoreByDate(Search search) {
		return storeDao.selectStoreByDate(search);
	}
	
}
