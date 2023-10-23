package com.erl.pageflow.employee.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.erl.pageflow.common.FileNameChange;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.employee.model.service.EmployeeService;
import com.erl.pageflow.employee.model.vo.Employee;

@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	// 뷰 ---------------------------------------------------------------------------------
	// 로그인 뷰
	@RequestMapping(value = "loginPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveLoginPage() {
		return "member/login";
	}
	
	// 직원등록 페이지로 이동
	@RequestMapping("empmoveinsert.do")
	public String moveempInsertPage() {
		return "member/emp_input";
	}
	
	
	@RequestMapping("idck.do")
	public String idck() {
		return "member/emp_input";
	}
	
	@RequestMapping("empmoveupdate.do")
	public String moveempUpdatePage(Employee employee,  Model model) {
		model.addAttribute("employee", employee);
		return "member/emp_update";
	}
	
	
	
	
	// 값---------------------------------------------------------------------
	
	// 로그인 값전송
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String loginMethod(Employee employee, HttpSession session, SessionStatus status, Model model) {

		Employee loginMember = employeeService.selectEmployee(employee.getEmpId());

		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			status.setComplete();
			return "common/main";
		}else {
			model.addAttribute("message", "로그인 실패");
			return "common/error";
		}
	}
	
	// 로그아웃 값전송
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
			return "common/main";
		}else {
			model.addAttribute("message", "로그아웃 실패");
			return "common/error";
		}
	}
	
	
	//  회원 리스트 조회
	
	@RequestMapping("mnlist.do")
	public ModelAndView memberListViewMethod(@RequestParam(name="page", required=false) String page, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//한페이지에 목록 10개씩 출력되게 한다면 
		int limit = 10;
		//회원 목록 전체 갯수 조회해 옴
		int listCount = employeeService.selectEmployeeListCount();
		Paging paging =new Paging(listCount,  currentPage, limit, "mnlist.do");
		paging.calculator(); //페이징에 필요한 항목들 계산 처리
		
		System.out.println(123);
		ArrayList<Employee> list = employeeService.selectEmployeeList(paging);
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.setViewName("member/admin");
		}else {
			mv.addObject("message", currentPage + "페이지 : 회원 목록 조회 실패.");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	// 직원 등록 
		@RequestMapping(value = "empinsert.do",  method= {RequestMethod.GET,RequestMethod.POST})
		public String employeeInsertMethod(@RequestParam(name= "file", required=false) MultipartFile file,HttpServletRequest request,Employee employee, Model model) {
			logger.info("empinsert.do" + employee.getDepId());
			logger.info("empinsert.do" + employee);
			
			String savePath = request.getSession().getServletContext().getRealPath(
					"resources/member_upfiles");
					
			//첨부파일이 있을 때
			if(!file.isEmpty()) {
				//전송온 파일이름 추출함
				String fileName = file.getOriginalFilename();
				String renameFileName = null;
				
				//저장폴더에는 변경된 이름을 저장 처리함
				//파일 이름바꾸기함 : 년월일시분초.확장자
				if(fileName != null && fileName.length() > 0) {				
					//바꿀 파일명에 대한 문자열 만들기
					renameFileName = FileNameChange.change(fileName, 	"yyyyMMddHHmmss");
					logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
					try {	
						//저장 폴더에 파일명 바꾸기 처리
						file.transferTo(new File(savePath + "\\" + renameFileName));
					
					}catch(Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "첨부파일 저장 실패!");
						return "common/error";
					}
				}  //파일명 바꾸기
				//notice 객체에 첨부파일 정보 저장 처리
				employee.setProfile(fileName);
				employee.setProfilechange(renameFileName);
			} //첨부파일 있을 때		
		
					
			if (employeeService.insertEmployee(employee) > 0) {
				return "redirect:mnlist.do";
			} else {
				model.addAttribute("message", "직원 등록 실패!");
				return "common/error";
			}
		}

		
		// 직원 수정 
				@RequestMapping(value = "empupdate.do",  method= {RequestMethod.GET,RequestMethod.POST})
				public String employeeUpdateMethod(@RequestParam(name= "file", required=false) MultipartFile file,HttpServletRequest request,Employee employee, Model model) {
					logger.info("empinsert.do" + employee.getDepId());
					logger.info("empinsert.do" + employee);
					
					String savePath = request.getSession().getServletContext().getRealPath(
							"resources/member_upfiles");
							
					//첨부파일이 있을 때
					if(!file.isEmpty()) {
						//전송온 파일이름 추출함
						String fileName = file.getOriginalFilename();
						String renameFileName = null;
						
						//저장폴더에는 변경된 이름을 저장 처리함
						//파일 이름바꾸기함 : 년월일시분초.확장자
						if(fileName != null && fileName.length() > 0) {				
							//바꿀 파일명에 대한 문자열 만들기
							renameFileName = FileNameChange.change(fileName, 	"yyyyMMddHHmmss");
							logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
							try {	
								//저장 폴더에 파일명 바꾸기 처리
								file.transferTo(new File(savePath + "\\" + renameFileName));
							
							}catch(Exception e) {
								e.printStackTrace();
								model.addAttribute("message", "첨부파일 저장 실패!");
								return "common/error";
							}
						}  //파일명 바꾸기
						//notice 객체에 첨부파일 정보 저장 처리
						employee.setProfile(fileName);
						employee.setProfilechange(renameFileName);
					} //첨부파일 있을 때		
				
							
					if (employeeService.insertEmployee(employee) > 0) {
						return "redirect:mnlist.do";
					} else {
						model.addAttribute("message", "직원 등록 실패!");
						return "common/error";
					}
				}

		
		
}