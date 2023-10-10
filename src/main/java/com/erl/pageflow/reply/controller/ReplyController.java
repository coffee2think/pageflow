package com.erl.pageflow.reply.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.erl.pageflow.board.controller.BoardController;
import com.erl.pageflow.reply.model.service.ReplyServiceImpl;

@Controller
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private ReplyServiceImpl replyService;
	
	
	
}
