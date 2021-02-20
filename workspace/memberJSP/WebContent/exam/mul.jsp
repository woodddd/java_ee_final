<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%
int x = Integer.parseInt(request.getParameter("x"));
int y = Integer.parseInt(request.getParameter("y"));
%> --%>
<jsp:useBean id="dataDTO" class="exam.bean.DataDTO" scope="session"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <%=x %> * <%=y %> = <%=x*y %> --%>
<jsp:getProperty property="x" name="dataDTO"/> *
<jsp:getProperty property="y" name="dataDTO"/> =
<%=dataDTO.getX() * dataDTO.getY() %>
<br>

<input type="button" value="처음으로" onclick="javascript:history.go(-2)">
</body>
</html>