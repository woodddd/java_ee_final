<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="../image/apple.png" style="float: left; width: 20px; height: 20px; cursor: pointer;" 
		onclick="location.href='../main/index.do'">
${sessionScope.memName }님 로그인<br><br>

	
<input type="button" value="로그아웃" onclick="location.href='/mvcboard/member/logout.do'">
</body>
</html>


