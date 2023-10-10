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
	
	@Autowired
	private ReplyServiceImpl replyService;
	
	//**************뷰페이지 이동****************
	//업무게시판 리스트 이동
	@RequestMapping("work_list.do")
	public String moveBoardList() {
		return "work/work_list";
	}
	
	//업무게시판 글보기 이동
	@RequestMapping("work_notice.do")
	public String moveBoardNotice() {
		return "work/work_notice";
	}
	
	//업무게시판 글작성 이동
	@RequestMapping("work_input.do")
	public String moveBoardInput() {
		return "work/work_input";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	
	//업무게시판 게시글 리스트 조회
	@RequestMapping("bdlist.do")
	public String selectBoardList(Model model) {
		int listCount = boardService.selectBoardListCount();
		
		Paging paging = new Paging(listCount, 1, 10, "selectBoardList.do");
		paging.calculator();
		ArrayList<Board> list = boardService.selectBoardList(paging);
		//logger.info("???");
		//logger.info("=========list : " + list);
		if(list != null && list.size() > 0) {
			model.addAttribute("paging", paging);
			model.addAttribute("boardList", list);
			return "work/work_list";
		}else {
			model.addAttribute("message", paging + " 업무게시판 조회 실패!");
			return "common/error";
		}
	}
	
	@RequestMapping("bdselect.do")
	public String selectBoard(@RequestParam("empId") int empId, 
							 @RequestParam("depId") int depId,
							 @RequestParam("boardId") int boardId, Model model) {
		
		Board board = boardService.selectBoard(new BoardKeyword(empId, depId, boardId));
		
		if(board != null) {
			
			ArrayList<Reply> replyList = replyService.selectReplyList(new ReplyKeyword(board.getDepId(), board.getBoardId()));
			System.out.println("replyList : " + replyList);
			model.addAttribute("board", board);
			model.addAttribute("replyList", replyList);
			return "work/work_notice";
		}else {
			model.addAttribute("message", "업무게시판 조회 실패!");
			return "common/error";
		}
	}
	
	//업무게시판 게시글 필터링된 리스트 조회
	@RequestMapping("selectFilterWorkList.do")
	public String selectBoardSearch() {
		return "";
	}
	
	//업무게시판 게시글 등록
	@RequestMapping(value = "insertWork.do", method = RequestMethod.POST)
	public String insertBoard() {
		return "";
	}
	
	//업무게시판 게시글 수정
	@RequestMapping(value = "updateWork.do", method = RequestMethod.POST)
	public String updateBoard() {
		return "";
	}
	
	//업무게시판 게시글 삭제
	@RequestMapping(value = "work_list.do", method = RequestMethod.POST)
	public String deleteBoard() {
		return "";
	}
	
}
