<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<!-- <form name="loginForm" method="post" action="http://localhost:8080/mvcmember/member/login.do"> -->
<!-- 위의 문장과 동일하다. 하지만 이전과는 다르게 localhost 부분만 지워줄 수 있다. -->
<form name="loginForm" method="post" action="/mvcmember/member/login.do">
<h3>로그인</h3>
<hr>
<table border="1" cellpadding="3" cellspacing="0">
<tr>
	<td align="center">아이디</td>
	<td>
		<input type="text" name="id" size="25">
	</td>
</tr>

<tr>
	<td align="center">비밀번호</td>
	<td>
		<input type="password" name="pwd" size="30">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="button" value="로그인" onclick="checkLogin()"> 
	<input type="button" value="회원가입" 	onclick="location.href='writeForm.do'">
</tr>
</table>
</form>
<script>
function checkLogin(){
	if(document.loginForm.id.value == "")
		alert("아이디를 입력하세요");
	else if (document.loginForm.pwd.value == "")
		alert("비밀번호를 입력하세요");
	else
		document.loginForm.submit();
}
</script>
</body>
</html>