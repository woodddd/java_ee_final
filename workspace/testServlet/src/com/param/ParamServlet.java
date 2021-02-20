package com.param;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParamServlet
 */
//@WebServlet("/param")
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
		String name = request.getParameter("name");//웹에서 이름으로 된 데이터를 읽어와라
		int age = Integer.parseInt(request.getParameter("age")); //주소를 통해서 넘어온 것은 모두 스트링 타입이다!
		
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(name + "님의 나이는" + age + "살 이므로 ");
		if(age > 20) out.println("성인 입니다.");
		else out.println("청소년 입니다.");
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
