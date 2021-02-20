<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${id }는 사용 가능 <!-- pageScope -> sessionScope -> requestScope 순으로 찾기 때문에 id 만 입력해주어도 문제 없음 -->
<input type="button" value="사용하기" onclick="checkIdClose('${id }')">

<script>
function checkIdClose(id){
	opener.writeForm.id.value = id;
	opener.writeForm.check.value = id;
	window.close();
	opener.writeForm.pwd.focus();
	
}
</script>
</body>
</html>