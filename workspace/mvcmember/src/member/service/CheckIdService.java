package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String id = request.getParameter("id");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean exist = memberDAO.isExistId(id);
		
		
		
		//응답
		
		//id를 보내는 방법 중 다른 방법
		request.setAttribute("id", id);
		//get방식으로 보내는 방법
//		if(exist) {
//			return "/member/checkIdFail.jsp?id=" + id;
//		}else {
//			return "/member/checkIdOk.jsp?id=" + id;
//		}
		if(exist) {
			return "/member/checkIdFail.jsp";
		}else {
			return "/member/checkIdOk.jsp";
		}
		
	}

}
