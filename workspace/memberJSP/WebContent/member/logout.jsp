<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
//쿠키
//쿠키는 특정 쿠키를 얻을 수 없고 모든 쿠키를 가져온다.
/* Cookie[] ar = request.getCookies();
if( ar != null){
	for(int i = 0; i < ar.length ; i++){
		if(ar[i].getName().equals("memName")){
			ar[i].setMaxAge(0);//쿠키 삭제
			response.addCookie(ar[i]);/ /클라이언트에 저장
		}else if(ar[i].getName().equals("memId")){
			ar[i].setMaxAge(0);//쿠키 삭제
			response.addCookie(ar[i]);
		}
	}
} */

//세션
session.removeAttribute("memberName");
session.removeAttribute("memId");

session.invalidate();//무효화.(모든 세션 다 삭제해버림)
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<h3>로그아웃</h3>

<script type="text/javascript">
window.onload=function(){/* 현재 이 페이지를 열었을때(읽자마자) */
	alert("로그아웃");
	location.href="../main/index.jsp";
} 

</script>
</body>
</html>