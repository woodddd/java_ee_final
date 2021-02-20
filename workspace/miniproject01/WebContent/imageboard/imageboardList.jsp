<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<style type="text/css">
th{
	font-size: 12pt;
}

td{
	font-size: 10pt;
}

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
<form name="imageboardListForm" method="get" action="imageboardDelete.do">
	<table border="1" cellpadding="3" cellspacing="0" frame="hsides" rules="rows">
	
	<tr>
		<th width="180"><input type="checkbox" id="all" onclick="CheckAll()">글번호</th>
		<th width="500">이미지</th>
		<th width="180">상품명</th>
		<th width="100">단가</th>
		<th width="180">개수</th>
		<th width="180">합계</th>
	</tr>
	<c:if test="${list != null }">
		<c:forEach var="imageboardDTO" items="${requestScope.list }">		
			<tr>
				<td align="center">
				<input type="checkbox" name="check" value="${imageboardDTO.getSeq() }">${imageboardDTO.getSeq() }</td>
				<td align="center">
					<a href="imageboardView.do?seq=${imageboardDTO.getSeq() }&pg=${pg}">
					<img src="http://localhost:8080/miniproject01/storage/${imageboardDTO.getImage1() }" width="50" height="50">
					</a>
				</td>
				<td align="center">${imageboardDTO.getImageName() }</td>
				<td align="center">
					<fmt:formatNumber pattern="#,###" value="${imageboardDTO.getImagePrice() }"/>
				</td>
				<td align="center">${imageboardDTO.getImageQty() }</td>
				<td align="center">
				<fmt:formatNumber pattern="#,###원" value="${imageboardDTO.getImagePrice() * imageboardDTO.getImageQty() }"/>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</table>
	<br>

	<div align="left" style="float: left;">
		<input type="button" value="선택삭제" onclick="choiceDelete()">
	</div>	
		
	<div  style="float: left; text-align: center; width: 600px;">
	${imageboardPaging.pagingHTML }
	</div>
	
		
</form>		

<script type="text/javascript">

function imageboardPaging(pg){
	
	location.href="imageboardList.do?pg="+pg;
		
}

function CheckAll(){
	//alert(document.getElementsByName("check").length);//3
	//alert(document.getElementById("all").checked); // true/false
	let check = document.getElementsByName("check");
	if(document.getElementById("all").checked){ //선택
		for(var i = 0 ; i < check.length ; i++){
			check[i].checked = true;
		}
	}else{ //해제
		for(var i = 0 ; i < check.length ; i++){
			check[i].checked = false;
		}
	}
	
}

function choiceDelete(){
	
	let check = document.getElementsByName("check");
	let count = 0;
	for(var i = 0; i < check.length; i++){
		if(check[i].checked){
			count++;	
		}
	}
	
	console.log("count = " + count);
	
	if(count == 0){
		alert("삭제 할 항목을 선택하세요");
	}else{
		if(confirm("정말로 삭제하시겠습니까?")){
			document.imageboardListForm.submit();	
		}
	} 
}

</script>