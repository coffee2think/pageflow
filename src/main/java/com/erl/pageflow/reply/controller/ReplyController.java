package com.erl.pageflow.reply.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.erl.pageflow.board.controller.BoardController;
import com.erl.pageflow.common.FileNameChange;
import com.erl.pageflow.common.UploadKeyword;
import com.erl.pageflow.reply.model.service.ReplyService;
import com.erl.pageflow.reply.model.vo.Reply;
import com.erl.pageflow.reply.model.vo.ReplyUpload;

@Controller
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private ReplyService replyService;
	
	//댓글의 댓글 달기
	@RequestMapping(value = "ryinsert.do", method = RequestMethod.POST)
	@ResponseBody
	public void insertReplyMethod(Reply reply, Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) throws IOException{
		
		//ajax요청시 리턴방법은 여러가지가 있음
		//response 객체 이용시에는 2가지 중 선택 가능
		//1. 출력 스트림으로 응답하는 방법(아이디 중복체크 예)
		//2. 뷰리졸버로 리턴하는 방법 : response body에 내보낼 값을 저장함
		//   JSON View 등록처리 되어 있어야 함 : servlet-cntext.xml
		logger.info("reply : " + reply);
		logger.info("mfile : " + mfile);
		String fileName = null;
		String renameFileName = null;
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/board_upfiles");
		
		JSONObject sendJson = new JSONObject();
		
		if(replyService.insertReply(reply) > 0) {
			logger.info("==reply insert1==");
			//전송용 json 객체 준비
			Reply selReply = replyService.selectReplyRecent();
			
			//첨부파일이 있을때 
			if(mfile != null) {
				if(!mfile.isEmpty()) {
					//전송온 파일이름 추출함
					fileName = mfile.getOriginalFilename();
					renameFileName = null;
					
					//저장폴더에는 변경된 이름을 저장 처리함
					//파일 이름 바꾸기함 : 년월일시분초.확장자
					if(fileName != null && fileName.length() > 0) {
						//바꿀 파일명에 대한 문자열 만들기
						renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
						logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
						
						try {
							//저장폴더에 파일명 바꾸기 처리
							mfile.transferTo(new File(savePath + "\\" + renameFileName));
							
							//upload_reply에 첨부파일 정보 저장 처리
							replyService.insertUploadReply(
									new ReplyUpload (0, selReply.getReplyId(), fileName, renameFileName));
							
						} catch (Exception e) {
							e.printStackTrace();
							//model.addAttribute("message", "파일명 바꾸기 또는 첨부파일 저장 실패");
							//return "common/error";
						}
					}
					
					
				}
			}
			//response.setContentType("text/html; charset=utf-8");
			
			//sendJson.put("reply_id", String.valueOf(selReply.getReplyId()));
			//sendJson.put("emp_name", URLEncoder.encode(selReply.getEmpName(), "utf-8"));
			//sendJson.put("profile", URLEncoder.encode((selReply.getProfile() == null) ? "" : selReply.getProfile(), "utf-8"));
			//sendJson.put("create_date", selReply.getCreateDate().toString());
			//sendJson.put("origin_file", URLEncoder.encode((fileName == null) ? "" : fileName, "utf-8"));
			//sendJson.put("rename_file", (renameFileName == null) ? "" : renameFileName);
			
			String rstr = selReply.getReplyId() 
						+ "#%" + URLEncoder.encode(selReply.getEmpName(), "utf-8")
			 			+ "#%" + URLEncoder.encode((selReply.getProfile() == null) ? "" : selReply.getProfile(), "utf-8")
			 			+ "#%" + selReply.getCreateDate().toString()
			 			+ "#%" + URLEncoder.encode((fileName == null) ? "" : fileName, "utf-8")
			 			+ "#%" + ((renameFileName == null) ? "" : renameFileName);
			
			PrintWriter out = response.getWriter();
			//out.print(sendJson);
			out.print(rstr);
			out.flush();
			out.close();
			
		}
		//return sendJson.toJSONString();//뷰리졸버로 리턴함
		
	}
	
	//맨 위의 댓글 달기
	@RequestMapping(value = "ryinsertfirst.do", method = RequestMethod.POST)
	@ResponseBody
	public void insertReplyMethodFirst(Reply reply, Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) throws IOException{
		
		logger.info("ryinsertfirst reply : " + reply);
		String fileName = null;
		String renameFileName = null;
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/board_upfiles");
		
		JSONObject sendJson = new JSONObject();
		
		if(replyService.insertReply2(reply) > 0) {
			
			//전송용 json 객체 준비
			Reply selReply = replyService.selectReplyRecent();
			logger.info("ryinsert2 selReply : " + selReply);
			//첨부파일이 있을때 
			if(mfile != null) {
				if(!mfile.isEmpty()) {
					//전송온 파일이름 추출함
					fileName = mfile.getOriginalFilename();
					renameFileName = null;
					
					//저장폴더에는 변경된 이름을 저장 처리함
					//파일 이름 바꾸기함 : 년월일시분초.확장자
					if(fileName != null && fileName.length() > 0) {
						//바꿀 파일명에 대한 문자열 만들기
						renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
						logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
						
						try {
							//저장폴더에 파일명 바꾸기 처리
							mfile.transferTo(new File(savePath + "\\" + renameFileName));
							
							//upload_reply에 첨부파일 정보 저장 처리
							replyService.insertUploadReply(
									new ReplyUpload (0, selReply.getReplyId(), fileName, renameFileName));
							
						} catch (Exception e) {
							e.printStackTrace();
							//model.addAttribute("message", "파일명 바꾸기 또는 첨부파일 저장 실패");
							//return "common/error";
						}
					}
					
					
				}
			}
			
			String rstr = selReply.getReplyId() 
						+ "#%" + URLEncoder.encode(selReply.getEmpName(), "utf-8")
			 			+ "#%" + URLEncoder.encode((selReply.getProfile() == null) ? "" : selReply.getProfile(), "utf-8")
			 			+ "#%" + selReply.getCreateDate().toString()
			 			+ "#%" + URLEncoder.encode((fileName == null) ? "" : fileName, "utf-8")
			 			+ "#%" + ((renameFileName == null) ? "" : renameFileName);
			
			PrintWriter out = response.getWriter();
			//out.print(sendJson);
			out.print(rstr);
			out.flush();
			out.close();
			
		}
	}
	
	
}
