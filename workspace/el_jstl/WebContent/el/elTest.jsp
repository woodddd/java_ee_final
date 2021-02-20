<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="50%"> <!-- 이곳에 들어온 50퍼센트는 테이블을 화면을 기준으로 50퍼센트로 잡아라 라는 말임 그래서 화면크기를 늘리거나 줄여도 비율이 같음 -->

	<tr>
		<th width="50%">표현식</th> <!-- th - 테이블헤더 ( 글짜가 굴게나오고 가운데정렬됨 ) -->
		<th width="50%">값</th>
		<!-- xx%는 테이블 열의 비율을 말함 현재 width의 길이를 50% 잡아라.  --> 
	</tr>
	
	<tr>
		<td align="center">\${25+3}</td> <!-- 앞에 \를 붙이면 뒤의 문자열을 그대로 출력함 -->
		<td align="center">${25+3}</td>
	</tr>
	
	<tr>
		<td align="center">\${25/3 }</td>
		<td align="center">${25/3 }</td>
	</tr>
	
	<tr>
		<td align="center">\${25 div 3 }</td>
		<td align="center">${25 div 3 }</td>
	</tr>
	
	<tr>
		<td align="center">\${25 % 3 }</td>
		<td align="center">${25 % 3 }</td>
	</tr>
	
	<tr>
		<td align="center">\${25 mod 3 }</td>
		<td align="center">${25 mod 3 }</td>
	</tr>
	
	<tr>
		<td align="center">\${25 < 3 }</td>
		<td align="center">${25 < 3 }</td>
	</tr>
	
	
	<!-- gt(>), lt(<), ge(>=), le(<=), eq(==), ne(!=) -->
	
	<tr>
		<td align="center">\${25 ne 3}</td>
		<td align="center">${25 ne 3 }</td>
	</tr>
	
	<tr>
		<td align="center">\${header['host'] }</td>
		<td align="center">${header['host'] }</td>
	</tr>
	
	<tr>
		<td align="center">\${header.host }</td>
		<td align="center">${header.host }</td>
	</tr>
		
</table>
</body>
</html>