package com.erl.pageflow.store.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
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
	
}
