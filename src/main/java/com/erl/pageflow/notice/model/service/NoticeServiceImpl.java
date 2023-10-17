package com.erl.pageflow.notice.model.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.notice.model.dao.NoticeDao;
import com.erl.pageflow.notice.model.vo.Notice;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired 
	private NoticeDao noticeDao;
	
	@Override
	public ArrayList<Notice> selectList(Paging paging){
		
		return noticeDao.selectList( paging);
		
		 
	}

	@Override
	public int selectListCount() {
		
		return noticeDao.selectListCount();
	}

	@Override
	public int insertNotice(Notice notice) {
		
		return noticeDao.insertNotice(notice);
	}

	

	

}
