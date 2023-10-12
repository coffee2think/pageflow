package com.erl.pageflow.board.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.board.model.service.BoardServiceImpl;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.FileNameChange;
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
	//업무게시판 글작성 이동
	@RequestMapping("bdmoveinsert.do")
	public String moveBoardInput(@RequestParam("depId") int depId, Model model) {
		model.addAttribute("depId", depId);
		return "work/work_input";
	}
	
	//**************요청 받아서 서비스로 넘기는 메소드****************
	
	//업무게시판 게시글 리스트 조회
	@RequestMapping("bdlist.do")
	public String selectBoardList(Model model) {
		int listCount = boardService.selectBoardListCount();
		int limit = 10;
		Paging paging = new Paging(listCount, 1, limit, "bdlist.do");
		paging.calculator();
		ArrayList<Board> list = boardService.selectBoardList(paging);
		for(Board b : list) {
			int replyCount = replyService.selectReplyListCount(new ReplyKeyword(b.getDepId(), b.getBoardId()));
			b.setReplyCount(replyCount);
		}
		
		logger.info("???");
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
			
			for(int i=0; i<replyList.size(); i++) {
				Reply r = replyList.get(i);
				String name = null;
				if(r.getDepth() == -1) {
					name = board.getEmpName();
				}else {
					name = replyService.selectReplyEmpName(r.getParentId());
				}
				
				r.setParentEmpName(name);
			}
			
			model.addAttribute("board", board);
			model.addAttribute("replyList", replyList);
			return "work/work_notice";
		}else {
			model.addAttribute("message", "업무게시판 조회 실패!");
			return "common/error";
		}
	}
	
	//업무게시판 게시글 필터링된 리스트 조회
	@RequestMapping("bdsearch.do")
	public String selectBoardSearch() {
		return "";
	}
	
	//업무게시판 게시글 등록
	@RequestMapping(value = "bdinsert.do", method = RequestMethod.POST)
	public String insertBoard(Board board, Model model,
			HttpServletRequest request, 
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/board_upfiles");
		//첨부파일이 있을때 
		if(!mfile.isEmpty()) {
			//전송온 파일이름 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;
			
			//저장폴더에는 변경된 이름을 저장 처리함
			//파일 이름 바꾸기함 : 년월일시분초.확장자
			if(fileName != null && fileName.length() > 0) {
				//바꿀 파일명에 대한 문자열 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				
				try {
					//저장폴더에 파일명 바꾸기 처리
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
					
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "파일명 바꾸기 또는 첨부파일 저장 실패");
					return "common/error";
				}
			}
			
			//notice객체에 첨부파일 정보 저장 처리
			//board.setBoardOriginalFileName(fileName);
			//board.setBoardRenameFileName(renameFileName);
		}
		
		if(boardService.insertBoard(board) > 0) {
			return "redirect:bdlist.do";
		}else {
			model.addAttribute("message", "새 게시글 등록 실패!");
			return "common/error";
		}
		
	}
	
	//첨부파일 다운로드 요청 처리용 
	@RequestMapping("bddown.do")
	public ModelAndView fileDownMethod(
			ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, 
			@RequestParam("rfile") String renameFileName) {
		//파일다운 메소드는 ModelAndView로 리턴해야 함
		
		//게시글 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/board_upfiles"); 
		
		//저장 폴더에서 읽을 파일에 대한 파일객체를 생성함
		File renameFile = new File(savePath + "\\" + renameFileName);
		
		//파일 다운 시 브라우저로 내보낼 원래 파일명에 대한 파일 객체 생성함
		File originFile = new File(originalFileName);
		
		//파일 다운로드용 뷰로 전달할 정보 저장 처리
		mv.setViewName("filedown");//등록된 파일다운로드용 뷰클래스의 id명
		mv.addObject("renameFile", renameFile);
		mv.addObject("originFile", originFile);
		
		return mv;
	}
	
	//업무게시판 게시글 수정
	@RequestMapping(value = "bdupdate.do", method = RequestMethod.POST)
	public String updateBoard() {
		return "";
	}
	
	//업무게시판 게시글 삭제
	@RequestMapping(value = "bddelete.do", method = RequestMethod.POST)
	public String deleteBoard() {
		return "";
	}
	
}
