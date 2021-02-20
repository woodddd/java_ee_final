<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%! //실행시 딱 한번만 처리한다.
//전역변수 - 1번만실행
String name = "홍길동";
int age = 25;
%>

<%
//지역변수,요청시(ex> F5(refresh) 마다 처리
age++;
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  Hello JSP!!<br> -->  
ㅇㅇ
<%-- 안녕하세요 JSP!!<br> --%>
<%-- 위 둘의 차이는 html 주석은 html로(웹으로) 전송되어 문법을 해석하는 과정에서 주석으로 처리되고,
     JSP 주석은 웹으로 전송조차 되지 않는다. --%>

나의 이름은 <%=name %> 입니다.<br>

<%-- 내 나이는 <%=age %> 살 입니다.<br> --%>




<%-- <% out.println("내 나이는 " + age + "살 입니다"); %> --%>
</body>
</html>