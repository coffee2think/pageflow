package com.erl.pageflow.store.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.store.model.vo.Store;

public interface StoreService {
	
	public int selectGetListCount();
	
	public ArrayList<Store> selectReleaseList(Paging paging);
	
	public String selectStoreBookName(int bookId);
	
	public String selectStoreClientName(int storageId);
	
	public int selectStoreBookPrice(int bookId);
	
	public ArrayList<Store> selectStoreList(Paging paging);
	
	public int selectStoreCountByDate(Search search);
	
	public ArrayList<Store> selectStoreByDate(Search search);
	
	public int selectReleaseCountByDate(Search search);
	
	public ArrayList<Store> selectReleaseByDate(Search search);
	
	public int deleteStore(int storeId);
	
	public int deleteRelease(int storeId);
	
	public int deleteInventory(int storeId);
}
