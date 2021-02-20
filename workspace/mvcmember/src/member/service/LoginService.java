package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO memberDTO = memberDAO.login(id,pwd);
		
		//응답
		if(memberDTO == null) {
			return "/member/loginFail.jsp";
		}else {
			//자바에서는 세션이 없다.
			// 그래서 세션을 받아와야한다
			HttpSession session = request.getSession();//세션생성
			session.setAttribute("memName",memberDTO.getName());
			session.setAttribute("memId",id);
			session.setAttribute("memEmail",memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
			
			session.setAttribute("memberDTO", memberDTO);
			
			return "/member/loginOk.jsp";
		}
	}

}
