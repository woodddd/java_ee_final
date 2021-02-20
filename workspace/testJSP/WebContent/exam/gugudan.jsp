<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 
 <%!
 int x=1;
 int y=1;
 
 %>
 
 <%
 
 x++;
 if(x >= 10){
	 x = 1;
 }
 
 y++;
 if(y >= 10){
	 y = 1;
 }
 %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body>
<div style="border: 1px dotted red; text-align: center;"><h3>구구단</h3></div>
<table border ="1">
<% for(int i = 1; i < 10 ; i++){ %>
	<tr>
	<% for(int dan=1; dan<10; dan ++){ %>
		<td><%=dan %> * <%=i %> = <%=dan *i %></td>  
	<%}//for dan %>
	</tr>
<%}//for i %>
</table>
</body>
</html>