package com.erl.pageflow.edit.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
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
	public Edit selectEdit(String editId) {
		return editDao.selectEdit(editId);
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
	public int deleteEdit(String editId) {
		return editDao.deleteEdit(editId);
	}
}
