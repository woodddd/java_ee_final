<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sql:query var="rs" dataSource="jdbc/oracle">
	select * from usertable where id='${param.id }'
</sql:query>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form method="post" action="jstlModify.jsp">
 <%-- <c:forEach var="row" items="${rs.rowByIndex }"> --%>
 <c:forEach var="row" items="${rs.rows }">
 <table border="1" cellpadding="0" cellspacing="1">
  <tr>
   <td width="80" align="center">이름</td>
   <td><input type="text" name="name" value="${row.name }" size="25"></td>
  </tr>
  
  <tr>
   <td align="center">아이디</td>
   <td><input type="text" name="id" value="${row.id }" size="25" readonly></td>
  </tr>
  
  <tr>
   <td align="center">비밀번호</td>
   <td>
   <%-- <input type="password" name="pwd" value="${row[2] }" size="25"> --%>
   <input type="password" name="pwd" value="${row.pwd }" size="25">
   </td>
  </tr>
  
  <tr>
   <td colspan="2" align="center">
    <input type="submit" value="수정" >
    <input type="button" value="목록" onclick="location.href='jstlList.jsp'">
   </td>
  </tr>
 </table>
 
 </c:forEach>
 </form>
</body>
</html>