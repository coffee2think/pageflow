package com.erl.pageflow.notice.model.service;

import java.util.ArrayList;

import org.apache.ibatis.javassist.compiler.ast.Keyword;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.notice.model.vo.Notice;
import com.erl.pageflow.sales.model.vo.Client;



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
	ArrayList<Notice> selectImportantNoticeList(Paging paging);

	
	
}
