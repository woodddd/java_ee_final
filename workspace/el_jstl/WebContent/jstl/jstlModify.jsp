<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8" />

<!-- 업데이트는 리턴을 하지 않아도 되므로 var 를 쓰지않아도 된다. -->
<sql:update dataSource="jdbc/oracle"> 
	update usertable set name = '${param.name }', pwd='${param.pwd }' where id='${param.id }'
</sql:update>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
window.onload=function(){
	alert("DB수정완료");
	location.href='jstlList.jsp';
}
</script>
</body>
</html>