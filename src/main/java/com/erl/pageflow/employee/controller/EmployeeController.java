package com.erl.pageflow.employee.controller;

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
import org.springframework.web.servlet.ModelAndView;

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
		public String memberInsertMethod(Employee employee, Model model) {
			logger.info("empinsert.do" + employee.getDepId());
			logger.info("empinsert.do" + employee);
			

			
			if (employeeService.insertEmployee(employee) > 0) {
				return "redirect:mnlist.do";
			} else {
				model.addAttribute("message", "직원 등록 실패!");
				return "common/error";
			}
		}

		
		
}