package com.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class calcServlet
 */
//@WebServlet("/calcServlet")
public class CalcServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int x = Integer.parseInt((request.getParameter("X")));
	int y = Integer.parseInt((request.getParameter("Y")));
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<body>");
	out.println(x + " + " + y + " = " + (x+y));
	out.println("<br>");
	out.println(x + " - " + y + " = " + (x-y));
	out.println("<br>");
	out.println(x + " * " + y + " = " + (x*y));
	out.println("<br>");
	out.println(x + " / " + y + " = " + ((double)x/y));
	out.println("<br>");
	
	out.println("</body>");
	out.println("</html>");
	}

}
