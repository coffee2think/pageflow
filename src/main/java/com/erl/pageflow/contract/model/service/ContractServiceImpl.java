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

	@Override
	public int selectContractCountByBook(Search search) {
		return contractDao.selectContractCountByBook(search);
	}

	@Override
	public ArrayList<Contract> selectContractByBook(Search search) {
		return contractDao.selectContractByBook(search);
	}

	@Override
	public int selectContractCountByCategory(Search search) {
		return contractDao.selectContractCountByCategory(search);
	}

	@Override
	public ArrayList<Contract> selectContractByCategory(Search search) {
		return contractDao.selectContractByCategory(search);
	}

	@Override
	public int selectContractCountByWriter(Search search) {
		return contractDao.selectContractCountByWriter(search);

	}

	@Override
	public ArrayList<Contract> selectContractByWriter(Search search) {
		return contractDao.selectContractByWriter(search);
	}

	@Override
	public int selectContractCountByEmployee(Search search) {
		return contractDao.selectContractCountByEmployee(search);
	}

	@Override
	public ArrayList<Contract> selectContractByEmployee(Search search) {
		return contractDao.selectContractByEmployee(search);
	}
	
	@Override
	public int selectContractCountByStatusAll(Search search) {
		return contractDao.selectContractCountByStatus(search);
	}
	
	@Override
	public ArrayList<Contract> selectContractByStatusAll(Search search) {
		return contractDao.selectContractByStatus(search);
	}
	
	@Override
	public int selectContractCountByStatusIng(Search search) {
		return contractDao.selectContractCountByStatus(search);
	}
	
	@Override
	public ArrayList<Contract> selectContractByStatusIng(Search search) {
		return contractDao.selectContractByStatus(search);
	}
	
	@Override
	public int selectContractCountByStatusFinish(Search search) {
		return contractDao.selectContractCountByStatus(search);
	}
	
	@Override
	public ArrayList<Contract> selectContractByStatusFinish(Search search) {
		return contractDao.selectContractByStatus(search);
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
}
