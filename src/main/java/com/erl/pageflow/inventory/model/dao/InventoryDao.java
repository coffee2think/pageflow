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
	
	public String selectInventoryClientName(int storageId) {
		return sqlSessionTemplate.selectOne("inventoryMapper.selectInventoryClientName", storageId);
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

}
