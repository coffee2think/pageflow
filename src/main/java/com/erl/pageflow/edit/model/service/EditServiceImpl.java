package com.erl.pageflow.edit.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.edit.model.dao.EditDao;
import com.erl.pageflow.edit.model.vo.Edit;

@Service("editService")
public class EditServiceImpl implements EditService {
	@Autowired
	private EditDao editDao;
	
	@Override
	public int selectEditListCount() {
		return editDao.selectEditListCount();
	}
	
	@Override
	public ArrayList<Edit> selectEditList(Paging paging) {
		return editDao.selectEditList(paging);
	}
	
	@Override
	public ArrayList<Edit> selectEditSearch(Paging paging) {
		return editDao.selectEditSearch(paging);
	}
	
	@Override
	public Edit selectEdit(int editId) {
		return editDao.selectEdit(editId);
	}

	@Override
	public int selectMaxEditId() {
		return editDao.selectMaxEditId();
	}
	
	@Override
	public ArrayList<Edit> selectEditBySDate(Search search) {
		return editDao.selectEditBySDate(search);
	}
	
	@Override
	public int selectEditCountBySDate(Search search) {
		return editDao.selectEditCountBySDate(search);
	}
	
	@Override
	public ArrayList<Edit> selectEditByEDate(Search search) {
		return editDao.selectEditByEDate(search);
	}
	
	@Override
	public int selectEditCountByEDate(Search search) {
		return editDao.selectEditCountByEDate(search);
	}

	@Override
	public int selectEditCountByDepartment(Search search) {
		return editDao.selectEditCountByDepartment(search);
	}

	@Override
	public ArrayList<Edit> selectEditByDepartment(Search search) {
		return editDao.selectEditByDepartment(search);
	}

	@Override
	public int selectEditCountByEmployee(Search search) {
		return editDao.selectEditCountByEmployee(search);
	}

	@Override
	public ArrayList<Edit> selectEditByEmployee(Search search) {
		return editDao.selectEditByEmployee(search);
	}

	@Override
	public int selectEditCountByBook(Search search) {
		return editDao.selectEditCountByBook(search);
	}

	@Override
	public ArrayList<Edit> selectEditByBook(Search search) {
		return editDao.selectEditByBook(search);
	}
	
	@Override
	public int insertEdit(Edit edit) {
		return editDao.insertEdit(edit);
	}
	
	@Override
	public int updateEdit(Edit edit) {
		return editDao.updateEdit(edit);
	}
	
	@Override
	public int deleteEdit(Edit edit) {
		return editDao.deleteEdit(edit);
	}
}