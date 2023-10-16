package com.erl.pageflow.contract.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.contract.model.dao.ContractDao;
import com.erl.pageflow.contract.model.vo.Contract;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractDao contractDao;
	
	public int selectContractListCount() {
		return contractDao.selectContractListCount();
	}
	
	public ArrayList<Contract> selectContractList(Paging paging){
		return contractDao.selectContractList(paging);
	}

	public ArrayList<Contract> selectContractSearch(Paging paging) {
		return contractDao.selectContractSearch(paging);
	}

	public Contract selectContract(String contrId) {
		return contractDao.selectContract(contrId);
	}

	public int insertContract(Contract contract) {
		return contractDao.insertContract(contract);
	}
	
	public int updateContract(Contract contract) {
		return contractDao.updateContract(contract);
	}
	
	public int deleteContract(String contrId) {
		return contractDao.deleteContract(contrId);
	}
}
