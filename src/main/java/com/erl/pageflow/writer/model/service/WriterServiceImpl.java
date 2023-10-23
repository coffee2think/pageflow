package com.erl.pageflow.writer.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.writer.model.vo.Writer;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
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
	public Writer selectWriter(int writerId) {
		return writerDao.selectWriter(writerId);
	}

	@Override
	public int selectMaxWriterId() {
		return writerDao.selectMaxWriterId();
	}

	@Override
	public int selectWriterCountByWriter(Search search) {
		return writerDao.selectWriterCountByWriter(search);
	}

	@Override
	public ArrayList<Writer> selectWriterByWriter(Search search) {
		return writerDao.selectWriterByWriter(search);
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
	public int deleteWriter(int writerId) {
		return writerDao.deleteWriter(writerId);
	}
}
