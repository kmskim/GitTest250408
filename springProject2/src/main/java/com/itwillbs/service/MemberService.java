package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberDTO;

@Service
public class MemberService {
	
	//MemberDAO 객체 생성 => 멤버변수 정의 => 스프링에서 객체 생성 후 멤버변수 전달(@Inject) 
	//(MemberDAO에 @Repository 붙임) => @Inject가 스프링에서 자동으로 @ 찾아서 객체 생성 후 자동으로 주입(전달) 
	
	@Inject
	private MemberDAO memberDAO;
	
	////리턴할 형 없음 insertMember(MemberDTO memberDTO) 메서드 정의
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberService insertMember");
		//회원가입
		
		//패키지 com.itwillbs.dao 파일 MemberDAO
		
		//리턴할 형 없음, memberDAO(MemberDTO memberDTO)
		
		//insertMember(memberDTO) 메서드 호출 
		memberDAO.insertMember(memberDTO);
		
	}  //insertMember

	//MemberDTO 리턴할형 loginMember(MemberDTO memberDTO) 메서드 저으이
	public MemberDTO loginMember(MemberDTO memberDTO) {
		System.out.println("MemberService loginMember");
	
		//MemberDTO memberDTO2 = loginMember(memberDTO) 메서드 호출 
		return memberDAO.loginMember(memberDTO);
//		return null;
	}  //loginMember
	
	public MemberDTO infoMember(String id){
		System.out.println("MemberService infoMember");
		
		return memberDAO.infoMember(id);
	}  //infoMember
	
	
	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberService updateMember");
		
		memberDAO.updateMember(memberDTO);
	}  //updateMember
	

	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberService deleteMember");
		
		memberDAO.deleteMember(memberDTO);
	}  //deleteMember
	
	
	public List<MemberDTO> listMember() {
		System.out.println("MemberService listMember");
		
		return memberDAO.listMember();
	}  //listMember
	
	
	
	
	
	
	
	
	
	

}
