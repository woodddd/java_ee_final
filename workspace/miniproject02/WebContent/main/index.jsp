<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* margin 태그는 위 오른쪽 아래 왼쪽 순으로 여백을 설정하는데 margin: 0 을 하게 되면 body(화면전체)영역에 여백을 사용하지 않겠다는것. */
body {
	margin: 0;
	padding: 0;
	height: 100%;
}

#header{
	width: 100%;
	height: 10%; /* 전체 높이의 10퍼센트정도 사용하겠다. */
	text-align: center;
	border: 1px solid lightgray;
}

#container {
	width: 100%;
	height: 500px;
	border: 1px solid lightgray;
}

#nav {
	width: 20%;
	height: 100%;
	float: left;
	border: 1px solid red;
}

#section {
	width: 79%;
	height: 100%;
	float: left;
	border: 1px solid blue;
}

#footer {
	width: 100%;
	height: 10%;
	border: 1px solid lightgray;
}
</style>
</head>
<body>
<div id="header">
	
	<h1><img src="../image/image01.jpg" width="50" height="50"
	onclick="location.href='../main/index.do'" style="cursor: pointer;">MVC 기반의 미니 프로젝트</h1>
</div>
<div id="container">
	<div id="nav">
		<jsp:include page="menu.jsp"/> <!-- menu.jsp의 내용을 끌고들어온다 -->
	</div>

	<div id="section">
		<jsp:include page="${display }" />
	</div>	
</div>

<div id="footer">
	<p>비트캠프
</div>
</body>
</html>