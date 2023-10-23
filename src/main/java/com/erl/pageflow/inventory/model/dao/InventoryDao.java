package com.erl.pageflow.inventory.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;

@Repository("inventoryDao")
public class InventoryDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int selectGetListCount() {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectGetListCount");
	}

	public String selectInventoryBookName(int bookId) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryBookName", bookId);
	}
	
	public String selectInventoryClientName(int clientId) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryClientName", clientId);
	}

	// 재고 리스트
	public ArrayList<Inventory> selectInventoryList(Paging paging) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryList", paging);
		return (ArrayList<Inventory>) list;
	}

	public int selectGetDateListCount(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectGetDateListCount");
	}

	public int selectInventoryCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountByDate", search);
	}

	public ArrayList<Inventory> selectInventoryByDate(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryByDate", search);
		return (ArrayList<Inventory>) list;
	}

	public ArrayList<Inventory> selectSearchKeyword(String keyword) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectSearchKeyword", keyword);
		return (ArrayList<Inventory>) list;
	}

	public int selectInventoryCountBybookId(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountBybookId", search);
	}

	public int selectInventoryCountBybookName(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountBybookName", search);
	}

	public int selectInventoryCountBystorageName(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountBystorageName", search);
	}

	public int selectInventoryCountBystore(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountBystore", search);
	}

	public int selectInventoryCountByrelease(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountByrelease", search);
	}

	public int selectInventoryCountByrefund(Search search) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryCountByrefund", search);
	}

	public ArrayList<Inventory> selectInventoryBybookId(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryBybookId", search);
		return (ArrayList<Inventory>) list;
	}

	public ArrayList<Inventory> selectInventoryBybookName(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryBybookName", search);
		return (ArrayList<Inventory>) list;
	}

	public ArrayList<Inventory> selectInventoryBystorageName(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryBystorageName", search);
		return (ArrayList<Inventory>) list;
	}

	public ArrayList<Inventory> selectInventoryBystore(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryBystore", search);
		return (ArrayList<Inventory>) list;
	}

	public ArrayList<Inventory> selectInventoryByrelease(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryByrelease", search);
		return (ArrayList<Inventory>) list;
	}
	

	public ArrayList<Inventory> selectInventoryByrefund(Search search) {
		List<Inventory> list = sqlSessionTemplate.selectList("inventoryMapper.selectInventoryByrefund", search);
		return (ArrayList<Inventory>) list;
	}

}
