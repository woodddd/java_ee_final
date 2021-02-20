
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="student.bean.StudentDTO"%>
<%@page import="student.dao.StudentDAO"%>  
<%@page import="java.util.List"%>   

<%
request.setCharacterEncoding("UTF-8");

StudentDAO studentDAO = StudentDAO.getInstance();
List<StudentDTO> list = studentDAO.select();




%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<table border="1">
			<tr>
				<th width="100">학번</th>
				<th width="100">이름</th>
				<th width="100">국어</th>
				<th width="100">영어</th>
				<th width="100">수학</th>
				<th width="100">총점</th>
				<th width="100">평균</th>
			</tr>
			
			<% if(list != null){ %>
				<% for(int i = 0; i < list.size() ; i++){ %>
					<tr>
						<td align="center"><%=list.get(i).getNum() %></td>
						<td align="center"><%=list.get(i).getName() %></td>
						<td align="center"><%=list.get(i).getKor() %></td>
						<td align="center"><%=list.get(i).getEng() %></td>
						<td align="center"><%=list.get(i).getMath() %></td>
						<td align="center"><%=list.get(i).getHab() %></td>
						<td align="center"><%=list.get(i).getAverage() %></td>
						
					</tr>
				<%}//for %>
			<%}//if %>
		</table>
	</form>
</body>
</html>