<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>

<%
//데이터
int seq = Integer.parseInt(request.getParameter("seq"));
int pg = Integer.parseInt(request.getParameter("pg"));

//DB
BoardDAO boardDAO = BoardDAO.getInstance();

//조회수 - 새로고침 방지
//특정 쿠키만 얻을 수 없으므로 모든 쿠키 가져오기
Cookie[] ar = request.getCookies();
String check = null;
if(ar != null){
	for(int i = 0; i < ar.length; i++){
		if(ar[i].getName().equals("memHit")){
			boardDAO.hitUpdate(seq);
			ar[i].setMaxAge(0);
			response.addCookie(ar[i]);//이 문장을 수행하는 이유는 setMaxAge(0)으로 변경한 시간을 클라이언트가 모르기 때문에 클라이언트에게 알려주기 위해서 다시 보내주는 것임.
		}//if
	}//for
}//if



BoardDTO boardDTO = boardDAO.boardView(seq);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>

<table border="1" width="450" cellpadding="3" cellspacing="0" frame="hsides" rules="rows">
	
		<tr>
			<td colspan="3"><h3><%=boardDTO.getSubject() %></h3></td>
		</tr>
		
		<tr>
			<td width="150">글번호: <%=boardDTO.getSeq() %> </td>
			<td width="150">작성자: <%=boardDTO.getId() %></td>
			<td width="150">조회수: <%=boardDTO.getHit() %></td>
		</tr>
		
		<tr>
			<td colspan="3" height="200" valign="top" style="white-space: pre-line; word-break: break-all"><%=boardDTO.getContent() %></td> 
		</tr>
	
</table>
<input type="button" value="목록" onclick="location.href='../board/boardList.jsp?pg=<%=pg %>'">
</body>
</html>