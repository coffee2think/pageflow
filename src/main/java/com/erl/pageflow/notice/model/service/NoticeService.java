package com.erl.pageflow.notice.model.service;

import java.util.ArrayList;

import org.apache.ibatis.javassist.compiler.ast.Keyword;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.notice.model.vo.Notice;



public interface NoticeService {
	ArrayList<Notice> selectList(Paging paging);
	int selectListCount();
	int selectSearchTitleCount(String keyword);
	int selectSearchWriterCount(String keyword);
	public ArrayList<Notice> selectSearchTitle(Search search);
	public ArrayList<Notice> selectSearchWriter(Search search);
	int insertNotice(Notice notice);
	Notice selectOne(int noticeId);
	int updateNotice(Notice notice);
	int deleteNotice(int noticeId);
}
