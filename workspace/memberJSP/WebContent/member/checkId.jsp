<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO" %>


<%
//데이터
String id = request.getParameter("id");

//DB
//MemberDTO memberDTO = new MemberDTO();
//memberDTO.setId(id);

MemberDAO memberDAO = MemberDAO.getInstance(); 
//String checkId = memberDAO.existId(id);//String 방식

boolean exist = memberDAO.isExistId(id); //boolean 방식으로 사용가능

%> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/member/checkId.jsp">
<% if(exist){ %>
	<%=id %>는 사용 불가능<br><br>
	아이디 <input type="text" name="id">
	<input type="submit" value="중복체크">
<%}else{ %>
	<%=id %>는 사용 가능<br><br>
	<input type="button" value="사용하기" onclick="checkIdClose('<%=id %>')">
<%} %>

</form>
<script>
function checkIdClose(id){
	opener.writeForm.id.value = id;
	// 아래의 문장과 동일
	//opener -- 열려있는 창들 중에서
	//opener.document.getElementById("id").value = id;
	opener.writeForm.check.value = id;
	window.close();
	opener.writeForm.pwd.focus();
	
}
</script>
</body>
</html>