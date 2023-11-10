package com.spring.biz.view.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class GetBoardListController {

	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardDAO boardDAO, ModelAndView mav) {
		System.out.println(">>> 게시글 전체 목록 보여주기");
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		mav.addObject("boardList", boardList); //Model에 데이터 저장
		mav.setViewName("getBoardList.jsp"); // View 명칭 저장
		
		return mav;
	}

}
