package com.spring.biz.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;

/* 
@SessionAttributes : 같은 컨트롤러에서 모델객체 공유해서 사용(session)
	단, 현재(동일) 컨트롤러에서만 사용 가능
	사용완료되면 SessionStatus 객체의 setComplete() 메소드 사용해서 해제
@SessionAttribute : HttpSession 에 데이터 저장 및 읽기
*/
@Controller
@SessionAttributes("board")
public class BoardController {
	//@Autowired
	private BoardService boardService;
	
	public BoardController() {
		System.out.println("===========> BoardController() 객체 생성");
	}
	
	@Autowired
	public BoardController(BoardService boardService) {
		System.out.println("===========> BoardController(boardService) 객체 생성");
		System.out.println("::: BoardService boardService : " + boardService);
		this.boardService = boardService;
	}
	
	// 메소드 선언부에 선언된 @ModelAttribute 는 리턴된 데이터를 VIEW 에 전달
	// @ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행
	// 뷰에 전달될 때 설정된 명칭(예: conditionMap)
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	//리턴타입 : ModelAndView ---> String
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 상세 보여주기");
		System.out.println("vo : " + vo);

		BoardVO board = boardService.getBoard(vo);
		System.out.println("board : " + board);
		
		model.addAttribute("board", board); //Model 객체 사용 View로 데이터 전달
		
		return "getBoard.jsp";
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 전체 목록 보여주기");
		System.out.println("vo : " + vo);
		
		//List<BoardVO> boardList = boardDAO.getBoardList();
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		model.addAttribute("boardList", boardList); //Model에 데이터 저장
		
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println(">>> 게시글 입력");
		System.out.println("vo : " + vo);
		
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println("> uploadFile : " + uploadFile);
		
		if (uploadFile == null) {
			System.out.println("::: uploadFile 파라미터가 전달되지 않은 경우");
		} else if (uploadFile.isEmpty()) {
			System.out.println("::: 전달받은 파일 데이터가 없는 경우");
		} else { //업로드 파일이 존재하는 경우
			System.out.println("uploadFile.isEmpty() : " + uploadFile.isEmpty());
			//원본파일명 구하기
			String filename = uploadFile.getOriginalFilename();
			System.out.println("::: 원본파일명 : " + filename);
			String savedFilename = UUID.randomUUID().toString();
			System.out.println("::: 저장파일명 : " + savedFilename);
			
			//물리적 파일 복사
			//uploadFile.transferTo(new File("C:/MyStudy/temp/" + savedFilename));
			String destPathFile = "C:/MyStudy/temp/" + savedFilename;
			uploadFile.transferTo(new File(destPathFile));
		}
		
		boardService.insertBoard(vo);
		
		return "getBoardList.do";
	}
	
	// @ModelAttribute("board") : @SessionAttributes 설정으로 존재하는 "board" 데이터 사용
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println(">>> 게시글 수정");
		System.out.println("vo : " + vo);
		
		boardService.updateBoard(vo);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, SessionStatus sessionStatus) {
		System.out.println(">>> 게시글 삭제");
		System.out.println("vo : " + vo);
		
		boardService.deleteBoard(vo);
		sessionStatus.setComplete();
		
		return "getBoardList.do";
	}
}
