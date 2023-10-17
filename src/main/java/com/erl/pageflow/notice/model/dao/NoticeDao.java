package com.erl.pageflow.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.notice.model.vo.Notice;

@Repository("noticeDao")
public class NoticeDao {
	@Autowired //root-context.xml 에서 생성한 객체를 자동 연결함
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Notice> selectList( Paging paging){
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectNoticeList", paging);
		return (ArrayList<Notice>)list;
	}

	public int selectListCount() {
		return sqlSessionTemplate.selectOne("noticeMapper.getListCount");
	}
	
	public int insertNotice(Notice notice ) {
		return sqlSessionTemplate.insert("noticeMapper.insertNotice", notice);
	}
}
