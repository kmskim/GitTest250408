package com.itwillbs.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@Controller
public class MemberController {

	//서블릿 파일 등록 <context: component-scan base-package="com.itwillbs.service">
	//MemberService 객체 생성 => 멤버변수 정의 => 스프링에서 객체 생성 후 멤버변수 전달(@Inject) 
	//(MemberService에 @Service 붙임) => @Inject가 스프링에서 자동으로 @ 찾아서 객체 생성 후 자동으로 주입(전달) 
	@Inject
	private MemberService memberService;
	
	//http://localhost:8080/myweb2/
	//여기에 
	//member/insert 붙이기
	
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		System.out.println("MemberController insert");
		//주소 변경 없이 화면 이동 WEB-INF/views/member/insert.jsp (jsp제외 작성)
		return "/member/insert";
	}  //insert
	
	//http://localhost:8080/myweb2/member/insertPro" method="post"
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		System.out.println("MemberController insertPro");
		//회원가입  
		//패키지 com.itwillbs.service 파일 MemberService
		//리턴할 형 없음 insertMember(MemberDTO memberDTO) 메서드 정의 > MemberService에 
		
		//insertMember(memberDTO) 메서드 호출 
		memberService.insertMember(memberDTO);
		
		//주소 변경하면서 login 페이지로 이동 
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login");
		//주소 변경 없이 화면 이동 WEB-INF/views/member/insert.jsp (jsp제외 작성)
		return "/member/login";
	}  //login
	
	
	@RequestMapping(value= "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController loginPro");
		//로그인
		
		//패키지 com.itwillbs.service 파일 MemberService 
		//MemberDTO 리턴할형 loginMember(MemberDTO memberDTO) 메서드 정의
		//MemberDTO memberDTO2 = loginMember(memberDTO) 메서드 호출 
		MemberDTO memberDTO2 = memberService.loginMember(memberDTO);
		
		//return sqlSession.selectOne(sql구문이름, memberDTO)
		//<select id="loginMember" resultType="com.itwillbs.domain.memberDTO">
	  	//	select * from members
	  	//	where id = #{id} and passwd = #{passwd}  
	    //</select>
		
		System.out.println("memberDTO2 : " + memberDTO2);
		
		if(memberDTO2 != null) {
			System.out.println("아이디, 비밀번호 일치");
			
			//session 로그인 표시값 저장 "id",id
			session.setAttribute("id", memberDTO2.getId());
			return "redirect:/member/main";
			
		}else {
			System.out.println("아이디, 비밀번호 불일치");
			
//			return "redirect:/member/login";
			
			//주소 변경 없이 화면 이동 WEB-INF/views/member/msg.jsp 
			return "/member/msg";
		}

	}  //loginPro
	
	
	
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("MemberController main");
		//주소 변경 없이 화면 이동 WEB-INF/views/member/insert.jsp (jsp제외 작성)
		return "/member/main";
	}  //main
	
	
	
	//http://localhost:8080/myweb2/member/logout method="get"
	//세션값 전체 지우기 invalidate()
	//=> 주소 변경하면서 /member/main 이동
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("MemberController logout");
		
		session.invalidate();
		return "redirect:/member/main";
	}  //logout
	
	
	
	//세션값 받아서 String id 변수에 저장 
	//id에 해당하는 멤버 조회 
	//리턴할형 MemberDTO infoMember(String id) 메서드 정의
	//MemberDTO memberDTO = infoMember(id) 메서드 호출
	//Model.addAttribute("memberDTO", memberDTO)
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info(HttpSession session, Model model) {
		System.out.println("MemberController info");
		
		String id = (String)session.getAttribute("id");
		MemberDTO memberDTO = memberService.infoMember(id);
		
		model.addAttribute("memberDTO", memberDTO);
		return "/member/info";
	}  //info
	
	
	
	//세션값 받아서 String id 변수에 저장 
	//MemberDTO memberDTO = infoMember(id) 메서드 호출
	//Model.addAttribute("memberDTO", memberDTO)
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		System.out.println("MemberController update");
		String id = (String)session.getAttribute("id");
		
		MemberDTO memberDTO = memberService.infoMember(id);
		
		model.addAttribute("memberDTO", memberDTO);
		return "/member/update";
	}  //update
	
	
	
	//request 값을 MemberDTO memberDTO 변수에 받아서
	
	//아이디, 비밀번호 일치
	//리턴할형 없음 updateMember(MemberDTO memberDTO) 메서드 정의 
	//updateMember(memberDTO) 호출
	//주소 변경 하면서 member/main으로 이동 
	
	//아이디, 비밀번호 틀림 
	//주소 변경 없이 화면 이동 member/msg.jsp
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(MemberDTO memberDTO) {
		System.out.println("MemberController updatePro");
		
		MemberDTO memberDTO2 = memberService.loginMember(memberDTO);
		
		if(memberDTO2 != null) {
			memberService.updateMember(memberDTO);
			return "redirect:/member/main";
		}else {
			return "/member/msg";
		}
		
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete(MemberDTO memberDTO) {
		System.out.println("MemberController deldte");
		
		return "/member/delete";
		
	}
	
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("Membercontroller deletePro");
		MemberDTO memberDTO2 = memberService.loginMember(memberDTO);
		
		if(memberDTO2 != null ) {
			
			memberService.deleteMember(memberDTO);
			session.invalidate();  //로그인 표시값 삭제
			return "redirect:/member/main";
		}else {
			
			return "/member/msg";
		}
		
	}  //deletePro
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("MemberController list");
		
		//리턴할형 List<MemberDTO> listMember() 메서드 정의
		//List<MemberDTO> memberList = listMember(id) 메서드 호출 
		//model.addAttribute("memberList", memberList);
		
		List<MemberDTO> memberList = memberService.listMember();
		
		model.addAttribute("memberList", memberList);
		
		return "/member/list";
	}  //list
	
	
	
	
	
	
	
	
	
			
}
