<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
window.onload=function(){
	alert("글이 저장되었습니다.");
	location.href="boardList.do?pg=${requestScope.pg}"; //답글 - 원글이 있는 페이지의 원글 아래로 답글이 들어가야한다.
}
</script>
</body>
</html>