package com.erl.pageflow.reply.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.reply.model.vo.Reply;

public interface ReplyService {
	//업무게시판 게시글 리스트 조회
	public ArrayList<Reply> selectReplyList(ReplyKeyword replyKeyword);
	
	public int selectReplyListCount(ReplyKeyword replyKeyword);
	
	public String selectReplyEmpName(int replyId);
}
