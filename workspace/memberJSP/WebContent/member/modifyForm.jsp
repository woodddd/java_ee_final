<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.bean.MemberDTO"%>

<jsp:useBean id="memberDAO" class="member.dao.MemberDAO" />

<%
//데이터
String id = (String)session.getAttribute("memId");

//DB
MemberDTO memberDTO = memberDAO.getMember(id);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<form name="modifyForm" method="post" action="modify.jsp">
<h3>회원정보수정</h3>
<hr>
<table border="1" cellpadding="3" cellspacing="0">
<tr>
	<td width="100" align="center">이름</td>
	<td>
		<input type="text" id="name" name="name" value="<%=memberDTO.getName() %>" placeholder="이름입력">
		<div id="nameDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
	</td>
</tr>

<tr>
	<td align="center">아이디</td>
	<td>
		<input type="text" id="id" name="id" value="<%=memberDTO.getId() %>" size="25" readonly>
		<div id="idDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
	</td>
</tr>

<tr>
	<td align="center">비밀번호</td>
	<td><input type="password" name="pwd" size="30"></td>
</tr>

<tr>
	<td align="center">재확인</td>
	<td><input type="password" name="repwd" size="30"></td>
</tr>

<tr>
	<td align="center">성별</td>
	<td>
		<input type="radio" name="gender" value="0">남
		<input type="radio" name="gender" value="1">여
	</td>
</tr>

<tr>
	<td align="center">이메일</td>
	<td>
		<input type="text" name="email1" value="<%=memberDTO.getEmail1() %>" size="15">
		@
		<input type="email" name="email2" list="email2" placeholder="직접입력">
		<datalist id="email2">
			<option value="gmail.com">
			<option value="naver.com">
			<option value="hanmail.net">
		</datalist>
	</td>
</tr>

<tr>
	<td align="center">핸드폰</td>
	<td>
	<select name="tel1" style="width: 60px;">
		<option value="010">010
		<option value="011">011
		<option value="019">019
	</select>
	 -
	 <input type="text" name="tel2" value="<%=memberDTO.getTel2() %>" size="5">
	 -
	 <input type="text" name="tel3" value="<%=memberDTO.getTel3() %>" size="5"></td>
</tr>

<tr>
	<td align="center">주소</td>
	<td>
		<input type="text" id="postcode" name="zipcode" value="<%=memberDTO.getZipcode() %>" size="5" readonly>
		<input type="button" value="우편번호검색" onclick="checkPost()"><br>
		<input type="text" id="address" name="addr1" value="<%=memberDTO.getAddr1() %>" size="50"  readonly placeholder="주소"><br>
		<input type="text" id="detailAddress" name="addr2" value="<%=memberDTO.getAddr2() %>" size="50" placeholder="상세주소">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="button" value="회원정보수정" onclick="javascript:checkModify()">
	<input type="reset" value="다시작성">
</tr>
</table>
</form>

<script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
window.onload=function(){
	document.modifyForm.gender[<%=memberDTO.getGender() %>].checked = true;
	document.modifyForm.email2.value = "<%=memberDTO.getEmail2() %>";
	document.modifyForm.tel1.value = "<%=memberDTO.getTel1() %>";

}
</script>
</body>
</html>





















