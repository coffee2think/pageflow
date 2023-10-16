package com.erl.pageflow.reply.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.common.UploadKeyword;
import com.erl.pageflow.reply.model.dao.ReplyDao;
import com.erl.pageflow.reply.model.vo.Reply;
import com.erl.pageflow.reply.model.vo.ReplyUpload;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;

	@Override
	public ArrayList<Reply> selectReplyList(ReplyKeyword replyKeyword) {
		return replyDao.selectReplyList(replyKeyword);
	}

	@Override
	public int selectReplyListCount(ReplyKeyword replyKeyword) {
		return replyDao.selectReplyListCount(replyKeyword);
	}

	@Override
	public String selectReplyEmpName(int replyId) {
		return replyDao.selectReplyEmpName(replyId);
	}

	@Override
	public int insertReply(Reply reply) {
		return replyDao.insertReply(reply);
	}

	@Override
	public int insertUploadReply(ReplyUpload replyUpload) {
		return replyDao.insertUploadReply(replyUpload);
	}

	@Override
	public Reply selectReplyRecent() {
		return replyDao.selectReplyRecent();
	}

	@Override
	public ReplyUpload selectReplyListFile(int replyId) {
		return replyDao.selectReplyListFile(replyId);
	}

	@Override
	public int selectReplyMaxCount() {
		return replyDao.selectReplyMaxCount();
	}

	@Override
	public int insertReply2(Reply reply) {
		return replyDao.insertReply2(reply);
	}
	
	
	
}
