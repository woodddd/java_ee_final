
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>    
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%
String realFolder = request.getServletContext().getRealPath("/storage"); /* 스토리지의 실제 폴더를 가져와라 */
//fileForm을 submit하게되면 realFolder의 경로를 볼 수 있다.
System.out.println("실제폴더 = " + realFolder);

//업로드
/* MultipartRequest는 파일만 받아서 처리한다 */
/* 이 클래스만 생성하면 업로드가 된다. */
MultipartRequest multi = new MultipartRequest(request //모든 리퀘스트 권한을 받는다.  
											, realFolder
											, 5*1024*1024 // 최대 5byte (5*1024는 5KB -> *1024 -> 5MB , 업로드 할 수 있는 최대 파일 크기)
											, "UTF-8"
											, new DefaultFileRenamePolicy()); //동일한 파일이름이 올라오면 이름을 자동으로 바꿔준다. ex-> abc.txt 를 중복해서올리면 abc1.txt abc2.txt...와같이 생성된다

//데이터
/* MultipartRequest에서 request의 모든 권한을 받았으므로
request로는 데이터를 받을 수 없다. */
/* String subject = request.getParameter("subject"); */
String subject = multi.getParameter("subject");
String content = multi.getParameter("content");

String originalFileName1 = multi.getOriginalFileName("upload1"); //원본 파일이름.
String originalFileName2 = multi.getOriginalFileName("upload2");

String fileName1 = multi.getFilesystemName("upload1"); //Rename되어 바뀐 이름의 파일을 읽어옴.
String fileName2 = multi.getFilesystemName("upload2");

File file1 = multi.getFile("upload1"); //파일생성. multi를 통해서 넘어온 upload1을 파일로 생성해준다. 
File file2 = multi.getFile("upload2");

long fileSize1=0;
long fileSize2=0;
if(file1 != null) fileSize1 = file1.length(); //length 는 파일의 크기를 읽어오는데 1byte 단위로 읽어온다.
if(file2 != null) fileSize2 = file2.length();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>파일이 업로드 되었습니다</h3>
<hr>
<ul>
	<li>제 목 : <%=subject %><br>
	<li>내 용 : <pre><%=content %></pre><br> <!-- 프리태그(<pre>)를 이용하게되면 입력한 모양 그대로 출력하게된다. -->
	<li>파 일 : <a href="fileDownload.jsp?fileName=<%=URLEncoder.encode(originalFileName1,"UTF-8") %>"><%=originalFileName1 %></a><br>
	<li>파 일 : <%=fileName1 %><br>
	<li>크 기 : <%=fileSize1 %><br>
	<br><br>
	<li>파 일 : <a href="fileDownload.jsp"><%=originalFileName2 %></a><br>
	<li>파 일 : <%=fileName2 %><br>
	<li>크 기 : <%=fileSize2 %><br>
</ul>
</body>
</html>