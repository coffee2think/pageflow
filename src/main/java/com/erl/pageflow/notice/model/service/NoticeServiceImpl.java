package com.erl.pageflow.notice.model.service;


import java.util.ArrayList;

import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
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

	@Override
	public Notice selectOne(int noticeId) {
		
		return noticeDao.selectOne(noticeId);
	}


	@Override
	public int updateNotice(Notice notice) {
		
		return noticeDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int noticeId) {
		
		return noticeDao.deleteNotice(noticeId);
	}

	@Override
	public ArrayList<Notice> selectSearchTitle(Search search) {
		
		return noticeDao.selectSearchTitle(search);
	}

	@Override
	public int selectSearchTitleCount(String keyword) {
	
		return noticeDao.selectSearchTitleCount(keyword);
	}

	@Override
	public int selectSearchWriterCount(String keyword) {
		
		return noticeDao.selectSearchWriterCount(keyword);
	}

	@Override
	public ArrayList<Notice> selectSearchWriter(Search search) {
		
		return noticeDao.selectSearchWriter(search);
	}

	@Override
	public int selectNoticeCountByDate(Search search) {
		
		return noticeDao.selectNoticeCountByDate(search);
	}

	@Override
	public ArrayList<Notice> selectNoticeByDate(Search search) {
		
		return noticeDao.selectNoticeByDate(search);
	}

	@Override
	public int updateReadCount(int noticeId) {
		
		return noticeDao.updateReadCount(noticeId);
	}

	@Override
	public ArrayList<Notice> selectNewTop() {
		return noticeDao.selectNewTop();
	}

}
