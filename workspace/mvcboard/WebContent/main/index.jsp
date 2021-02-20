<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">*** 메인화면 ***</h3>
<hr>

<!-- 세션이 있을 경우 -->
<c:if test="${sessionScope.memId == null }">
<a href="/mvcboard/member/loginForm.do">로그인</a><br>
</c:if>

<!-- 세션이 없을 경우 -->
<c:if test="${sessionScope.memId != null }">
<a href="/mvcboard/member/logout.do">로그아웃</a><br>
<a href="/mvcboard/board/boardWriteForm.do">글쓰기</a><br>
</c:if>
<a href="/mvcboard/board/boardList.do?pg=1">목록</a><br>

</body>
</html>