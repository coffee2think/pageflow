package com.erl.pageflow.store.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.store.model.dao.StoreDao;

@Service("storeService")
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDao storeDao;
}
