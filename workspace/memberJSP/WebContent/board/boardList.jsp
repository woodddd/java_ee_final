
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="board.bean.BoardDTO"%>
<%@page import="board.bean.BoardPaging"%>
<%@page import="board.dao.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%-- <%
	//내가한것
	//데이터
String id = (String)session.getAttribute("memId");

int pg = Integer.parseInt(request.getParameter("pg"));

int endNum = pg*5; 
int startNum = endNum -4;


List<BoardDTO> boardList = new ArrayList<BoardDTO>();

//DB
BoardDAO boardDAO = BoardDAO.getInstance();
/* boardList = boardDAO.listRead(); */

//총글수
int totalA = boardDAO.getTotalA();//총글수
int totalP = (totalA + 4)/5;//총 페이지수

List<BoardDTO> list = boardDAO.boardList(startNum,endNum);

%> --%>

<%
//데이터
String id = (String)session.getAttribute("memId");
int pg = Integer.parseInt(request.getParameter("pg"));

//DB
int endNum = pg*5;
int startNum = endNum-4;
BoardDAO boardDAO = BoardDAO.getInstance();
List<BoardDTO> list = boardDAO.boardList(startNum, endNum);


//페이징 처리 - 1페이지당 5개씩
int totalA = boardDAO.getTotalA();//총글수

BoardPaging boardPaging = new BoardPaging();
boardPaging.setCurrentPage(pg);
boardPaging.setPageBlock(3);
boardPaging.setPageSize(5);
boardPaging.setTotalA(totalA);
boardPaging.makePagingHTML();


//조회수 - 새로고침 방지
//강사님 쿠키사용
if(session.getAttribute("memId") != null){
	Cookie cookie = new Cookie("memHit","0");
	cookie.setMaxAge(30*60);//30분 시간 설정(초단위라 * 60해준것임)
	response.addCookie(cookie);//클라이언트에게 보내기
}
//내가 쿠키사용한것.
/* Cookie cookie = new Cookie("check", "check"); */

//클라이언트에 저장
/* response.addCookie(cookie); */

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../css/member.css">

<style type="text/css">
th{
	font-size: 12pt;
}

td{
	font-size: 10pt;
}

#subjectA:link {color: black; text-decoration: none;} /* 링크가걸렸을 때 */
#subjectA:visited{color: black; text-decoration: none;} /* 손을 뗐을때 */
#subjectA:hover{color: lightgreen; text-decoration: underline;} /* 마우스에 그냥 올라갔을때, */
#subjectA:active{color: black; text-decoration: none;} /* 마우스를 누르고 있을때 */

#currentPaging {color: red; text-decoration underline;}
#paging {color: black; text-decoration: none; }


</style>
</head>
<body>

	<table border="1" cellpadding="3" cellspacing="0" frame="hsides" rules="rows"> 
	<!-- frame="hsides" rules="rows" 속성은 행의 사이드를 없앰. -->
	
	<tr>
		<th width="100">글번호</th><!-- 가운데, 굵게 -->
		<th width="300">제목</th>
		<th width="100">작성자</th>
		<th width="100">조회수</th>
		<th width="100">작성일</th>
	</tr>
	<% if(list != null){ %>
		<% for(int i = 0; i <list.size() ; i++ ){ %>
				<tr>
				<td align="center"><%=list.get(i).getSeq() %></td>
				<td><a id="subjectA" href="#" onclick="islogin(<%=list.get(i).getSeq() %>,<%=pg %>,'<%=id %>')"><%=list.get(i).getSubject() %></a></td>
				<td align="center"><%=list.get(i).getId() %></td>
				<td align="center"><%=list.get(i).getHit() %> </td>
				<td align="center"><%=list.get(i).getLogtime() %></td>
				</tr>
				
				
				
				
		<%}//for %>
	<%}//if %>
	<tr>
		<!-- <td> -->
	</table>
		<br>
		
		<img src="../image/apple.png" style="float: left; width: 20px; height: 20px; cursor: pointer;" 
		onclick="location.href='../main/index.jsp'">

		<div style="display: inline-block; width: 700px; text-align: center;">
		<%=boardPaging.getPagingHTML() %>
		</div>
		
		
		
		<%-- </td>
		<td colspan="4" align="center">
				
		<%if(list != null){ %>
			<%for(int i = 1; i <= totalP; i++){ %>
				<%if(pg==i){ %>
				[<a id="currentPaging" href="boardList.jsp?pg=<%=i %>"><%=i %></a>]
				<%}else{ %>
				[<a id="Paging" href="boardList.jsp?pg=<%=i %>"><%=i %></a>]
				<%}//if %>
			<%}//for %>
		<%}//if %>
		</td>
	</tr> --%>
		





<script type="text/javascript">
function islogin(seq,pg,id){
	//if() alert("먼저 로그인 하세요")
	//else
		/*  alert(seq+", " + pg); 데이터넘어온지확인 */
		/* alert(id); 어떤아아디로 들어온건지확인용 */
	if(id == 'null'){ /* id === "null" */
		alert("먼저 로그인 하세요.");
	}else{
		location.href="boardView.jsp?seq=" + seq + "&pg=" + pg; /* 작성한글 확인하러 이동 */
	
	}
		

}//function
</script>

</body>
</html>