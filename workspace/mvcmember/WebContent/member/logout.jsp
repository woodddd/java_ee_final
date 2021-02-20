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

<script type="text/javascript">
window.onload=function(){/* 현재 이 페이지를 열었을때(읽자마자) */
	alert("로그아웃");
	location.href="../member/loginForm.do";
	/* location.href="/mvcmember/member/loginForm.do";위에문장과 동일하게 사용할 수 있다. */
} 

</script>
</body>
</html>