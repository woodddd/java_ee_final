package member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;

public class CheckPostService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		//데이터
		//request.setCharacterEncoding("UTF-8");
		//서블릿 파일에서 한글변환을 하도록 작성하였기 때문에 이제 자바에서 UTF-8을 걸어줄 필요가 없어졌음

		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");
		//System.out.println(sido+", "+sigungu+", "+roadname);

		//DB
		List<ZipcodeDTO> list = null;
		if(sido!=null && roadname!=null){
			MemberDAO memberDAO = MemberDAO.getInstance();
			list = memberDAO.getZipcodeList(sido, sigungu, roadname);
		}
		
		
		//응답
		request.setAttribute("list", list);
		return "/member/checkPost.jsp";
	}

	
}
