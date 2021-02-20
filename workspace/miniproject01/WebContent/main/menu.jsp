<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.memId == null }">
	<a href="/miniproject01/member/writeForm.do">회원가입</a><br>
	<a href="/miniproject01/member/loginForm.do">로그인</a><br>
</c:if>

<c:if test="${sessionScope.memId != null }">
	<h3>${memName }님 로그인</h3>
	
	<a href="/miniproject01/member/logout.do">로그아웃</a><br>
	<a href="/miniproject01/member/modifyForm.do">회원정보수정</a><br>
	<a href="/miniproject01/board/boardWriteForm.do">글쓰기</a><br>
	<a href="/miniproject01/imageboard/imageboardWriteForm.do">상품 등록</a><br>
</c:if>
	<a href="../board/boardList.do?pg=1">목록</a><br>
	<a href="../imageboard/imageboardList.do?pg=1">상품 목록</a><br>
