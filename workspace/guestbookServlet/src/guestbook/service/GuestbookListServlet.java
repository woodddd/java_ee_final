package guestbook.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/GuestbookListServlet")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		//데이터 out.println("<input type=button value=글목록 onclick=location.href='http://localhost:8080/guestbookServlet/GuestbookListServlet?pg=1'>"); 를통해 pg=1 이라는 데이터가 넘어옴
 		//get 방식을 통해 넘어온 데이터는 문자열 데이터임.
 		int pg = Integer.parseInt(request.getParameter("pg"));
 		
 		//페이징 처리 - 한페이지에 게시물 2개씩
 		 int endNum = pg*2;
 		 int startNum = endNum-1;
 		
 		//DB
 		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
 		int totalA=guestbookDAO.getTotalA();//총글수
 		
 			int totalP = totalA - (totalA/2);//총 페이지 수
 			
 		
 		List<GuestbookDTO> list = guestbookDAO.getList(startNum,endNum);
 		//응답
 		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>"); 
		out.println("<head>");
		out.println("<title>글목록</title>");
		out.println("<style>");
		out.println("#currentPaging {color: red; text-decoration underline;}");// 속성중에서 id속성을 찾아라
		out.println("#paging {color: black; text-decoration: none; }");
		out.println("</style>");
		out.println("<body>");
		
		if(list != null) {
			//페이지
			for(int i =1; i <=totalP; i++) {
				if(pg==i) {
					out.println("[<a id=currentPaging href='http://localhost:8080/guestbookServlet/GuestbookListServlet?pg="+i+"'>"+i+"</a>]");
				}else {
					out.println("[<a id=paging href='http://localhost:8080/guestbookServlet/GuestbookListServlet?pg="+i+"'>"+i+"</a>]");
				}
			}//for
			out.println("<br><br>");
			
			
			
			for(GuestbookDTO guestbookDTO : list) {
				out.println("<table border=1>");
				out.println("<tr>");
				out.println("<td width=150 align=center>작성자</td>");
				out.println("<td width=150 align=center>"+guestbookDTO.getName()+"</td>");
				out.println("<td width=150 align=center>작성일</td>");
				out.println("<td width=150 align=center>"+guestbookDTO.getLogtime()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align=center>이메일</td>");
				out.println("<td colspan=3>"+guestbookDTO.getEmail()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align=center>홈페이지</td>");
				out.println("<td colspan=3>"+guestbookDTO.getHomepage()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align=center>제목</td>");
				out.println("<td colspan=3>"+guestbookDTO.getSubject()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align=center>내용</td>");
				out.println("<td colspan=3><pre>"+guestbookDTO.getContent()+"</pre></td>");
				out.println("</tr>");
				out.println("</table>");
			}//for
			
		}//if
		out.println("</body>");
		out.println("</html>");
 	}

}

















