<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="angel" uri="tld" %>
<%@ taglib prefix="devil" uri="tld" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산</title>
</head>
<body>
<h3>자바클래스의 메소드를 이용하여 계산</h3>
${param['x'] } + ${param['y'] } = ${ angel:sum(param['x'], param['y']) }<br> <!-- angel 은 sum 을 사용하기 위한지시자. 다른 이름이어도 상관이 없음 --> 

${param['x'] } - ${param['y'] } = ${ devil:minus(param['x'], param['y']) }<br>

${param['x'] } * ${param['y'] } = ${ devil:mul(param['x'], param['y']) }<br>

</body>
</html>