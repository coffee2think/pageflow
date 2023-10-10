package com.erl.pageflow.board.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erl.pageflow.board.model.service.BoardServiceImpl;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.reply.model.service.ReplyServiceImpl;
import com.erl.pageflow.reply.model.vo.Reply;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardServiceImpl boardService;
	
	private ReplyServiceImpl replyService = new ReplyServiceImpl();
	
	//**************뷰페이지 이동****************
	//업무게시판 리스트 이동
	@RequestMapping("work_list.do")
	public String moveWorkList() {
		return "work/work_list";
	}
	
	//업무게시판 글보기 이동
	@RequestMapping("work_notice.do")
	public String moveWorkNotice() {
		return "work/work_notice";
	}
	
	//업무게시판 글작성 이동
	@RequestMapping("work_input.do")
	public String moveWorkInput() {
		return "work/work_input";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	//업무게시판 게시글 리스트 갯수 조회
	@RequestMapping("selectWorkListCount.do")
	public String selectWorkListCount() {
		
		
		return "";
	}
	
	//업무게시판 게시글 리스트 조회
	@RequestMapping("selectWorkList.do")
	public String selectWorkList(Model model) {
		int listCount = boardService.selectWorkListCount();
		
		Paging paging = new Paging(listCount, 1, 10, null);
		paging.calculator();
		ArrayList<Board> list = boardService.selectWorkList(paging);
		
		logger.info("=========list : " + list);
		if(list != null && list.size() > 0) {
			model.addAttribute("boardList", list);
			return "work/work_list";
		}else {
			model.addAttribute("message", paging + " 업무게시판 조회 실패!");
			return "common/error";
		}
	}
	
	@RequestMapping("selectWork.do")
	public String selectWork(@RequestParam("empId") int empId, 
							 @RequestParam("depId") int depId,
							 @RequestParam("boardId") int boardId, Model model) {
		
		Board board = boardService.selectWork(new BoardKeyword(empId, depId, boardId));
		
		System.out.println("board : " + board);
		if(board != null) {
			//ArrayList<Reply> replyList = replyService.selectReplyList(new ReplyKeyword(board.getDepId(), board.getBoardId()));
			//System.out.println("replyList : " + replyList);
			model.addAttribute("board", board);
			//model.addAttribute("replyList", replyList);
			return "work/work_notice";
		}else {
			model.addAttribute("message", "업무게시판 조회 실패!");
			return "common/error";
		}
	}
	
	//업무게시판 게시글 필터링된 리스트 조회
	@RequestMapping("selectFilterWorkList.do")
	public String selectFilterWorkList() {
		return "";
	}
	
	//업무게시판 게시글 등록
	@RequestMapping(value = "insertWork.do", method = RequestMethod.POST)
	public String insertWork() {
		return "";
	}
	
	//업무게시판 게시글 수정
	@RequestMapping(value = "updateWork.do", method = RequestMethod.POST)
	public String updateWork() {
		return "";
	}
	
	//업무게시판 게시글 삭제
	@RequestMapping(value = "work_list.do", method = RequestMethod.POST)
	public String deleteWork() {
		return "";
	}
	
}
