<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="jstlWrite.jsp">
 <table border="1" cellpadding="0" cellspacing="1">
  <tr>
   <td width="80" align="center">이름</td>
   <td><input type="text" name="name" size="25"></td>
  </tr>
   
  <tr>
   <td align="center">아이디</td>
   <td><input type="text" name="id" size="25"></td>
  </tr>
  
  <tr>
   <td align="center">비밀번호</td>
   <td><input type="password" name="pwd" size="25"></td>
  </tr>
  
  <tr>
   <td colspan="2" align="center">
    <input type="submit" value="등록">
    <input type="button" value="목록" onclick="location.href='jstlList.jsp'">
   </td>
  </tr>
 </table>
</form>
</body>
</html>











