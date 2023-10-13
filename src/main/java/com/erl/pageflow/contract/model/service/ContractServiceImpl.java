package com.erl.pageflow.contract.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.contract.model.dao.ContractDao;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractDao contractDao;
}
