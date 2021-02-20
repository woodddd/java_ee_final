<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sql:query var="rs" dataSource="jdbc/oracle">
	select * from usertable
</sql:query>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
 <!-- 필드명 -->
 <tr>
 <%-- <c:forEach var="columnName" items="${rs.getColumnNames() }"> --%>
  <c:forEach var="columnName" items="${rs.columnNames }">
   <th width="100">${columnName }</th>
  </c:forEach>
  <th width="150">비고</th>
 </tr>
 <!-- 필드 -->
 <%-- <c:forEach var="row" items="${rs.getRowsByIndex() }"> --%>
 <c:forEach var="row" items="${rs.rowsByIndex }">
	<tr>
	 	<c:forEach var="column" items="${row }">
	 		<td width="100" align="center">${column }</td>
	 	</c:forEach>
	 	<td width="150" align="center">
	 		<input type="button" value="수정" onclick="location.href='jstlModifyForm.jsp?id=${row[1]}'">
	 		<input type="button" value="삭제" onclick="del('${row[1] }')">
 	</tr>
 </c:forEach>
 
</table> 
<script type="text/javascript">
function del(id){
	if(confirm("삭제하시겠습니까?")){ /* 동의 구하는 팝업창 - T/F true 삭제하겠다. false 삭제하지 않겠다.*/ /* confirm 자체가 t/f 이므로 confirm==true/f 라고 비교해줄 필요가 없음 */
		location.href='jstlDelete.jsp?id=' + id;	
	
	}
}
</script>
</body>
</html>
















