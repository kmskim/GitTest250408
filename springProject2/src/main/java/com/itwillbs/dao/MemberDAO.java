package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import com.itwillbs.domain.MemberDTO;

@Repository
public class MemberDAO {
	
//	mybatis 객체 생성 자동으로 주입 
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwillbs.mapper.MemberMapper";
	
	//리턴할 형 없음 insertMember(MebmerDTO memberDTO) 메서드 정의 
	public void insertMember(MemberDTO memberDTO){
		System.out.println("MemberDAO insertMember");
		
		//insert(sql 구문, 데이터);
		sqlSession.insert(namespace + ".insertMember", memberDTO);
		
	}  //insertMember
	
	//return sqlSession.selectOne(sql구분이름, memberDTO);
	
	public MemberDTO loginMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO loginMember");
		
		return sqlSession.selectOne(namespace + ".loginMember", memberDTO);
	}  //loginMember
	
	
	public MemberDTO infoMember(String id) {
		System.out.println("MemberDAO infoMember");
		
		return sqlSession.selectOne(namespace + ".infoMember", id);
	}  //infoMember
	
	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO updateMember");
		
		sqlSession.update(namespace + ".updateMember", memberDTO);
		
	}  //updateMember
	
	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO deleteMember");
		
		sqlSession.delete(namespace + ".deleteMember", memberDTO);
	}  //deleteMember
	
	public List<MemberDTO> listMember() {
		System.out.println("MemberDTO listMember");
		
		return sqlSession.selectList(namespace + ".listMember");
	}  //listMember
	
	
	
	
	
	
	
	
	
	
	
}
