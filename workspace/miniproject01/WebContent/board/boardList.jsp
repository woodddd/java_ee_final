
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    


<style>
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

#currentPaging {
		color: red; 
		text-decoration: underline;
		cursor: pointer;
		}
#paging {
		color: black;
		text-decoration: none;
		cursor: pointer; /* 마우스를 가져다 데면 손가락 모양으로 마우스 모양을 바꿔줌 */
		}


</style>

	<table border="1" cellpadding="3" cellspacing="0" frame="hsides" rules="rows"> 
	<!-- frame="hsides" rules="rows" 속성은 행의 사이드를 없앰. -->
	
	<tr>
		<th width="180">글번호</th><!-- 가운데, 굵게 -->
		<th width="500">제목</th>
		<th width="180">작성자</th>
		<th width="100">조회수</th>
		<th width="180">작성일</th>
	</tr>
	<c:if test="${list != null }">
		<c:forEach var="boardDTO" items="${requestScope.list }">
				<tr>
				<td align="center">${boardDTO.getSeq() }</td>
				<td>
					<c:forEach var="i" begin="1" end="${boardDTO.lev }" step="1">
						&emsp;
					</c:forEach>
				
					<c:if test="${boardDTO.pseq != 0 }">
						<img src="../image/reply.gif">
					</c:if>
					<a id="subjectA" href="#" onclick="islogin(${boardDTO.getSeq() },${requestScope.pg },'${sessionScope.memId }')">
					${boardDTO.getSubject() }
					</a>
				</td>
				<td align="center">${boardDTO.getId() }</td>
				<td align="center">${boardDTO.getHit() }</td>
				<td align="center">
					<fmt:formatDate pattern="YYYY.MM.dd" value="${boardDTO.getLogtime() }"/>
				</td>
				</tr>
		</c:forEach>
	</c:if>
	<tr>
	</table>
		<br>

		<div align="center">
		${boardPaging.pagingHTML }
		</div>
		
		
<br><br>
<form name="boardListSearchForm" method="post" action="boardSearch.do">
<input type="hidden" name="pg" value="1">
<div style="text-align: center;">
	<select name="searchType" style="width: 100px;">
		<option value = "subject" selected>제목</option>
		<option value = "id">아이디</option>
	</select>
	<input type="search" name="keyword">
	<input type="submit" value="검 색">
</div>
</form>	
<script type="text/javascript">
function islogin(seq,pg,id){
	//if() alert("먼저 로그인 하세요")
	//else
		/*  alert(seq+", " + pg); 데이터넘어온지확인 */
		/* alert(id); 어떤아아디로 들어온건지확인용 */
	if(id == 'null' || id == ''){ /* id === "null" */
		alert("먼저 로그인 하세요.");
	}else{
		location.href="boardView.do?seq=" + seq + "&pg=" + pg; /* 작성한글 확인하러 이동 */
	
	}
		

}//function

function boardPaging(pg){
	//let keyword = document.boardListSearchForm.keyword.value;
	
	if("${keyword}" == ""){
		location.href="boardList.do?pg="+pg;
	}else{
		location.href="boardSearch.do?pg="+pg+"&searchType=${searchType}&keyword=${keyword}" ;	
	}
	
}


</script>
