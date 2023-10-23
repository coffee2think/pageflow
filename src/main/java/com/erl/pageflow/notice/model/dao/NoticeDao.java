package com.erl.pageflow.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.notice.model.vo.Notice;
import com.erl.pageflow.sales.model.vo.Client;

@Repository("noticeDao")
public class NoticeDao {
	@Autowired //root-context.xml 에서 생성한 객체를 자동 연결함
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Notice> selectList( Paging paging){
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectNoticeList", paging);
		return (ArrayList<Notice>)list;
	}

	public int selectListCount() {
		return sqlSessionTemplate.selectOne("noticeMapper.selectListCount");
	}
	
	public int insertNotice(Notice notice ) {
		return sqlSessionTemplate.insert("noticeMapper.insertNotice", notice);
	}
	
	public Notice selectOne( int noticeId) {
		return sqlSessionTemplate.selectOne("noticeMapper.selectOne", noticeId);
	}
	
	public int updateNotice(Notice notice) {
		return sqlSessionTemplate.update("noticeMapper.updateNotice", notice);
	}
	
	public int deleteNotice(int noticeId) {
		return sqlSessionTemplate.delete("noticeMapper.deleteNotice", noticeId);
	}
	
	public ArrayList<Notice> selectSearchTitle(Search search){
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectSearchTitle", search);
		return (ArrayList<Notice>)list;
	}
	
	public ArrayList<Notice> selectSearchWriter(Search search){
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectSearchWriter", search);
		return (ArrayList<Notice>)list;
	}
	
	public int selectSearchTitleCount(String keyword) {
		return sqlSessionTemplate.selectOne("noticeMapper.selectSearchTitleCount", keyword);
	}
	
	public int selectSearchWriterCount(String keyword) {
		return sqlSessionTemplate.selectOne("noticeMapper.selectSearchWriterCount", keyword);
	}
	
	public int selectNoticeCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("noticeMapper.selectNoticeCountByDate", search);
	}
	
	public ArrayList<Notice> selectNoticeByDate(Search search) {
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectNoticeByDate", search);
		return (ArrayList<Notice>) list;
	}
	
	public int updateReadCount(int noiceId) {
		return sqlSessionTemplate.update("noticeMapper.updateReadCount", noiceId );
	}

	
	
	
	
	
	
}
