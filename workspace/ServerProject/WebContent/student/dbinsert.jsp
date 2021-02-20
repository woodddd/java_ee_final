

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="student.dao.StudentDAO"%>
<%@page import="student.bean.StudentDTO"%>   
<%

request.setCharacterEncoding("UTF-8");


String num = request.getParameter("num");
String name = request.getParameter("name");
int kor = Integer.parseInt(request.getParameter("kor"));
int eng = Integer.parseInt(request.getParameter("eng"));
int math = Integer.parseInt(request.getParameter("math"));

int hab = kor + eng + math;
double average = (double)(kor + eng + math)/3;

StudentDTO studentDTO = new StudentDTO();

studentDTO.setNum(num);
studentDTO.setName(name);
studentDTO.setKor(kor);
studentDTO.setEng(eng);
studentDTO.setMath(math);
studentDTO.setHab(hab);
studentDTO.setAverage(average);

System.out.println(num);
System.out.println(name);
System.out.println(kor);
System.out.println(eng);
System.out.println(math);
System.out.println(hab);
System.out.println(average);



StudentDAO studentDAO = StudentDAO.getInstance();
int su = studentDAO.insert(studentDTO);





%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(su == 1){ %>
	[등록 완료]
	<br><br>
	<input type="button" value="목록보기" onclick=location.href='studentList.jsp'>
<%}else{ %>
	[등록 실패]
<%} %>
</body>
</html>