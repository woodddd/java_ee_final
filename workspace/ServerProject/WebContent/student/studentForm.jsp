<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>입력화면</h3>
<hr>
	<form name="studentForm" method="post" action="dbinsert.jsp">
		<table border = "1" >
			<tr>
				<td align="center">학번</td>
				<td>
					<input type="text" id="num" name="num"></input>
					<div id="numDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
				</td>
			</tr>
			
			<tr>
				<td align="center">이름</td>
				<td>
					<input type="text" id="name" name="name"></input>
					<div id="nameDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
				</td>			
			</tr>
			
			<tr>
				<td align="center">국어</td>
				<td>
					<input type="text" name="kor"></input>
				</td>			
			</tr>
			
			<tr>
				<td align="center">영어</td>
				<td>
					<input type="text" name="eng"></input>
				</td>			
			</tr>
			
			<tr>
				<td align="center">수학</td>
				<td>
					<input type="text" name="math"></input>
				</td>			
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="등록" onclick="check()"></input>
					<input type="reset" value="다시쓰기"></input>
					<input type="button" value="목록" onclick=location.href='studentList.jsp'></input>
				</td>
			</tr>
					
		</table>
	</form>
<script>
function check(){
	document.getElementById("numDiv").innerText = "";
	document.getElementById("nameDiv").innerText = "";
	
	if(document.getElementById("num").value == ""){
		document.getElementById("numDiv").innerText = "학번을 입력하세요";
	}else if(document.getElementById("name").value == ""){
		document.getElementById("nameDiv").innerText = "이름을 입력하세요";
	}else{
		document.studentForm.submit();
	}
}
</script>
</body>
</html>