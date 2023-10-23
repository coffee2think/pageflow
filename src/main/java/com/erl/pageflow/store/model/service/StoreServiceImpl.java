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
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;

	@Override
	public int selectGetListCount() {
		return storeDao.selectGetListCount();
	}

	@Override
	public int selectGetReleaseListCount() {
		return storeDao.selectGetReleaseListCount();
	}

	@Override
	public ArrayList<Store> selectReleaseList(Paging paging) {
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
	public ArrayList<Store> selectStoreList(Paging paging) {
		return storeDao.selectStoreList(paging);
	}

	@Override
	public int selectStoreCountByDate(Search search) {
		return storeDao.selectStoreCountByDate(search);
	}

	@Override
	public ArrayList<Store> selectStoreByDate(Search search) {
		return storeDao.selectStoreByDate(search);
	}

	@Override
	public int selectReleaseCountByDate(Search search) {
		return storeDao.selectReleaseCountByDate(search);
	}

	@Override
	public ArrayList<Store> selectReleaseByDate(Search search) {
		return storeDao.selectReleaseByDate(search);
	}

	@Override
	public int deleteStore(int storeId) {
		return storeDao.deleteStore(storeId);
	}

	@Override
	public int deleteInventory(int storeId) {
		return storeDao.deleteInventory(storeId);
	}

	@Override
	public int deleteRelease(int storeId) {
		return storeDao.deleteRelease(storeId);
	}

	@Override
	public int selectStoreCountByBookId(Search search) {
		return storeDao.selectStoreCountByBookId(search);
	}

	@Override
	public int selectStoreCountByBookName(Search search) {
		return storeDao.selectStoreCountByBookName(search);
	}

	@Override
	public int selectStoreCountByEmpName(Search search) {
		return storeDao.selectStoreCountByEmpName(search);
	}

	@Override
	public int selectStoreCountByClientName(Search search) {
		return storeDao.selectStoreCountByClientName(search);
	}

	@Override
	public ArrayList<Store> selectStoreByBookId(Search search) {
		return storeDao.selectStoreByBookId(search);
	}

	@Override
	public ArrayList<Store> selectStoreByBookName(Search search) {
		return storeDao.selectStoreByBookName(search);
	}

	@Override
	public ArrayList<Store> selectStoreByEmpName(Search search) {
		return storeDao.selectStoreByEmpName(search);
	}

	@Override
	public ArrayList<Store> selectStoreByClientName(Search search) {
		return storeDao.selectStoreByClientName(search);
	}

	@Override
	public int selectReleaseCountByBookId(Search search) {
		return storeDao.selectReleaseCountByBookId(search);
	}

	@Override
	public int selectReleaseCountByBookName(Search search) {
		return storeDao.selectReleaseCountByBookName(search);
	}

	@Override
	public int selectReleaseCountByEmpName(Search search) {
		return storeDao.selectReleaseCountByEmpName(search);
	}

	@Override
	public int selectReleaseCountByClientName(Search search) {
		return storeDao.selectReleaseCountByClientName(search);
	}

	@Override
	public ArrayList<Store> selectReleaseByBookId(Search search) {
		return storeDao.selectReleaseByBookId(search);
	}

	@Override
	public ArrayList<Store> selectReleaseByBookName(Search search) {
		return storeDao.selectReleaseByBookName(search);
	}

	@Override
	public ArrayList<Store> selectReleaseByEmpName(Search search) {
		return storeDao.selectReleaseByEmpName(search);
	}

	@Override
	public ArrayList<Store> selectReleaseByClientName(Search search) {
		return storeDao.selectReleaseByClientName(search);
	}

	@Override
	public int insertStore(Store store) {
		return storeDao.insertStore(store);
	}

	@Override
	public int insertInventory(Store store) {
		return storeDao.insertInventory(store);
	}

	@Override
	public int insertStoreInventory(Store store) {
		return storeDao.insertStoreInventory(store);
	}

	@Override
	public int selectPreInvenId() {
		return storeDao.selectPreInvenId();
	}

	@Override
	public int selectCurrInven() {
		return storeDao.selectCurrInven();
	}

	@Override
	public int selectMaxStoreId() {
		return storeDao.selectMaxStoreId();
	}

	@Override
	public int insertRelease(Store store) {
		return storeDao.insertRelease(store);
	}

	@Override
	public int updateStore(Store store) {
		return storeDao.updateStore(store);
	}

	@Override
	public int updateRelease(Store store) {
		return storeDao.updateRelease(store);
	}

}
