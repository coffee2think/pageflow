package com.erl.pageflow.notice.model.service;


import java.util.ArrayList;
import java.util.List;

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
	public ArrayList<Notice> selectNoticeList(Paging paging){
		return noticeDao.selectNoticeList(paging);
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

  @Override
	public ArrayList<Notice> selectImportantNoticeList(Paging paging) {
		return noticeDao.selectImportantNoticeList(paging);
	}

	@Override
	public int updateNoticeAlarmAll() {
		return noticeDao.updateNoticeAlarmAll();
	}

	@Override
	public int updateNoticeAlarmDept(int depId) {
		return noticeDao.updateNoticeAlarmDept(depId);
	}

	@Override
	public int updateNoticeAlarmEmp(List<Integer> empIdList) {
		return noticeDao.updateNoticeAlarmEmp(empIdList);
	}

	@Override
	public int insertReferenceNotice(Notice notice) {
		return noticeDao.insertReferenceNotice(notice);
	}

	@Override
	public int updateMinusNoticeAlarm(int loginMemberId) {
		return noticeDao.updateMinusNoticeAlarm(loginMemberId);
	}

	@Override
	public int selectReferenceNotice(Notice notice) {
		return noticeDao.selectReferenceNotice(notice);
	}

	@Override
	public int deleteReferenceNotice(int noticeId) {
		return noticeDao.deleteReferenceNotice(noticeId);
	}

	@Override
	public int selectReadEmpCount(int noticeId) {
		return noticeDao.selectReadEmpCount(noticeId);
	}

}
