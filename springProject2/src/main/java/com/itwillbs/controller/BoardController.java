package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.MemberDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	
	//객체 생성
	@Inject
	private BoardService boardService;

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		System.out.println("BoardController write");
		//주소 변경 없이 화면 이동 WEB-INF/views/member/insert.jsp (jsp제외 작성)
		return "/board/write";
	}  //write
	
	
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		System.out.println("MemberController writePro");
		//회원가입  
		//패키지 com.itwillbs.service 파일 BoardService
		//리턴할 형 없음 insertBoard(BoardDTO boardDTO) 메서드 정의  
		
		//insertBoard(boardDTO) 메서드 호출
		boardService.insertBoard(boardDTO);
		
		//주소 변경하면서 login 페이지로 이동 
		return "redirect:/board/list";
	}  //writePro
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		System.out.println("MemberController list");
		
		// 한 화면에 보여줄 글 개수 => 10개 설정
		int pageSize = 10;
		// pageNum=1 => pageNum 값 가져오기
		String pageNum = request.getParameter("pageNum");
		// pageNum 없으면 무조건 "1"페이지로 설정
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		// 패키지 com.itwill.domain 파일이름 pageDTO
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		//List<BoardDTO> boardList = listBoard() 메서드 호출
		List<BoardDTO> boardList = boardService.boardList(pageDTO);
		
		// 전체 글 개수 select count(* from board
		int count = boardService.countBoard();
		// 한 화면에 보여줄 페이지 개수
		int pageBlock = 10;
		// 한 화면에서 보여줄 시작하는 페이지 번호
		// currentPage 		pageBlock => 	startPage
		//	 1 ~ 10(0~9)	   10	  =>    (0~9)/10 * 10+1 => 1
		//	11 ~ 20(10~19) 	   10	  =>  (10~19)/10 * 10+1 => 11
		//	21 ~ 30(20~29)	   10	  =>  (20~29)/10 * 10+1 => 21
		int startPage = (currentPage -1 )/pageBlock * pageBlock + 1 ;
		// 한 화면에서 보여줄 끝나는 페이지 번호
		// startPage	pageBlock	=>	endPage
		//	    1			10		=>	   10
		//	   11			10		=>	   20
		//	   21			10		=>	   30
		int endPage = startPage + pageBlock - 1;
		// 전체 페이지 개수 55개 글 / 10개 글 화면에 보이기 + (나머지 글 없으면 = 0, 나머지 글이 있으면 +1)
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);

		//model에 boardList, boardList 담아서
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		return "/board/list";
	} // list()
	
	// http://localhost:8080/myweb2/board/contant?num=1 method="get"
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String contantBoard(BoardDTO boardDTO, Model model) {
		System.out.println("MemberController contantBoard");
		
		// BoardDTO, HttpServletRequest
		int num = boardDTO.getNum();
		
		// updateReadCount() 호출
		boardService.updateReadCount(num);
		
		// BoardDTO boardDTO = contantBoard(num) 메서드 호출
		boardDTO = boardService.contentBoard(num);
		
		// model에 "boardDTO", boardDTO 담아서
		model.addAttribute("boardDTO", boardDTO);
		
		// 주소변경없이 화면이동 WEB-INF/views/board/contant.jsp
		return "/board/content";
	} // contentBoard()
	
	// http://localhost:8080/myweb2/board/update?num=1 method="get"
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String contantBoard(HttpServletRequest request, Model model) {
		
		// HttpServletRequest request
		// int num = Integer.parseInt(request.getParameter("num"));
		int num = Integer.parseInt(request.getParameter("num"));
		// BoardDTO boardDTO = contentBoaerd(num)메서드 호출
		BoardDTO boardDTO = boardService.contentBoard(num);
		// model에 "boardDTO", boardDTO 담아서
		model.addAttribute("boardDTO", boardDTO);
		
		// 주소변경없이 화면이동 WEB-INF/views/board/update.jsp
		return "/board/update";
	}
	
	// http://localhost:8080/myweb2/board/updatePro method="post"
	@RequestMapping(value = "/board/updatePro", method = RequestMethod.POST)
	public String contantBoard(BoardDTO boardDTO) {
		
		// BoardDTO boardDTO 받기
		// updateBoard(boardDTO) 메서드 호출
		boardService.updateBoard(boardDTO);
		
		// 주소 변경하면서 /board/list 이동
		return "redirect:/board/list";
	}
	
	
	// http://localhost:8080/myweb2/board/delete?num=1 method="get"
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String deleteBoard(HttpServletRequest request) {
		
		// HttpServletRequest request
		// int num = Integer.parseInt(request.getParameter("num"));
		int num = Integer.parseInt(request.getParameter("num"));
		
		// deleteBoard(num) 메서드 호출
		boardService.deleteBoard(num);
		
		// 주소 변경하면서 /board/list 이동
		return "redirect:/board/list";
	}
	
	
}  //class
