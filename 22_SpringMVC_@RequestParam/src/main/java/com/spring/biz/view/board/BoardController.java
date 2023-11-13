package com.spring.biz.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class BoardController {

	//리턴타입 : ModelAndView ---> String
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println(">>> 게시글 상세 보여주기");
		System.out.println("vo : " + vo);
		
		BoardVO board = boardDAO.getBoard(vo);
		
//		mav.addObject("board", board);
//		mav.setViewName("getBoard.jsp");
		
		model.addAttribute("board", board);
		
		return "getBoard.jsp";
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDAO boardDAO, Model model,
			@RequestParam(value= "searchCondition", defaultValue= "TITLE", required= false) 
			//searchCondition이라는 파라미터명은 밑에 String searchCondition값으로 들어간다.
			// required= false --> 필수적으로 넘어가야 하는 파라미터 라는 뜻 (값이 안 넘어와도 오류 발생 X)
			String condition,
			@RequestParam(value="searchKeyword", defaultValue="", required = false)
			String keyword) {
		System.out.println(">>> 게시글 전체 목록 보여주기");
		System.out.println("> condition : " + condition);
		System.out.println("> keyword : " + keyword);
		
		//List<BoardVO> boardList = boardDAO.getBoardList();
		List<BoardVO> boardList = boardDAO.getBoardList(condition, keyword);
		
		//mav.addObject("boardList", boardList); //Model에 데이터 저장
		//mav.setViewName("getBoardList.jsp"); // View 명칭 저장
		
		model.addAttribute("boardList", boardList); //Model에 데이터 저장
		
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 입력");
		System.out.println("vo : " + vo);
		
		boardDAO.insertBoard(vo);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 수정");
		System.out.println("vo : " + vo);
		
		boardDAO.updateBoard(vo);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 삭제");
		System.out.println("vo : " + vo);
		
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}
}
