package com.erl.pageflow.store.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.store.model.vo.Store;

public interface StoreService {

	public int selectGetListCount();

	public int selectGetReleaseListCount();

	public ArrayList<Store> selectReleaseList(Paging paging);

	public String selectStoreBookName(int bookId);

	public String selectStoreClientName(int storageId);

	public int selectStoreBookPrice(int bookId);

	public ArrayList<Store> selectStoreList(Paging paging);

	public int selectReleaseCountByDate(Search search);

	public ArrayList<Store> selectReleaseByDate(Search search);

	public int deleteStore(int storeId);

	public int deleteRelease(int storeId);

	public int deleteInventory(int storeId);

	public int selectStoreCountByDate(Search search);

	public ArrayList<Store> selectStoreByDate(Search search);

	public int selectStoreCountByBookId(Search search);

	public int selectStoreCountByBookName(Search search);

	public int selectStoreCountByEmpName(Search search);

	public int selectStoreCountByClientName(Search search);

	public ArrayList<Store> selectStoreByBookId(Search search);

	public ArrayList<Store> selectStoreByBookName(Search search);

	public ArrayList<Store> selectStoreByEmpName(Search search);

	public ArrayList<Store> selectStoreByClientName(Search search);

	public int selectReleaseCountByBookId(Search search);

	public int selectReleaseCountByBookName(Search search);

	public int selectReleaseCountByEmpName(Search search);

	public int selectReleaseCountByClientName(Search search);

	public ArrayList<Store> selectReleaseByBookId(Search search);

	public ArrayList<Store> selectReleaseByBookName(Search search);

	public ArrayList<Store> selectReleaseByEmpName(Search search);

	public ArrayList<Store> selectReleaseByClientName(Search search);

	public int insertStore(Store store);

	public int insertInventory(Store store);

	public int insertStoreInventory(Store store);

	public int selectPreInvenId();

	public int selectCurrInven();

	public int selectMaxStoreId();

	public int insertRelease(Store store);

	public int updateStore(Store store);

}
