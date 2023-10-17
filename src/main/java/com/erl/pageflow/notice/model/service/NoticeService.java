package com.erl.pageflow.notice.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.notice.model.vo.Notice;



public interface NoticeService {
	ArrayList<Notice> selectList(Paging paging);
	int selectListCount();
	int insertNotice(Notice notice);
}
