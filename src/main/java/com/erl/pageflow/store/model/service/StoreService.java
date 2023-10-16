package com.erl.pageflow.store.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.store.model.vo.Store;

public interface StoreService {
	
	public int selectGetListCount();
	
	public ArrayList<Store> selectReleaseList(Paging paging);
	
	public String selectStoreBookName(int bookId);
	
	public String selectStoreClientName(int storageId);
	
	public int selectStoreBookPrice(int bookId);
	
	public ArrayList<Store> selectStoreList(Paging paging);
	
	public int deleteStore(ArrayList<String> storelist);

	public int deleteInventory(ArrayList<String> storelist);
	
	public int selectStoreCountByDate(Search search);
	
	public ArrayList<Store> selectStoreByDate(Search search);
}
