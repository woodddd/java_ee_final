package board.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//DB
		int endNum = pg*5;
		int startNum = endNum -4;
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> list = boardDAO.boardList(startNum, endNum);
		
		//조회수 - 새로고침 방지
		//로그인 했을 때만 조회수 올리기
		HttpSession session = request.getSession();
		//생성된 세션이 있다면 세션을 가져오고, 세션이 없다면 생성
		
		if(session.getAttribute("memId") != null) {
			Cookie cookie = new Cookie("memHit","0");
			cookie.setMaxAge(30*60);//30분 시간 설정(초단위라 * 60해준것임)
			response.addCookie(cookie);//클라이언트에게 보내기
		}
		
		//페이징 처리
		int totalA = boardDAO.getTotalA();//총글수
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		
		boardPaging.makePagingHTML();
		
		//응답
		request.setAttribute("pg",pg);
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("display", "/board/boardList.jsp");
		
		return "/main/index.jsp";
	}

}
