<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="" method="post" encType="multipart/form-data" action="fileUpload.jsp">
<table border="1">
	<tr>
		<td align="center">제목</td>
		<td>
		<input type="text" id="subject" name="subject">
		</td>
	</tr>
		
	<tr>
		<td align="center">내용</td>
		<td>
		<textarea id="content" name="content" cols="50" rows="15"></textarea>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="file" name="upload1" size="50">
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="file" name="upload2" size="50">
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="파일 업로드">
			<input type="reset" value="다시작성">
		</td>
	</tr>
</table>

</form>
</body>
</html>