<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<h3>로그아웃</h3>
<img src="../image/apple.png" style="float: left; width: 20px; height: 20px; cursor: pointer;" 
		onclick="location.href='../main/index.do'">

<script type="text/javascript">
window.onload=function(){/* 현재 이 페이지를 열었을때(읽자마자) */
	alert("로그아웃");
	location.href="/mvcboard/main/index.do";
} 

</script>
</body>
</html>