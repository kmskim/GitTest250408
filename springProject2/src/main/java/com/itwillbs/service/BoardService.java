package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardService {

	//BoardDAO 객체 생성
	@Inject
	private BoardDAO boardDAO;
	
	
	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardService insertBoard()");
		
		/*
		if(boardDAO.getMaxNum() == null) {
			boardDTO.setNum(1);
		}else {
			boardDTO.setNum(boardDAO.getMaxNum() + 1);
		}
		*/
		
		//패키지 com.itwillbs.dao 파일 BoardDAO
		//BoardDTO에 담긴 값 name, subject, content
		//num = max(num) + 1
		boardDTO.setNum(boardDAO.getMaxNum() + 1);
		//rdate
		boardDTO.setRdate(new Timestamp(System.currentTimeMillis()));
		//readcount
		boardDTO.setReadcount(0);
		
		boardDAO.insertBoard(boardDTO);
		
	}
	
	public List<BoardDTO> boardList(PageDTO pageDTO){
		System.out.println("BoardService boardList()");
		
		// 10개씩 잘랐을 떄 1페이지 내용 디비에서 가져오기 위해서
		// 시작하는 행 번호 구하기
		// 페이지번호(pageNum) 페이지크기(pageSize) => 시작하는 행번호(startRow)
		// 		  1					10		  => 0 * 10 + 1 => 0 + 1 => 1
		//		  2					10		  => 1 * 10 + 1	=> 10 + 1 => 11
		//		  3					10		  => 2 * 10 + 1	=> 20 + 1 => 21
		int startRow = (pageDTO.getCurrentPage() - 1) * pageDTO.getPageSize() + 1;
		
		// 끝나는 행 번호 구하기
		// 시작하는 행번호(startRow) 페이지크기(pageSize) => 끝나는 행번호(endRow)
		//		  1					10		  	   => 1 + 10 -1 => 10
		//		  11				10		  	   => 11 + 10 -1 => 20
		//		  21				10		  	   => 21 + 10 -1 => 30
		int endRow = pageDTO.getStartRow() + pageDTO.getPageSize() - 1;
		
		// limit #{startRow - 1}, #{pageSize}
		pageDTO.setStartRow(startRow - 1);
		pageDTO.setEndRow(endRow);
		
		return boardDAO.boardList(pageDTO);
		
	}
	
	public void updateReadCount(int num) {
		System.out.println("BoardService updateReadCount()");
		
		boardDAO.updateReadCount(num);
	}
	
	public BoardDTO contentBoard(int num) {
		System.out.println("BoardService contentBoard()");
		
		return boardDAO.contentBoard(num);
	}
	
	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService updateBoard()");
		
		boardDAO.updateBoard(boardDTO);
	}
	
	public void deleteBoard(int num) {
		System.out.println("BoardService updateBoard()");
		
		boardDAO.deleteBoard(num);
	}
	
	public Integer countBoard() {
		System.out.println("BoardService countBoard()");
		
		return boardDAO.countBoard();
	}
	
	
	
	
	
}  //class
