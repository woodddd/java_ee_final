<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%-- <%
int x = Integer.parseInt(request.getParameter("x"));
int y = Integer.parseInt(request.getParameter("y"));
%> --%>

<%-- <% 
DataDTO dataDTO = new DataDTO();
dataDTO.setX(25);
%> --%>

<jsp:useBean id="dataDTO" class="exam.bean.DataDTO" scope="session" />
<jsp:setProperty property="x" name="dataDTO"/>
<jsp:setProperty property="y" name="dataDTO" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="sumForm" method="get" action="mul.jsp">
<!-- 아래 두줄은 서브밋을 사용하기 위해 현재 페이지에 변수값을 저자해 주는것. 서브밋은 현재 페이지의 데이터를 들고가기 때문. -->
<!-- <input type="hidden" name="x"/>
<input type="hidden" name="y"/> -->
<%-- <%=x %> + <%=y %> = <%=x+y %> --%>

<jsp:getProperty property="x" name="dataDTO"/> + 
<jsp:getProperty property="y" name="dataDTO"/> = 
<%=dataDTO.getX() + dataDTO.getY() %> <!-- 위의 jsp 생성자를 통한 변수는 계산을 하지 못함. 그래서 직접 DTO 에 접근하여 데이터를 계산해야함 -->
<br>

<input type="submit" value="곱구하기">
<%-- input type="button" value="곱구하기" onclick="location.href='mul.jsp?x=<%=x %>&y=<%=y %>'"> --%>
</form>
</body>
</html>