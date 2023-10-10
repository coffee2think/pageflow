package com.erl.pageflow.reply.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.reply.model.dao.ReplyDao;
import com.erl.pageflow.reply.model.vo.Reply;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;

	@Override
	public ArrayList<Reply> selectReplyList(ReplyKeyword replyKeyword) {
		return replyDao.selectReplyList(replyKeyword);
	}

}
