package com.erl.pageflow.store.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.store.model.vo.Store;

@Repository("storeDao")
public class StoreDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int selectGetListCount() {
		return sqlSessionTemplate.selectOne("storeMapper.selectGetListCount");
	}

	public int selectGetReleaseListCount() {
		return sqlSessionTemplate.selectOne("storeMapper.selectGetReleaseListCount");
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

	public int selectStoreCountByBookId(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreCountByBookId", search);
	}

	public int selectStoreCountByBookName(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreCountByBookName", search);
	}

	public int selectStoreCountByEmpName(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreCountByEmpName", search);
	}

	public int selectStoreCountByClientName(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectStoreCountByClientName", search);
	}

	public ArrayList<Store> selectStoreByBookId(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectStoreByBookId", search);
		return (ArrayList<Store>) list;
	}

	public ArrayList<Store> selectStoreByBookName(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectStoreByBookName", search);
		return (ArrayList<Store>) list;
	}

	public ArrayList<Store> selectStoreByEmpName(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectStoreByEmpName", search);
		return (ArrayList<Store>) list;
	}

	public ArrayList<Store> selectStoreByClientName(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectStoreByClientName", search);
		return (ArrayList<Store>) list;
	}

	public int selectReleaseCountByBookId(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectReleaseCountByBookId", search);
	}

	public int selectReleaseCountByBookName(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectReleaseCountByBookName", search);
	}

	public int selectReleaseCountByEmpName(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectReleaseCountByEmpName", search);
	}

	public int selectReleaseCountByClientName(Search search) {
		return sqlSessionTemplate.selectOne("storeMapper.selectReleaseCountByClientName", search);
	}

	public ArrayList<Store> selectReleaseByBookId(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectReleaseByBookId", search);
		return (ArrayList<Store>) list;
	}

	public ArrayList<Store> selectReleaseByBookName(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectReleaseByBookName", search);
		return (ArrayList<Store>) list;
	}

	public ArrayList<Store> selectReleaseByEmpName(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectReleaseByEmpName", search);
		return (ArrayList<Store>) list;
	}

	public ArrayList<Store> selectReleaseByClientName(Search search) {
		List<Store> list = sqlSessionTemplate.selectList("storeMapper.selectReleaseByClientName", search);
		return (ArrayList<Store>) list;
	}

	public int insertStore(Store store) {
		return sqlSessionTemplate.insert("storeMapper.insertStore", store);
	}

	public int insertInventory(Store store) {
		return sqlSessionTemplate.insert("storeMapper.insertInventory", store);
	}
	
	public int insertStoreInventory(Store store) {
		return sqlSessionTemplate.insert("storeMapper.insertStoreInventory", store);
	}


	public int selectPreInvenId() {
		return sqlSessionTemplate.selectOne("storeMapper.selectPreInvenId");
	}

	public int selectCurrInven() {
		return sqlSessionTemplate.selectOne("storeMapper.selectCurrInven");
	}

	public int selectMaxStoreId() {
		return sqlSessionTemplate.selectOne("storeMapper.selectMaxStoreId");
	}

	public int insertRelease(Store store) {
		return sqlSessionTemplate.insert("storeMapper.insertRelease", store);
	}

	public int updateStore(Store store) {
		return sqlSessionTemplate.update("storeMapper.updateStore", store);
	}

}
