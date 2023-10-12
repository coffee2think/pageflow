package com.erl.pageflow.sales.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.sales.model.dao.SalesDao;

@Service("salesService")
public class SalesServiceImpl implements SalesService {
	@Autowired
	private SalesDao salesDao;
}
