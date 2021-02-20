package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void init() { //맨처음에 딱 1번만 호출됨
		System.out.println("init()");
	}

	public void destroy() {//톰캣이 껐다 켜지면 실행됨.
		System.out.println("destroy()");
	}

	//웹 화면에서 새로고침을 실행하게 되면(클라이언트가 요청시마다 호출) doGet 메소드를 실행하게 됨.
										   //요청 처리                                                 //응답처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		
		response.setContentType("text/html;charset=UTF-8");//"text/javascript"로 한다면 문법을 자바스크립트로 알아라. 라는 뜻임
		PrintWriter out = response.getWriter(); //웹으로 나갈 수 있도록 길을 생성
		out.println("<html>");
		out.println("<body>");
		out.println("Hello Servlet!!<br>");//화면에는 현재 구문만 보이게 됨. 태그 안에 있는 것은 화면에 나오지 않음
		out.println("안녕하세요<br>");
		out.println("</body>");
		out.println("</html>");
		
		//1.서블릿 파일 작성
		//2. 서블릿 등록 - Web.xml(Context는 서블릿 파일이 있다는 것을 모르기 때문에)
		
		
	}

}
