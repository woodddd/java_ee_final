package member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//데이터
		String loginid = request.getParameter("loginid");
		String loginpwd = request.getParameter("loginpwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		 String name = memberDAO.login(loginid,loginpwd);
		 //로그인을 할 시 아이디가 일치하고 비밀번호가 일치 하여야 하는데, 이것은 디비에 아이디, 
		 //비밀번호를 넘겨준 후, 디비에서 해당 조건을 만족하는 name만 추출해오면 된다.
		 
		 //응답
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.println("<html>");
			out.println("<head>");
			out.println("<title>로그인</title>");
			out.println("<body>");

			if(name == null) {
				out.println("아이디 또는 비밀번호가 맞지 않습니다.");
			}else {
				out.println(name + "님 로그인");
			}
			out.println("</body>");
			out.println("</html>");
	}

}
