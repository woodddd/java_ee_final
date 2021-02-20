<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form name="boardViewForm">

<input type="hidden" name="seq" value="${boardDTO.seq }">
<input type="hidden" name="pg" value="${pg }">
<hr>

<table border="1" width="450" cellpadding="3" cellspacing="0" frame="hsides" rules="rows">
	
		<tr>
			<td colspan="3"><h3>${requestScope.boardDTO.getSubject() }</h3></td>
		</tr>
		
		<tr>
			<td width="150">글번호: ${requestScope.boardDTO.getSeq() }</td>
			<td width="150">작성자: ${requestScope.boardDTO.getId() }</td>
			<td width="150">조회수: ${requestScope.boardDTO.getHit() }</td>
		</tr>
		
		<tr>
			<td colspan="3" height="200" valign="top" style="white-space: pre-line; word-break: break-all">${requestScope.boardDTO.getContent() }</td> 
		</tr>
	
</table>
<input type="button" value="목록" onclick="location.href='../board/boardList.do?pg=${pg }'">
	<c:if test="${requestScope.boardDTO.getId() == sessionScope.memId }">
		<input type="button" value="글수정" onclick="mode(1)">
		<input type="button" value="글삭제" onclick="mode(2)">
	</c:if>
	 <input type="button" value="답글" onclick="location.href='boardReplyForm.do?pseq=${boardDTO.seq}&pg=${pg }'">
</form>
<script type="text/javascript">
function mode(num){
   if(num==1){//글수정
      document.boardViewForm.method="post";
      document.boardViewForm.action="boardModifyForm.do";
      document.boardViewForm.submit();
      
   }else if(num==2){//글삭제
      if(confirm("정말로 삭제하시겠습니까?")){
         document.boardViewForm.method="post";
         document.boardViewForm.action="boardDelete.do";
         document.boardViewForm.submit();
      }      
   }
}

</script>
