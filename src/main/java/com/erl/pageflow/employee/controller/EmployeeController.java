package com.erl.pageflow.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.erl.pageflow.employee.model.service.EmployeeService;
import com.erl.pageflow.employee.model.vo.Employee;

@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	// �丸 �����ִ� �޼ҵ� ---------------------------------------------------------------------------------
	// �α��� �������� �̵��ϴ� �޼ҵ�
	@RequestMapping(value = "loginPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveLoginPage() {
		return "member/login";
	}
	
	
	
	// ���ް������� ���� �������� ��� �޼ҵ� ---------------------------------------------------------------------
	
	// �α��� ó���� �޼ҵ� (��ȣȭ ó���Ǹ� ���� ����)
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String loginMethod(Employee employee, HttpSession session, SessionStatus status, Model model) {
		logger.info("login.do : " + employee);
		logger.info(employee.getEmpId() + "");
		Employee loginMember = employeeService.selectEmployee(employee.getEmpId());
		logger.info(loginMember + "");
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			status.setComplete();
			return "common/main";
		}else {
			model.addAttribute("message", "�α��� ����!");
			return "common/error";
		}
	}
	
	// �α׾ƿ� ó���� �޼ҵ�
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
			return "common/main";
		}else {
			model.addAttribute("message", "�α��� ������ �������� �ʽ��ϴ�.");
			return "common/error";
		}
	}
}