package com.erl.pageflow.writer.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.writer.model.vo.Writer;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.writer.model.dao.WriterDao;

@Service("writerservice")
public class WriterServiceImpl implements WriterService{
	@Autowired
	private WriterDao writerDao;
	
	@Override
	public int selectWriterListCount() {
		return writerDao.selectWriterListCount();
	}
	
	@Override
	public ArrayList<Writer> selectWriterList(Paging paging) {
		return writerDao.selectWriterList(paging);
	}
	
	@Override
	public ArrayList<Writer> selectWriterSearch(Paging paging) {
		return writerDao.selectWriterSearch(paging);
	}
	
	@Override
	public Writer selectWriter(String writerId) {
		return writerDao.selectWriter(writerId);
	}
	
	@Override
	public int insertWriter(Writer writer) {
		return writerDao.insertWriter(writer);
	}
	
	@Override
	public int updateWriter(Writer writer) {
		return writerDao.updateWriter(writer);
	}
	
	@Override
	public int deleteWriter(String writerId) {
		return writerDao.deleteWriter(writerId);
	}
}
