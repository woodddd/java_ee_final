package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

//답글쓰기
public class BoardReplyService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int pseq = Integer.parseInt(request.getParameter("pseq"));//원글번호
		int pg = Integer.parseInt(request.getParameter("pg"));//원글이 있는 페이지 번호
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("memName");
		String id = (String)session.getAttribute("memId");
		String email = (String)session.getAttribute("memEmail");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("id", id);
		map.put("email", email);
		map.put("subject", subject);
		map.put("content", content);
		map.put("pseq",pseq+"");//원글번호
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardReply(map);
		
		//응답
		request.setAttribute("pg", pg);//답글쓰기를 누르면 다시 원래 있던 pg로 돌아가기 위해서 pg를 넘겨야한다.
		return "/board/boardReply.jsp";
	}

}
