<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
<form name="boardForm" method="post" action="boardWrite.jsp">
<h3>글쓰기</h3>
<hr>
	<table border="1" cellpadding="3" cellspacing="1">
		<tr>
			<td align="center">제목</td>
			<td>
			<input type="text" id="subject" name="subject" placeholder="제목입력">
			<div id="subjectDiv" style=" color: red; font-size:8pt; font-weight:bold;">
			</td>
		</tr>
		
		<tr>
			<td align="center">내용</td>
			<td>
			<textarea id="content" name="content" cols="50" rows="15" placeholder="내용입력"></textarea>
			<div id="contentDiv" style=" color: red; font-size:8pt; font-weight:bold;">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글쓰기" onclick="javascript:checkBoardWrite()">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	
	</table>
</form>
<script type = "text/javascript" src= "../js/board.js"></script>
</body>
</html>