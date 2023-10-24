package com.erl.pageflow.employee.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.service.EmployeeService;
import com.erl.pageflow.employee.model.vo.Employee;

@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	// 뷰
	// ---------------------------------------------------------------------------------
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

	/*
	 * @RequestMapping("idchk.do") public String idck() { return "member/emp_input";
	 * }
	 */

	@RequestMapping("empmoveupdate.do")
	public ModelAndView moveUpdatePage(@RequestParam("empId") int empId, ModelAndView mv) {

		Employee employee = employeeService.selectEmployee(empId);

		if (employee != null) {
			mv.addObject("employee", employee);
			mv.setViewName("member/emp_update");
		} else {
			mv.addObject("message", "수정페이지로 이동 실패");
			mv.setViewName("common/error");
		}

		return mv;

	}
	
	//마이페이지로 이동
	@RequestMapping("movemypage.do")
	public String moveMyPage() {
		return "member/myPage";
	}
	
	//수정페이지로 이동
	@RequestMapping("movemyupdate.do")
	public String moveMyUpdatePage() {
		return "member/myPageUpdate";
	}
	
	
	// 값---------------------------------------------------------------------

	// 로그인 값전송
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(Employee employee, HttpSession session, SessionStatus status, Model model) {

		Employee loginMember = employeeService.selectEmployee(employee.getEmpId());

		if (loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			status.setComplete();
			return "common/main";
		} else {
			model.addAttribute("message", "로그인 실패");
			return "common/error";
		}
	}

	// 로그아웃 값전송
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			return "common/main";
		} else {
			model.addAttribute("message", "로그아웃 실패");
			return "common/error";
		}
	}

	// 회원 리스트 조회

	@RequestMapping("mnlist.do")
	public ModelAndView memberListViewMethod(@RequestParam(name = "page", required = false) String page,
			ModelAndView mv) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한페이지에 목록 10개씩 출력되게 한다면
		int limit = 10;
		// 회원 목록 전체 갯수 조회해 옴
		int listCount = employeeService.selectEmployeeListCount();
		Paging paging = new Paging(listCount, currentPage, limit, "mnlist.do");
		paging.calculator(); // 페이징에 필요한 항목들 계산 처리

		System.out.println(123);
		ArrayList<Employee> list = employeeService.selectEmployeeList(paging);
		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.setViewName("member/admin");
		} else {
			mv.addObject("message", currentPage + "페이지 : 회원 목록 조회 실패.");
			mv.setViewName("common/error");
		}

		return mv;

	}

	// 직원 등록
	@RequestMapping(value = "empinsert.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeInsertMethod(@RequestParam(name = "file", required = false) MultipartFile file,
			HttpServletRequest request, Employee employee, Model model) {
		logger.info("empinsert.do" + employee.getDepId());
		logger.info("empinsert.do" + employee);
		logger.info("empinsert.do" + file);

		// 비밀번호 암호화처리
		employee.setEmpPwd(bcryptPasswordEncoder.encode(employee.getEmpPwd()));

		String savePath = request.getSession().getServletContext().getRealPath("resources/member_upfiles");

		// 첨부파일이 있을 때

		if (file != null && !file.isEmpty()) { // 전송온 파일이름 추출함 String fileName =
			String fileName = file.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함
			// 파일 이름바꾸기함 : 년월일시분초.확장자 if(fileName != null &&
			if (fileName != null && fileName.length() > 0) { // 바꿀 파일명에 대한 문자열 만들기
				renameFileName = String.valueOf(employee.getEmpId());
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				try { // 저장 폴더에 파일명 바꾸기 처리
					file.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			}
			// 파일명 바꾸기 //notice 객체에 첨부파일 정보 저장 처리
			employee.setProfile(renameFileName);

		} // 첨부파일 있을 때

		if (employeeService.insertEmployee(employee) > 0) {
			return "redirect:mnlist.do";
		} else {
			model.addAttribute("message", "직원 등록 실패!");
			return "common/error";
		}
	}

	// ajax 통신으로 아이디 중복 확인 요청 처리용 메소드
	@RequestMapping(value = "idchk.do", method = RequestMethod.POST)
	public void dupCheckIdMethod(@RequestParam("empid") int empid, HttpServletResponse response) throws IOException {
		// @RequestParam("전송온이름") 자료형 값저장변수명
		// 메소드의 매개변수에 사용하는 어노테이션임 아래의 코드와 같은 기능을 수행
		// String userid = request.getParameter("userid");

		int idCount = employeeService.selectCheckId(empid);

		String returnStr = null;
		if (idCount == 0) {
			returnStr = "ok";
		} else {
			returnStr = "dup";
		}

		// response 를 이용해서 클라이언트와 출력스트림을 열어서 값 보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}

	// 직원 수정
	@RequestMapping(value = "empupdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeUpdateMethod(@RequestParam(name = "deleteFlag", required = false) String delFlag,
			@RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request,
			Employee employee, Model model) {
		logger.info("empinsert.do:" + employee.getDepId());
		logger.info("empinsert.do" + employee);

		String savePath = request.getSession().getServletContext().getRealPath("resources/member_upfiles");

		// 첨부파일이 변경된 경우의 처리 --------------------------------------------------------
		// 1. 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		// 또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if (employee.getProfile() != null && (delFlag != null && delFlag.equals("yes")) || !file.isEmpty()) {
			// 저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + employee.getProfile()).delete();
			// notice 안의 파일정보도 제거함
			employee.setProfile(savePath);

		}

		// 2. 새로운 첨부파일이 있을 때
		if (!file.isEmpty()) {
			// 전송온 파일이름 추출함
			String fileName = file.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함
			// 파일 이름바꾸기함 : 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				try {
					// 저장 폴더에 파일명 바꾸기 처리
					file.transferTo(new File(savePath + "\\" + renameFileName));

				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			} // 파일명 바꾸기
				// notice 객체에 첨부파일 정보 저장 처리
			employee.setProfile(fileName);

		} // 첨부파일 있을 때

		if (employeeService.updateEmployee(employee) > 0) {
			// 수정이 성공했다면, 컨트롤러의 다른 메소드를 직접 호출할 수 있음
			// 필요시 값을 전달할 수도 있음 : 쿼리 스트링 사용함
			return "redirect:mnlist.do?empId=" + employee.getEmpId();
		} else {
			model.addAttribute("message", employee.getEmpId() + "직원 정보 수정 실패!");
			return "common/error";
		}
	}

	@RequestMapping(value = "msearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView memberSearch(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, ModelAndView mv) {
		// 검색결과에 대한 페이징 처리
		// 출력할 페이지 지정
		int currentPage = 1;
		// 전송온 페이지 값이 있다면 추출함
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지당 출력할 목록 갯수 지정
		int limit = 10;
		// 전송 온 limit 값이 있다면
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
		int listCount = 0;
		switch (searchType) {
		case "emp":
			listCount = employeeService.selectSearchEmpCount(keyword);
			break;
		case "dept":
			listCount = employeeService.selectSearchDeptCount(keyword);
			break;
		}

		// 뷰 페이지에 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "msearch.do");
		paging.calculator();

		// 서비스 메소드 호출하고 리턴결과 받기
		ArrayList<Employee> list = null;
		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		switch (searchType) {
		case "emp":
			search.setKeyword(keyword);
			list = employeeService.selectSearchEmp(search);
			break;
		case "dept":
			search.setKeyword(keyword);
			list = employeeService.selectSearchDept(search);
			break;
		}

		// 받은 결과에 따라 성공/실패 페이지 내보내기
		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currnetPage", currentPage);
			mv.addObject("limit", limit);
			mv.addObject("searchType", searchType);
			mv.addObject("keyword", keyword);
			
			mv.setViewName("member/admin");
		} else {
			mv.addObject("message", searchType + "에 대한 " + keyword + "검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}
  
  // 내정보 수정
	@RequestMapping(value = "myupdate.do", method = RequestMethod.POST)
	public void myUpdateMethod(Employee employee, HttpServletResponse response) throws IOException {
    logger.info("myupdate.do : " + employee);

    String returnStr = null;
    if(employeeService.myUpdateInfo(employee) > 0) {
      returnStr = "success";
    } else {
      returnStr = "fail";
    }
    
    // response를 이용해서 클라이언트와 출력 스트림을 열어서 값 보냄
    response.setContentType("text/html; charset=utf-8");
    PrintWriter out = response.getWriter();
    out.append(returnStr);
    out.flush();
    out.close();
  }
}