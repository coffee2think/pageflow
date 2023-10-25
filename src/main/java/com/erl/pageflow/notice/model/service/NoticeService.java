package com.erl.pageflow.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.notice.model.vo.Notice;



public interface NoticeService {
	ArrayList<Notice> selectNoticeList(Paging paging);
	int selectListCount();
	int selectSearchTitleCount(String keyword);
	int selectSearchWriterCount(String keyword);
	ArrayList<Notice> selectSearchTitle(Search search);
	ArrayList<Notice> selectSearchWriter(Search search);
	int insertNotice(Notice notice);
	Notice selectOne(int noticeId);
	int updateNotice(Notice notice);
	int deleteNotice(int noticeId);
  int selectNoticeCountByDate(Search search);
  public ArrayList<Notice> selectNoticeByDate(Search search);
  int updateReadCount(int noticeId);
  public ArrayList<Notice> selectNewTop();
	ArrayList<Notice> selectImportantNoticeList(Paging paging);
	int updateNoticeAlarmAll();
	int updateNoticeAlarmDept(int depId);
	int updateNoticeAlarmEmp(List<Integer> empIdList);
	int insertReferenceNotice(Notice notice);
	int updateMinusNoticeAlarm(int loginMemberId);
	int selectReferenceNotice(Notice notice);
	int deleteReferenceNotice(int noticeId);
	int selectReadEmpCount(int noticeId);

}
