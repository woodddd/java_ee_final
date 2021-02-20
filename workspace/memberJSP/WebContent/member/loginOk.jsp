<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
//String name = request.getParameter("name");

String name = null;
String id = null;

//쿠키
/* Cookie[] ar = request.getCookies();//특정 쿠키를 얻을 수 없고, 모든 쿠키를 가져온다.
//Cookie 설정 시간이 지나면 ar 에는 null 값이 들어옴.
if(ar != null){
	for(int i = 0; i < ar.length; i++){
		//System.out.println("쿠키명 = " + ar[i].getName());
		//System.out.println("쿠키값 = " + ar[i].getValue());
		//System.out.println();
		
		if(ar[i].getName().equals("memName")) name = ar[i].getValue();
		else if(ar[i].getName().equals("memId")) id = ar[i].getValue();
	}
} */

//세션

name = (String)session.getAttribute("memName"); //자식 = (자식)부모
id = (String)session.getAttribute("memId");
//넘겨주는 객체는 오브젝트이나 스트링으로 받으려 하고 있기 때문에 형변환을 하지 않으면 에러가 발생한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="../image/apple.png" style="width: 30px; height: 30px; cursor: pointer;" 
onclick="location.href='../main/index.jsp'">
<%=name %>님 로그인<br><br>
	
<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
<input type="button" value="회원정보수정" onclick="location.href='modifyForm.jsp'"> 
</body>
</html>


