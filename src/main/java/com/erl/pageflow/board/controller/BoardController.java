package com.erl.pageflow.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.board.model.service.BoardService;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.board.model.vo.BoardUpload;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.FileNameChange;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.reply.model.service.ReplyService;
import com.erl.pageflow.reply.model.vo.Reply;
import com.erl.pageflow.reply.model.vo.ReplyUpload;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
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
	public String selectBoardListMethod(Model model,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		int depId = 1;
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = boardService.selectBoardListCount();
		String depName = boardService.selectDepName(depId);
		
		Paging paging = new Paging(listCount, currentPage, limit, "bdlist.do");
		//"bdlistdate.do?begin="+search.getBegin().toString()+"&end="+search.getEnd().toString());
		paging.calculator();
		ArrayList<Board> list = boardService.selectBoardList(paging);
		
		for(Board b : list) {
			int replyCount = replyService.selectReplyListCount(new ReplyKeyword(b.getDepId(), b.getBoardId()));
			b.setReplyCount(replyCount);
		}
		
		LocalDate today = LocalDate.now();
		//model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("begin", today.minusWeeks(1).plusDays(1)); // 일주일 전부터
		model.addAttribute("end", today); // 오늘까지
		
		
		model.addAttribute("paging", paging);
		model.addAttribute("depId", depId);
		model.addAttribute("depName", depName);
		model.addAttribute("firstType", "first");
		
		if(list != null && list.size() > 0) {
			model.addAttribute("boardList", list);
			//return "work/work_list";
		}else {
			//model.addAttribute("message", paging + " 업무게시판 조회 실패!");
			//return "common/error";
		}
		return "work/work_list";
		
	}
	
	//업무게시판 게시글 최신 리스트 조회
	@RequestMapping("bdlistnew.do")
	public String selectBoardListNewMethod(Board board, Model model, 
			@RequestParam("newdays") int newdays,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		LocalDate today = LocalDate.now();
//			model.addAttribute("begin", today.minusDays(today.getDayOfMonth() - 1)); // 이번달 1일부터
		model.addAttribute("begin", today.minusDays(newdays)); // 3일 전부터
		model.addAttribute("end", today); // 오늘까지
		model.addAttribute("depId", board.getDepId());//
		model.addAttribute("empId", board.getEmpId());//
		model.addAttribute("firstType", "date");
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		model.addAttribute("page", currentPage);//
		model.addAttribute("limit", limit);
		
		return "redirect:bdlistdate.do";
	}
	
	//----------------마이-----------------
	//업무게시판 게시글 리스트 조회
	@RequestMapping("bdlistmy.do")
	public String selectBoardListMyMethod(Board board, Model model,
			@RequestParam(name = "searchType", required=false) String searchType,
			@RequestParam(name="begin", required=false) String begin,
			@RequestParam(name="end", required=false) String end,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		int listCount = boardService.selectBoardListCountMy(
				new BoardKeyword(board.getEmpId(), board.getDepId()));
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		String depName = boardService.selectDepName(board.getDepId());
		
		Paging paging = new Paging(listCount, currentPage, limit, "bdlistmy.do");
		paging.calculator();
		ArrayList<Board> list = boardService.selectBoardListMy(
				new Search(board.getEmpId(), board.getDepId(), paging.getStartRow(), paging.getEndRow()));
		for(Board b : list) {
			int replyCount = replyService.selectReplyListCount(new ReplyKeyword(b.getDepId(), b.getBoardId()));
			b.setReplyCount(replyCount);
		}
		
		model.addAttribute("paging", paging);
		model.addAttribute("depId", board.getDepId());
		model.addAttribute("depName", depName);
		model.addAttribute("searchType", searchType);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("firstType", "first");
		if(list != null && list.size() > 0) {
			
			model.addAttribute("boardList", list);
			
		}else {
			//model.addAttribute("message", paging + " 업무게시판 조회 실패!");
			//return "common/error";
		}
		
		return "work/work_list";
	}
	
	//----------------날짜-----------------
	//업무게시판 게시글 필터링된 리스트 숫자 조회
	@RequestMapping(value = "bdlistdatecount.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void selectBoardListDateCountMethod(Search search, 
				HttpServletResponse response) throws IOException {
		
		int count = boardService.selectBoardListDateCount(search);
		
		PrintWriter out = response.getWriter();
		out.print(count);
		out.flush();
		out.close();
	}
	
	//업무게시판 게시글 필터링된 리스트 조회
	@RequestMapping("bdlistdate.do")
	public String selectBoardListDateMethod(Search search, Model model,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		
		int listCount = boardService.selectBoardListDateCount(search);
		String depName = boardService.selectDepName(search.getDepId());
		
		logger.info("depName : " + depName);
		logger.info("listCount : " + listCount);
		Paging paging = new Paging(listCount, currentPage, limit, "bdlistdate.do");
				//"bdlistdate.do?begin="+search.getBegin().toString()+"&end="+search.getEnd().toString());
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Board> list = boardService.selectBoardListDate(search);
		logger.info("list : " + list);
		for(Board b : list) {
			int replyCount = replyService.selectReplyListCount(new ReplyKeyword(b.getDepId(), b.getBoardId()));
			b.setReplyCount(replyCount);
		}
		model.addAttribute("paging", paging);
		model.addAttribute("depId", search.getDepId());
		model.addAttribute("depName", depName);
		model.addAttribute("searchType", search.getSearchType());
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		model.addAttribute("firstType", "date");
		if(list != null && list.size() > 0) {
			model.addAttribute("boardList", list);
			//return "work/work_list";
		}else {
			//model.addAttribute("message", paging + " 업무게시판 조회 실패!");
			//return "common/error";
		}
		return "work/work_list";
	}
	
	
	@RequestMapping("bdselect.do")
	public String selectBoardMethod(Search search, Board b, Model model) {
//							 @RequestParam("empId") int empId, 
//							 @RequestParam("depId") int depId,
//							 @RequestParam("boardId") int boardId, Model model) {
		
		
		int empId = b.getEmpId();
		int depId = b.getDepId();
		int boardId = b.getBoardId();
		
		logger.info("empId : " + empId + " depId : " + depId + " boardId : " + boardId);
		
		Board board = boardService.selectBoard(new BoardKeyword(empId, depId, boardId));
		
		if(board != null) {
			ArrayList<Reply> replyList = replyService.selectReplyList(new ReplyKeyword(board.getDepId(), board.getBoardId()));
			
			for(int i=0; i<replyList.size(); i++) {
				//부모 댓글 작성자 이름 호출
				Reply r = replyList.get(i);
				
				//댓글의 첨부파일 찾기
				ReplyUpload replyUpload = replyService.selectReplyListFile(r.getReplyId());
				
				if(replyUpload != null) {
					r.setRenameFile(replyUpload.getRenameUrl());
					r.setOriginFile(replyUpload.getUploadUrl());
				}
				
				String name = null;
				if(r.getDepth() == -1) {
					name = board.getEmpName();
				}else {
					name = replyService.selectReplyEmpName(r.getParentId());
				}
				
				r.setParentEmpName(name);
			}
			
			//보드 댓글 하나 올리기
			boardService.updateBoardViewNum(new BoardKeyword(empId, depId, boardId));
			board.setViewsNum(board.getViewsNum() + 1);
			
			//보드 첨부파일 찾기
			BoardUpload boardUpload = boardService.selectBoardListFile(new BoardKeyword(empId, depId, boardId));
			
			if(boardUpload != null) {
				board.setRenameFile(boardUpload.getRenameUrl());
				board.setOriginFile(boardUpload.getUploadUrl());
			}
			
			model.addAttribute("board", board);
			model.addAttribute("replyList", replyList);
			model.addAttribute("begin", search.getBegin().toString());
			model.addAttribute("end", search.getEnd().toString());
			model.addAttribute("firstType", "first");
			return "work/work_notice";
		}else {
			model.addAttribute("message", "업무게시판 조회 실패!");
			return "common/error";
		}
	}
	
	//----------------서치-----------------
	//업무게시판 게시글 검색
	@RequestMapping(value = "bdsearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchBoardMethod(Search search, Model model,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String limitStr) {
		
		String searchType = search.getSearchType();
		int listCount = 0;
		String depName = boardService.selectDepName(search.getDepId());
		logger.info("searchType : " + searchType);
		if(searchType != null) {
			switch (searchType) {
				case "content":
					listCount = boardService.selectBoardSearchCountContent(search);
					break;
				case "writer":
					listCount = boardService.selectBoardSearchCountWriter(search);
					break;
				case "title":
					listCount = boardService.selectBoardSearchCountTitle(search);
					break;
			}
			
		}
		
		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (limitStr != null) ? Integer.parseInt(limitStr) : 10;
		logger.info("listCount : " + listCount);
		Paging paging = new Paging(listCount, currentPage, limit, 
				"bdsearch.do?depId="+search.getDepId() + "&searchType=" + searchType);
		paging.calculator();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<Board> list = null;
		if(searchType != null) {
			switch (searchType) {
				case "content":
					list = boardService.selectBoardSearchContent(search);
					break;
				case "writer":
					list = boardService.selectBoardSearchWriter(search);
					break;
				case "title":
					list = boardService.selectBoardSearchTitle(search);
					break;
			}
		}
		
		logger.info("list : " + list);
		
		model.addAttribute("keyword", search.getKeyword());
		model.addAttribute("paging", paging);
		model.addAttribute("depId", search.getDepId());
		model.addAttribute("depName", depName);
		model.addAttribute("searchType", searchType);
		model.addAttribute("begin", search.getBegin().toString());
		model.addAttribute("end", search.getEnd().toString());
		model.addAttribute("firstType", "first");
		if(list != null && list.size() > 0) {
			
			for(Board b : list) {
				int replyCount = replyService.selectReplyListCount(new ReplyKeyword(b.getDepId(), b.getBoardId()));
				b.setReplyCount(replyCount);
			}
			model.addAttribute("boardList", list);
		}else {
			//model.addAttribute("message", paging + " 업무게시판 서치 실패!");
			//return "common/error";
		}
		
		return "work/work_list";
	}
	
	//업무게시판 게시글 등록
	@RequestMapping(value = "bdinsert.do", method = RequestMethod.POST)
	public String insertBoardMethod(Board board, Model model,
			HttpServletRequest request, 
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		logger.info("board : " + board);
		logger.info("mfile : " + mfile);
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/board_upfiles");
		
		if(boardService.insertBoard(board) > 0) {
			model.addAttribute("depId", board.getDepId());
			int boardId = boardService.selectBoardId();
			
			//첨부파일이 있을때 
			if(mfile != null) {
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
					//첨부파일 정보 저장 처리
					BoardUpload boardUpload = new BoardUpload(
							0, board.getDepId(), boardId, fileName, renameFileName);
							
					boardService.insertUploadBoard(boardUpload);
				}
			}
			
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
	@RequestMapping("bdmoveupdate.do")
	public String updateMoveBoardMethod(Board board, Model model) {
		logger.info("bdmoveupdate.do => board : " + board);
		//보드 첨부파일 찾기
		BoardUpload boardUpload = boardService.selectBoardListFile(
				new BoardKeyword(board.getEmpId(), board.getDepId(), board.getBoardId()));
		
		if(boardUpload != null) {
			board.setRenameFile(boardUpload.getRenameUrl());
			board.setOriginFile(boardUpload.getUploadUrl());
		}
		
		model.addAttribute("board", board);
		model.addAttribute("depId", board.getDepId());
		return "work/work_input";
	}
	
	//업무게시판 게시글 수정
	@RequestMapping("bdupdate.do")
	public String updateBoardMethod(
			@RequestParam("boardId") int boardId,
			@RequestParam("depId") int depId,
			@RequestParam("empId") int empId,
			@RequestParam("boardTitle") String boardTitle,
			@RequestParam("boardDetail") String boardDetail,
			Model model) {
		logger.info("bdupdate.do => boardId : " + boardId);
		logger.info("bdupdate.do => depId : " + depId);
		//보드 첨부파일 찾기
		BoardUpload boardUpload = boardService.selectBoardListFile(
				new BoardKeyword(empId, depId, boardId));
		
		
		if(boardUpload != null) {
			Board board = new Board(depId, boardId, empId);
			board.setRenameFile(boardUpload.getRenameUrl());
			board.setOriginFile(boardUpload.getUploadUrl());
			board.setBoardDetail(boardTitle);
			board.setBoardDetail(boardDetail);
			boardService.updateUploadBoard(board);
			
			if(boardService.updateBoard(board) > 0) {
				model.addAttribute("message", "게시글 수정 실패!");
				return "common/error";
			}
			
		}
		model.addAttribute("empId", empId);
		model.addAttribute("depId", depId);
		model.addAttribute("boardId", boardId);
		return "redirect:bdselect.do";
	}
	
	//업무게시판 게시글 삭제
	@RequestMapping("bddelete.do")
	public String deleteBoardMethod(Board board) {
		
		if(boardService.deleteBoard(board) > 0) {
			
		}
		
		return "redirect:bdlist.do";
	}
	
}
