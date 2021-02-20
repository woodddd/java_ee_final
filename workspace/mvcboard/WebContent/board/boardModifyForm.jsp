<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
<form name="boardModifyForm" method="post" action="boardModify.do?">

<input type="hidden" name="seq" value="${boardDTO.seq }">
<input type="hidden" name="pg" value="${pg }">

<h3>글쓰기</h3>
<hr>
	<table border="1" cellpadding="3" cellspacing="1">
		<tr>
			<td align="center">제목</td>
			<td>
			<input type="text" id="subject" value="${boardDTO.subject }" name="subject">
			<div id="subjectDiv" style=" color: red; font-size:8pt; font-weight:bold;">
			</td>
		</tr>
		
		<tr>
			<td align="center">내용</td>
			<td>
			<textarea id="content" name="content" cols="50" rows="15" >${boardDTO.content }</textarea>
			<div id="contentDiv" style=" color: red; font-size:8pt; font-weight:bold;">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="수정완료" onclick="javascript:checkBoardModify()">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	
	</table>
</form>
<script type="text/javascript">
function checkBoardModify() {
	document.getElementById("subjectDiv").innerText = "";
	document.getElementById("contentDiv").innerText = "";
	
	if(document.getElementById("subject").value == "") {
		document.getElementById("subjectDiv").innerText = "제목을 입력하세요";
	}
	else if(document.getElementById("content").value == ""){
		document.getElementById("contentDiv").innerText = "내용을 입력하세요";
	}
	else{
		document.boardModifyForm.submit();
	}
		
}

</script>

</body>
</html>