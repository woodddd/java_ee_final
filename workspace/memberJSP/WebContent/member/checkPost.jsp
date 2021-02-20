<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="member.dao.MemberDAO"%>
<%@ page import="member.bean.ZipcodeDTO"%>

<%
//데이터
request.setCharacterEncoding("UTF-8");

String sido = request.getParameter("sido");
String sigungu = request.getParameter("sigungu");
String roadname = request.getParameter("roadname");
//System.out.println(sido+", "+sigungu+", "+roadname);

//DB
List<ZipcodeDTO> list = null;
if(sido!=null && roadname!=null){
	MemberDAO memberDAO = MemberDAO.getInstance();
	list = memberDAO.getZipcodeList(sido, sigungu, roadname);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../css/member.css">

</head>
<body>
<form method="post" action="checkPost.jsp">
<table border="1" width="100%" cellpadding="2" cellspacing="0">
<tr>
	<td width="100" align="center">시도</td>
	<td>
		<select name="sido" style="width: 100px;">
				<option>시도선택</option>
				<option value="서울">서울</option>
				<option value="인천">인천</option>
				<option value="대전">대전</option>
				<option value="대구">대구</option>
				<option value="울산">울산</option>
				<option value="세종">세종</option>
				<option value="광주">광주</option>
				<option value="경기">경기</option>
				<option value="강원">강원</option>
				<option value="전남">전남</option>
				<option value="전북">전북</option>
				<option value="경남">경남</option>
				<option value="경북">경북</option>
				<option value="충남">충남</option>
				<option value="충북">충북</option>
				<option value="부산">부산</option>
				<option value="제주">제주</option>
			</select>
	</td>
	<td align="center" width="100">시.군.구</td>
	<td><input type="text" name="sigungu"></td>
</tr>

<tr>
	<td align="center">도로명</td>
	<td colspan="3">
		<input type="text" name="roadname" size="35">
		<input type="submit" value="검색">
	</td>
</tr>

<tr>
	<td align="center">우편번호</td>
	<td colspan="3" align="center">주소</td>
</tr>

<%if(list != null){ %>
	<%for(ZipcodeDTO zipcodeDTO : list){ 
		String address = zipcodeDTO.getSido()+" "
					   + zipcodeDTO.getSigungu()+" "
					   + zipcodeDTO.getYubmyundong()+" "
					   + zipcodeDTO.getRi()+" "
					   + zipcodeDTO.getRoadname()+" "
					   + zipcodeDTO.getBuildingname();
	%>
		<tr>
			<td align="center"><%=zipcodeDTO.getZipcode() %></td>
			<td colspan="3">
				<a id="addressA" href="#" onclick="checkPostClose('<%=zipcodeDTO.getZipcode() %>', '<%=address%>')"><%=address%></a>
			</td>
		</tr>
	<%}//for %>
<%}//if %>
</table>
</form>

<script>
function checkPostClose(zipcode, address){
	
	/* opener.document.forms[0].zipcode.value = zipcode; 해당 폼의 0번째 배열에 있는 값 동일폼명이 여러개가 있을때 배열에 담기게됨*/
	/* 아래의 방법과 동일한 문장 */
	
	opener.document.getElementById("postcode").value = zipcode;
	opener.document.getElementById("address").value = address;
	window.close();
	opener.document.getElementById("detailAddress").focus();
}
</script>
</body>
</html>





















