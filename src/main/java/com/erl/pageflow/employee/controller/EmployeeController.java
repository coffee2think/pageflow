package com.erl.pageflow.employee.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.service.EmployeeService;
import com.erl.pageflow.employee.model.vo.Employee;
import com.erl.pageflow.employee.model.vo.SearchEmp;

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

	// 마이페이지로 이동
	@RequestMapping("movemypage.do")
	public String moveMyPage() {
		return "member/myPage";
	}

	// 수정페이지로 이동
	@RequestMapping("movemyupdate.do")
	public ModelAndView moveMyUpdatePage(@RequestParam("empId") int empId, ModelAndView mv) {
		Employee employee = employeeService.selectEmployee(empId);
		
		if(employee != null) {
			mv.addObject("employee", employee);
			mv.setViewName("member/myPageUpdate");
		} else {
			mv.addObject("message", "수정페이지로 이동 실패!");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	// 쪽지함 페이지 이동
	@RequestMapping("movemsgbox.do")
	public String moveMsgBoxPage() {
		return "member/message_box";
	}

	// 값---------------------------------------------------------------------

	// 로그인 값전송
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(Employee employee, HttpSession session, SessionStatus status, Model model) {

		Employee loginMember = employeeService.selectEmployee(employee.getEmpId());

    if (loginMember != null && loginMember.getLoginOk().equals("Y")
	/*
	 * && bcryptPasswordEncoder.matches(employee.getEmpPwd(),
	 * loginMember.getEmpPwd())
	 */) {
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
			return "redirect:loginPage.do";
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

		ArrayList<Employee> list = employeeService.selectEmployeeList(paging);
		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("listCount", listCount);
			mv.setViewName("member/admin");
		} else {
			mv.addObject("message", currentPage + "페이지 : 회원 목록 조회 실패.");
			mv.setViewName("common/error");
		}

		return mv;

	}

	// 직원 등록
	@RequestMapping(value = "empinsert.do", method = RequestMethod.POST)
	public String employeeInsertMethod(@RequestParam(name = "upfile", required = false) MultipartFile mfile,
			HttpServletRequest request, Employee employee, Model model) {

		logger.info("empinsert.do" + employee);
		logger.info("empinsert.do" + mfile);

		// 비밀번호 암호화처리
		employee.setEmpPwd(bcryptPasswordEncoder.encode(employee.getEmpPwd()));

		String savePath = request.getSession().getServletContext().getRealPath("resources/member_upfiles");

		// 첨부파일이 있을 때

		if (mfile != null && !mfile.isEmpty()) { // 전송온 파일이름 추출함 String fileName =
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함
			// 파일 이름바꾸기함 : 사번.확장자

			if (fileName != null && fileName.length() > 0) { // 바꿀 파일명에 대한 문자열 만들기
				renameFileName = String.valueOf(employee.getEmpId());
				// 원본 파일의 확장자를 추출해서, 바꿀 파일명에 붙여줌
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				try { // 저장 폴더에 파일명 바꾸기 처리
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			}
			// 파일명 바꾸기 //employee 객체에 첨부파일 정보 저장 처리
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

	// 직원 수정 newempPwd
	@RequestMapping(value = "empupdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeUpdateMethod(
			@RequestParam(name = "newempPwd", required = false) String newempPwd,
			@RequestParam(name = "upfile", required = false) MultipartFile file, HttpServletRequest request,
			Employee employee, Model model) {
		logger.info("empupdate.do:" + employee.getDepId());
		logger.info("empupdate.do" + employee);

		// 새 비밀번호 암호화처리
		if (newempPwd != null) {
			employee.setEmpPwd(bcryptPasswordEncoder.encode(newempPwd));
		}

		String savePath = request.getSession().getServletContext().getRealPath("resources/member_upfiles");
		
		// 첨부파일이 변경된 경우의 처리 --------------------------------------------------------
		// 1. 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		// 또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if (employee.getProfile() != null && !file.isEmpty()) {
			// 저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + employee.getProfile()).delete();
			// notice 안의 파일정보도 제거함
			employee.setProfile(null);
		}

		// 2. 새로운 첨부파일이 있을 때
		if (!file.isEmpty()) {
			// 전송온 파일이름 추출함
			String fileName = file.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함

			// 바꿀 파일명에 대한 문자열 만들기
			if (fileName != null && fileName.length() > 0) { // 바꿀 파일명에 대한 문자열 만들기
				renameFileName = String.valueOf(employee.getEmpId());
				// 원본 파일의 확장자를 추출해서, 바꿀 파일명에 붙여줌
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				try { // 저장 폴더에 파일명 바꾸기 처리
					file.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			} // 파일명 바꾸기
				// employee 객체에 첨부파일 정보 저장 처리
			employee.setProfile(renameFileName);

		} // 첨부파일 있을 때

		if (employeeService.updateEmployee(employee) > 0) {
			// 수정이 성공했다면, 컨트롤러의 다른 메소드를 직접 호출할 수 있음
			// 필요시 값을 전달할 수도 있음 : 쿼리 스트링 사용함
			return "redirect:mnlist.do";
		} else {
			model.addAttribute("message", employee.getEmpId() + "직원 정보 수정 실패!");
			return "common/error";
		}
	}

	@RequestMapping(value = "enamesearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView memberSearch(@RequestParam("searchType") String searchType,
			@RequestParam("keyword") String keyword, @RequestParam(name = "page", required = false) String page,
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
		int listCount = employeeService.selectSearchEmpCount(keyword);

		// 뷰 페이지에 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "msearch.do");
		paging.calculator();

		// 서비스 메소드 호출하고 리턴결과 받기

		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setKeyword(keyword);

		ArrayList<Employee> list = employeeService.selectSearchEmp(search);
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
  
	// 관리자 권한 부여 페이지로 이동 처리
	@RequestMapping("mnauthority.do")
	public ModelAndView moveauthorPage(@RequestParam(name = "page", required = false) String page, ModelAndView mv) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한페이지에 목록 10개씩 출력되게 한다면
		int limit = 10;
		// 회원 목록 전체 갯수 조회해 옴
		int listCount = employeeService.selectEmployeeListCount();
		Paging paging = new Paging(listCount, currentPage, limit, "mnauthority.do");
		paging.calculator(); // 페이징에 필요한 항목들 계산 처리

		System.out.println(123);
		ArrayList<Employee> list = employeeService.selectEmployeeList(paging);
		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.setViewName("member/authority");
		} else {
			mv.addObject("message", currentPage + "페이지 : 회원 목록 조회 실패.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 권한 페이지 검색 처리
	@RequestMapping(value = "authsearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView authSearch(HttpServletRequest request, 
			@RequestParam("searchType") String searchType,
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
		
		String[] keywords = request.getParameterValues("keyword");
		logger.info(keywords[0].length() + "," +  keywords[1].length() );
		
		Employee employee = null;
		if(keywords.length == 2){
			employee = new Employee();
			employee.setDepName(keywords[0].trim());
			employee.setPosName(keywords[1].trim());
			logger.info("1.employee: " + employee );
		}
		
		String keyword = null;
		if(keywords.length == 1){
			keyword = keywords[0];
			if(keyword.contains(",")) {
				keywords = keyword.split(",");
				employee = new Employee();
				employee.setDepName(keywords[0].trim());
				employee.setPosName(keywords[1].trim());
				logger.info("2.employee: " + employee );
			}
		}
		if((employee.getDepName() == null || employee.getDepName().length() == 0) 
				&& (employee.getPosName() == null || employee.getPosName().length() == 0)) {
			mv.addObject("message",  "검색에 대한 부서명 직책명 중 하나는 반드시 입력하십시오");
			mv.setViewName("common/error");
			
			return mv;
		}

		// 총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
		int listCount = 0;
		if((employee.getDepName() != null && employee.getDepName().length() > 0 ) 
				&& (employee.getPosName() == null || employee.getPosName().length() == 0)) {
			listCount = employeeService.selectSearchDeptCount(employee.getDepName());
		}	
		
		if((employee.getDepName() == null || employee.getDepName().length() == 0) && (employee.getPosName() != null || employee.getPosName().length() > 0)) {
			listCount = employeeService.selectSearchPosCount(employee.getPosName());
		}
		if((employee.getDepName() != null && employee.getDepName().length() > 0) 
					&& (employee.getPosName() != null && employee.getPosName().length() > 0)) {
			listCount = employeeService.selectSearchDeptPosCount(employee);
		}
		

		// 뷰 페이지에 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "authsearch.do");
		paging.calculator();

		// 서비스 메소드 호출하고 리턴결과 받기

		Search search = new Search();
		search.setSearchType(searchType);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Employee> list = null; 
		if((employee.getDepName() != null && employee.getDepName().length() > 0 ) 
				&& (employee.getPosName() == null || employee.getPosName().length() == 0)) {
			search.setKeyword(employee.getDepName());
			list = employeeService.selectSearchDept(search);
		}	
		
		if((employee.getDepName() == null || employee.getDepName().length() == 0) 
				&& (employee.getPosName() != null || employee.getPosName().length() > 0)) {
			search.setKeyword(employee.getPosName());
			list = employeeService.selectSearchPos(search);
		}
		if((employee.getDepName() != null && employee.getDepName().length() > 0) 
					&& (employee.getPosName() != null && employee.getPosName().length() > 0)) {
			SearchEmp searchEmp = new SearchEmp();
			searchEmp.setStartRow(search.getStartRow());
			searchEmp.setEndRow(search.getEndRow());
			searchEmp.setDeptName(employee.getDepName());
			searchEmp.setPosName(employee.getPosName());
			list = employeeService.selectSearchDeptPos(searchEmp);
			search.setKeyword(keywords[0] + "," + keywords[1] );
		}
		
		
		// 받은 결과에 따라 성공/실패 페이지 내보내기
		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currnetPage", currentPage);
			mv.addObject("limit", limit);
			mv.addObject("searchType", searchType);
			mv.addObject("keyword", search.getKeyword());
			mv.setViewName("member/authority");
		} else {
			mv.addObject("message", searchType + "에 대한 " + keyword + "검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 관리자용 : 회원 로그인 제한/가능 처리용 메소드
	@RequestMapping("loginok.do")
	public String changeLoginOKMethod(Employee employee, Model model) {
		logger.info("loginok.do : " + employee);
		if (employeeService.updateLoginOK(employee) > 0) {
			return "redirect:mnauthority.do";
		} else {
			model.addAttribute("message", "로그인 제한/허용 처리 오류 발생!");
			return "common/error";
		}
	}

	// 관리자용 : 관리자 권한 부여/해제 처리용 메소드
	@RequestMapping("adminyn.do")
	public String changeAdminYNMethod(Employee employee, Model model) {
		logger.info("adminyn.do : " + employee);
		if (employeeService.updateAdminYN(employee) > 0) {
			return "redirect:mnauthority.do";
		} else {
			model.addAttribute("message", "관리자 권한 부여/해제 처리 오류 발생!");
			return "common/error";
		}
	}
	
	// 퇴사 요청 처리용
	@RequestMapping("empdelete.do")
	public ModelAndView employeeleave(@RequestParam("empId") int empId, ModelAndView mv) {

		int result = employeeService.updateEmpLeave(empId);

		if (result > 0) {
			
			mv.setViewName("redirect:mnlist.do");
		} else {
			mv.addObject("message", empId + "퇴사 처리 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}
	
	// 내정보 수정
	@RequestMapping(value = "myupdate.do", method = RequestMethod.POST)
	public String myUpdateMethod(Employee employee, Model model, @RequestParam("origin_emppwd") String originEmpPwd) throws IOException {
	    logger.info("myupdate.do : " + employee);
	
	    //새로운 암호가 전송이 왔다면 패스워드 암호화 처리함
		String empPwd = employee.getEmpPwd().trim();
		if(empPwd != null && empPwd.length() > 0) {
			//암호화된 기존의 패스워드와 새로운 패스워드를 비교해서 다른 값이면
			if(!this.bcryptPasswordEncoder.matches(empPwd, originEmpPwd)) {
				//member 에 새로운 패스워드를 암호화해서 기록함
				employee.setEmpPwd(this.bcryptPasswordEncoder.encode(empPwd));
			} else {
				//기존 패스워드면 원래 암호화된 패스워드가 적용됨
				employee.setEmpPwd(originEmpPwd);
			}
		}
		
		if(employeeService.myUpdateInfo2(employee) > 0) {
			//수정이 성공했다면 컨트롤러의 다른 메소드를 직접 호출할 수 있음
			//필요시 값을 전달할 수도 있음 : 쿼리 스트링 사용함
			return "redirect:movemypage.do?empId=" + employee.getEmpId();
		} else {
			model.addAttribute("message", employee.getEmpId() + " 회원정보 수정 실패!");
			return "common/error";
		}
	}
}