<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<sql:update var="su" dataSource="jdbc/oracle"> 
	insert into usertable values('${param.name }','${param.id }','${param.pwd }')
</sql:update><!-- 커넥션 풀을 가져와라. 이름이 jdbc/oracle 인 것을. -->
	<!-- 변수를 달아주면 (var = "변수명") 해당 변수에 실행결과 갯수가 저장이됨 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${su==1 }"><h3>DB에 저장 완료</h3></c:if>
<c:if test="${su==0 }"><h3>DB에 저장 실패</h3></c:if>
<br><br>
<input type="button" value="목록" onclick="location.href='jstlList.jsp'">
</body>
</html>
