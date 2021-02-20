
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.bean.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%@page import="java.net.URLEncoder"%>
    
<%
//데이터
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

//DB
MemberDAO memberDAO = MemberDAO.getInstance();
MemberDTO memberDTO = memberDAO.login(id,pwd);

/* //쿠키사용시
String name = memberDTO.getName();
String email = memberDTO.getEmail1()+"@"+memberDTO.getEmail2();
 */

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(memberDTO==null){ 
	response.sendRedirect("loginFail.jsp"); /* <!-- 페이지이동 -->  */
}else{
	//response.sendRedirect("loginOk.jsp?name=" + URLEncoder.encode(name,"UTF-8"));
	
	//쿠키
	
	//Cookie cookie = new Cookie("쿠키명","값");
	
	/* Cookie cookie = new Cookie("memName", name); //이름을 쿠키에 담아서 보내겠다.
	cookie.setMaxAge(30*60);//초단위로 움직인다.
	response.addCookie(cookie); // 클라이언트에 저장.

	Cookie cookie2 = new Cookie("memId", id);
	cookie.setMaxAge(30*60);
	response.addCookie(cookie2); */
			
	//세션
	/* HttpSession session = request.getSession(); session 은 서블릿 내장객체로 이미 생성이 되어 있기 때문에 에러가 발생한다. */
	session.setAttribute("memName",memberDTO.getName());
	session.setAttribute("memId",id);
	session.setAttribute("memEmail",memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
	
	/* session.setAttribute("memDTO", memberDTO); *//* 모든 자료의 값을 다 가져감 */
	
	
	response.sendRedirect("loginOk.jsp");
	
} %>

</body>
</html>