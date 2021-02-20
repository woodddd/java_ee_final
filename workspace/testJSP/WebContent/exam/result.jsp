<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

int x = Integer.parseInt(request.getParameter("x"));
int y = Integer.parseInt(request.getParameter("y"));
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result</title>
</head>
<body>
<%=x %> + <%=y %> = <%=x+y %><br>
<%=x %> - <%=y %> = <%=x-y %><br>
<%=x %> * <%=y %> = <%=x*y %><br>
<%=x %> / <%=y %> = <%=x/y %><br>

<input type="button" value="뒤로" onclick="location.href = 'input.jsp'"> <!-- 같은 exam 폴더 안에 있기 떄문에 경로를 다 써줄 필요가 없음 -->
<input type="button" value="뒤로" onclick="history.back()"> <!-- 이전 데이터를 기억하고 있음 -->
<input type="button" value="뒤로" onclick="history.go(-1)"> <!-- 실행 결과 1개전으로 이동 -->
</body>
</html>