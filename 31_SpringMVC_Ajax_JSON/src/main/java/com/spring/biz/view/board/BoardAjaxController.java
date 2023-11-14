package com.spring.biz.view.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;

//@Controller
@RestController // 클래스 내의 요청처리 결과가 모두 @ResponseBody 처리됨!
public class BoardAjaxController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getJsonBoardList.do") // 데이터 목록 boardlist
	@ResponseBody 
	//@ResponseBody : response 응답객체의 body영역에 데이터 전달 
	public List<BoardVO> getAjaxBoardList(BoardVO vo) {
		System.out.println("====== BoardAjaxController.getAjaxBoardList() 실행");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("boardList : " + boardList);
		return boardList;
	}
	
	// 특정 데이터 하나만 검색
	@RequestMapping("/getJsonBoard.do") // 데이터 하나니까 board
	//@ResponseBody
	public BoardVO getAjaxBoard(@RequestBody BoardVO vo) { 
		//@Requestbody : 요청객체에 body영역에 있는 데이터를 가져다가 매칭시켜줌!!! post방식일 때 전달데이터 처리용으로 쓴다!
		System.out.println("====== BoardAjaxController.getAjaxBoard()실행");
		System.out.println("vo : " + vo); // 파라미터 값 잘 담겼는지 확인
		
		BoardVO board = boardService.getBoard(vo);
		System.out.println("board : " + board); // 처리된 결과값 확인
	
		return board; //boardVO의 데이터 타입을 리턴!
	}
	
}
