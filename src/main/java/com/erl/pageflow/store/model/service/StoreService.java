package com.erl.pageflow.store.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.store.model.vo.Store;

public interface StoreService {
	
	public int selectGetListCount();
	
	public ArrayList<Store> selectReleaseList(Paging paging);
	
	public String selectStoreBookName(int bookId);
	
	public String selectStoreClientName(int storageId);
	
	public int selectStoreBookPrice(int bookId);
	
	public ArrayList<Store> selectStoreList(Paging paging);
}
