package board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardViewService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		Cookie[] ar = request.getCookies();
		String check = null;
		if(ar != null) {
			for(int i = 0; i < ar.length ; i++) {
				if(ar[i].getName().equals("memHit")) {
					boardDAO.hitUpdate(seq);
					ar[i].setMaxAge(0);
					response.addCookie(ar[i]);//쿠키가 삭제됐다는걸 클라이언트에게 보내줌
				}//if
			}//for
		}//if
		BoardDTO boardDTO = boardDAO.boardView(seq);
		
		request.setAttribute("pg", pg);
		request.setAttribute("boardDTO", boardDTO);
		
		return "/board/boardView.jsp";
	}

}
