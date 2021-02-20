package com.person;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonServlet
 */
//@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String name = request.getParameter("name");
	int gender = Integer.parseInt(request.getParameter("gender"));
	String color = request.getParameter("color");
	String[] hobby = request.getParameterValues("hobby");
	String[] subject = request.getParameterValues("subject");
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<body>");
	out.println("<ul>");
	
	out.println("<li>이름 : " + name);
	out.println("<br>");
	
	out.println("<li>성별: ");
	if(gender == 0) {
		out.println("남자");
		out.println("<br>");
	}else {
		out.println("여자");
		out.println("<br>");
	}
	
	out.println("<li>색깔 : " + color);
//	if(color.equals("red")) {
		out.println("<body style= \"color:" + color + "\">");
		//out.println("<body style=\"color:"+color+"\">");
//	}else if(color.equals("green")) {
//		out.println("<body style= \"color:green\">");
//	}else if(color.equals("blue")) {
//		out.println("<body style= \"color:blue\">");
//	}else if(color.equals("violet")) {
//		out.println("<body style= \"color:violet\">");
//	}else if(color.equals("cyan")) {
//		out.println("<body style= \"color:cyan\">");
//	}
	out.println("<br>");
	
	
	out.println("<li>취미 : ");
	for(int i = 0; i < hobby.length ; i++) {
		out.println(hobby[i] + "   ");
	}
	out.println("<br>");
	
	out.println("<li>과목 : ");
	for(int i = 0; i < subject.length ; i ++) {
		out.println(subject[i] + "   ");
	}
	
	//확장포문의 예
//	for(String data : subject) {
//		out.println(data);
//	}
	
	out.println("</ul>");
	out.println("</body>");
	out.println("</html>");
	
	}

}
