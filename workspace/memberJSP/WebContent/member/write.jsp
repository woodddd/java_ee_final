<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.bean.MemberDTO, member.dao.MemberDAO" %> 
    

<%
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="memberDTO" class="member.bean.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />
 
<%--  <jsp:setProperty property="name" name="memberDTO"/>
 <jsp:setProperty property="id" name="memberDTO"/>
 <jsp:setProperty property="pwd" name="memberDTO"/>
 <jsp:setProperty property="gender" name="memberDTO"/>
 <jsp:setProperty property="email1" name="memberDTO"/>
 <jsp:setProperty property="email2" name="memberDTO"/>
 <jsp:setProperty property="tel1" name="memberDTO"/>
 <jsp:setProperty property="tel2" name="memberDTO"/>
 <jsp:setProperty property="tel3" name="memberDTO"/>
 <jsp:setProperty property="zipcode" name="memberDTO"/>
 <jsp:setProperty property="addr1" name="memberDTO"/>
 <jsp:setProperty property="addr2" name="memberDTO"/> --%>


<%
//DB
MemberDAO memberDAO = MemberDAO.getInstance();
int su = memberDAO.write(memberDTO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(su==1) { %>
	회원가입 성공!!<br><br>
	<input type="button" value="로그인" 
	onclick=location.href='http://localhost:8080/memberJSP/member/loginForm.jsp'>
	
<%}else{ %>
	회원가입 실패!!
	
<%} %>
</body>
</html>









