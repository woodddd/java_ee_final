<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<fmt:requestEncoding value="UTF-8"/> <%-- post방식으로 데이터가 넘어오면 한글이 꺠져서 보이는 현상이 있다. 그래서 한글 파일을 읽을 수 있도록 현재 문장을 써준다.
단, 이 문장을 사용하려면, <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 이 문장을 써야한다. --%>
이 름 : ${param.name }<br><br> <!-- submit 으로 넘어온 jstlInput.jsp 의 name 변수들중 name 과 age 를 getParameter 형식으로 받아옴 -->

나 이 : ${param.age }<br><br>
<c:if test="${param.age > 20 }">
	<string>성인</string>
</c:if>	
<c:if test="${param.age <= 20 }">
	<string>청소년</string>
</c:if>
<br><br>

색 깔 : 
<c:if test="${param.color == 'red'}"> <!-- "" 를 사용하면 에러가 발생하고 자바가 아니기 때문에 equals 를 사용하지 않는다. -->
빨 강
</c:if>

<c:if test="${param.color == 'green'}">
초 록
</c:if>

<c:if test="${param.color == 'blue'}">
파 랑
</c:if>

<c:if test="${param.color == 'violet'}">
보 라
</c:if>

<c:if test="${param.color == 'cyan'}">
하 늘
</c:if>
<br><br>

<!-- 다중if문 같은 역할을 한다. 
또는 스위치랑 비슷하다고 이야기 할 수도 있다. -->
<!-- 위의 문장과 결과 동일 -->
색 깔 : 
<c:choose>
   <c:when test="${param.color == 'red' }">빨강</c:when>
   <c:when test="${param.color == 'green' }">초록</c:when>
   <c:when test="${param.color == 'blue' }">파랑</c:when>
   <c:when test="${param.color == 'violet' }">보라</c:when>
   <c:otherwise>하늘</c:otherwise>   
</c:choose>

<br><br>
<!-- 이전 자바에서 취미를 (여러개의 값을) 가져올 때엔 getParameterValues 로 배열로 받아왔었다. -->
취 미 : 
${paramValues['hobby'][0] }  <!-- 해당 배열에 값이 있을때 값을 출력한다 -->
${paramValues['hobby'][1] }
${paramValues['hobby'][2] }
${paramValues['hobby'][3] }
${paramValues['hobby'][4] }
<br><br>
<!-- 위의 방식을 포문으로 작성한 문장 -->
취 미 : 
<c:forEach var="data" items="${paramValues.hobby }">
	${data }
</c:forEach>
<br><br>


</body>
</html>