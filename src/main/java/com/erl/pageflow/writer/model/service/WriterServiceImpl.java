package com.erl.pageflow.writer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.writer.model.dao.WriterDao;

@Service("writerservice")
public class WriterServiceImpl {
	@Autowired
	private WriterDao writerdao;
}
