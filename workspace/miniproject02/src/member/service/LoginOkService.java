package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

public class LoginOkService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String name = null;
		String id = null;
		
//		id = (String)session.getAttribute("memId");
		
		return "/member/loginOk.jsp";
	}

}
