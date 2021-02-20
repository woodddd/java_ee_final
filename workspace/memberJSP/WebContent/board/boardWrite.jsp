<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
 
<%
request.setCharacterEncoding("UTF-8");
String name = (String)session.getAttribute("memName");
String id = (String)session.getAttribute("memId");
String email = (String)session.getAttribute("memEmail");

String subject = request.getParameter("subject");
String content = request.getParameter("content");

//자바 객체사용 - 내가한것.
/* BoardDTO boardDTO = new BoardDTO();


boardDTO.setId("hong");
boardDTO.setName("홍길동");
boardDTO.setEmail("hong@java.com");
boardDTO.setSubject(subject);
boardDTO.setContent(content);
boardDTO.setLev(0);
boardDTO.setStep(0);
boardDTO.setPseq(0);
boardDTO.setReply(0);
boardDTO.setHit(0);

BoardDAO boardDAO = BoardDAO.getInstance();
int su = boardDAO.boardWrite(boardDTO); */
/* 위의 문장을 사용하기 위해서는 boardWrite 를기존 DTO 에 값을 저장하는 방식으로 사용을 해야함. 하지만 나는 강사님것도 보고싶어서 바꿀거 */ 

Map<String, String> map = new HashMap<String, String>();
map.put("name", name);
map.put("id", id);
map.put("email", email);
map.put("subject", subject);
map.put("content", content);

//DB
BoardDAO boardDAO = BoardDAO.getInstance();
boardDAO.boardWrite(map);



%>


 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload=function(){
	alert("작성하신 글을 저장하셨습니다.");
	location.href="boardList.jsp?pg=1";
}
</script>
</head>
<body>



</body>
</html>