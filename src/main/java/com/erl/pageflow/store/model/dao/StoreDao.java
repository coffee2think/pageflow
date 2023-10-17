package com.erl.pageflow.store.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.store.model.vo.Store;

@Repository("storeDao")
public class StoreDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int selectGetListCount() {
		return sqlSessionTemplate.selectOne("storeMapper.selectGetListCount");
	}

	public ArrayList<Store> selectReleaseList(Paging paging) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectReleaseList", paging);
		return (ArrayList<Store>) list;
	}

	public String selectStoreBookName(int bookId) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreBookName", bookId);
	}

	public String selectStoreClientName(int storageId) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreClientName", storageId);
	}

	public int selectStoreBookPrice(int bookId) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreBookPrice", bookId);
	}

	public ArrayList<Store> selectStoreList(Paging paging) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectStoreList", paging);
		return (ArrayList<Store>) list;
	}

	public int selectStoreCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreCountByDate", search);
	}

	public ArrayList<Store> selectStoreByDate(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectStoreByDate", search);
		return (ArrayList<Store>) list;
	}

	public int selectReleaseCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectReleaseCountByDate", search);
	}

	public ArrayList<Store> selectReleaseByDate(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectReleaseByDate", search);
		return (ArrayList<Store>) list;
	}

	
	public int deleteStore(int storeId) {
		return sqlSessionTemplate.delete("storeMapper.deleteStore", storeId);
	}
	
	public int deleteRelease(int storeId) {
		return sqlSessionTemplate.delete("storeMapper.deleteRelease", storeId);
	}
	
	public int deleteInventory(int storeId) {
		return sqlSessionTemplate.delete("storeMapper.deleteInventory", storeId);
	}

}
