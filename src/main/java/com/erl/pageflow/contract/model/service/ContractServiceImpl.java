package com.erl.pageflow.contract.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.contract.model.dao.ContractDao;
import com.erl.pageflow.contract.model.vo.Contract;
import com.erl.pageflow.edit.model.vo.Edit;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractDao contractDao;
	
	@Override
	public int selectContractListCount() {
		return contractDao.selectContractListCount();
	}
	
	@Override
	public ArrayList<Contract> selectContractList(Paging paging){
		return contractDao.selectContractList(paging);
	}

	@Override
	public ArrayList<Contract> selectContractSearch(Paging paging) {
		return contractDao.selectContractSearch(paging);
	}

	@Override
	public Contract selectContract(int contrId) {
		return contractDao.selectContract(contrId);
	}

	@Override
	public int insertContract(Contract contract) {
		return contractDao.insertContract(contract);
	}
	
	@Override
	public int updateContract(Contract contract) {
		return contractDao.updateContract(contract);
	}
	
	@Override
	public int deleteContract(int contrId) {
		return contractDao.deleteContract(contrId);
	}
	
	@Override
	public int selectMaxContrId() {
		return contractDao.selectMaxContrId();
	}
	
	@Override
	public ArrayList<Contract> selectContractByDate(Search search) {
		return contractDao.selectContractByDate(search);
	}
	
	@Override
	public int selectContractCountByDate(Search search) {
		return contractDao.selectContractCountByDate(search);
	}
}
