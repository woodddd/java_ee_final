<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 

<%
String name = request.getParameter("name");
%> 

<% %> -> 스크립트릿 이라고 한다.

안녕하세요, <%= name %> 회원님! 
--%>
안녕하세요, ${param.name} 회원님!