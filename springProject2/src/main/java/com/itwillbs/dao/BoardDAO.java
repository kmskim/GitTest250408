package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Repository
public class BoardDAO {

	//마이바티스 객체 생성
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.itwillbs.mapper.BoardMapper";
	
	//boardDAO.getMaxNum()
	public int getMaxNum() {
		System.out.println("BoardDAO getMaxNum");
		
		return sqlSession.selectOne(namespace + ".getMaxNum");
	}  //getMaxNum
	
	
	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO insertBoard");
		
		boardDTO.setNum(getMaxNum() + 1);
		
		sqlSession.insert(namespace + ".insertBoard", boardDTO);
	}  //insertBoard
	
	public List<BoardDTO> boardList(PageDTO pageDTO){
		System.out.println("BoardDAO boardList");
		
		return sqlSession.selectList(namespace + ".boardList", pageDTO);
	}
	
	public void updateReadCount(int num) {
		System.out.println("BoardDAO updateReadCount");
		
		sqlSession.update(namespace + ".updateReadCount", num);
	}
	
	public BoardDTO contentBoard(int num) {
		System.out.println("BoardDAO contentBoard");
		
		return sqlSession.selectOne(namespace + ".contentBoard", num);
	}
	
	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO updateBoard");
		
		sqlSession.update(namespace + ".updateBoard", boardDTO);
	}
	
	public void deleteBoard(int num) {
		System.out.println("BoardDAO deleteBoard");
		
		sqlSession.delete(namespace + ".deleteBoard", num);
	}
	
	public Integer countBoard() {
		System.out.println("BoardDAO countBoard");
		
		return sqlSession.selectOne(namespace + ".countBoard");
	}
	
	
}  //class
