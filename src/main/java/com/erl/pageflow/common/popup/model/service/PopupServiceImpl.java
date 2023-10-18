package com.erl.pageflow.common.popup.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.popup.model.dao.PopupDao;

@Service("popupService")
public class PopupServiceImpl {
	
	@Autowired
	private PopupDao popupDao;
	
	
	

}
