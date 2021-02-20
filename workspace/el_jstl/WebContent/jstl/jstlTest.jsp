<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>** 변수설정 **</h3>

<%-- c: 를 사용하려면  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> core에 대한 tablib 를해야한다. --%>
<c:set var="name" value="홍길동" ></c:set>
<c:set var="age">25</c:set>
<!-- value 를 사용해도 되지만 아래줄의 코드와 같이 <> <> 태그사이에 값을 입력해 주어도 된다. -->

나의 이름은 <c:out value="${name }" /> 입니다.<br> 
나의 이름은 ${name } 입니다.<br>

<!-- 출력되는 결과는 똑같으나 위의 코드가 보안의 기능이 추가되어있음 -->

내 나이는 ${age }살 입니다<br> 
<!-- 선언한 적이 없는 메소드는 웹에서는 널이 반환되지도 에러가 발생되지도 않는다.그냥 값이 안나온다-->

<h3>** 변수 삭제 **</h3>
<c:remove var="name"/>
나의 이름은 <c:out value="${name }"/>입니다.<br>
내 나이는 ${age }살 입니다.<br>

<h3>** forEach **</h3>
<c:forEach var="i" begin="1" end="10" step="1">
	${i }
</c:forEach>
<br>

<h2>10 9 8 7 6 5 4 3 2 1</h2>
<c:forEach var="i" begin="1" end="10" step="1"> <!-- step은 0보다 큰 값이 와야한다. -->
	${11+(i*-1)}
</c:forEach>
<br>


<h2>1~10까지의 합</h2>
<c:forEach var="i" begin="1" end="10" step="1">
	
	<c:set var="sum" value="${sum+i }"></c:set> 
</c:forEach>
${sum }
<br>

<h3>** 문자열 분리 **</h3>
<c:forTokens var="car" items="소나타,아우디,링컨,페라리,벤츠" delims=", "> <!-- 딜림즈(구분기호) -->
	${car }<br>
</c:forTokens>

<h3>** jstlStart.jsp에서 넘어온 데이터</h3>
결과 = ${requestScope.list } <!-- 자바에서의 request 객체 --> 
<!-- 화면 결과에 나온 [ ] 대괄호가 들어있는 결과가 나온다면 그것은 주소값이나온 것임. (가장중요함!!)-->

<!-- //자바에서의 문자열 분리
String.split()
stringTokenizer -->

<h3>** 인덱스 2번째 데이터 출력 **</h3>
결과 = ${requestScope.list[2] }  <%-- 결과 = ${list[2] } 로도 사용할 수 있다. --%> 
<br>

<h3>** list2의 주소 출력 **</h3>
결과 = ${list2 } 
<br>
<!-- list2 안에는 PersonDTO 가 들어있으므로 PersonDTO 의 주소값이 출력되게 된다. 하지만 list1 에는 String 타입의객체들이 들어있으므로 list가 주소값이 나오는 것이 아닌 문자열이 나오게 되는 것이다 -->

<!-- for(PersonDTO personDTO : list2){

} 아래의 문장은 자바의 향상된 포문을 작성한 것이다.-->
<h3>** list2의모든 데이터 출력 **</h3>
<c:forEach var="personDTO" items="${list2 }">
	이름 = ${personDTO.getName() } 나이 = ${personDTO.getAge() }<br>
</c:forEach>
<br>

<h3>** 메소드명을 변수명처럼 사용 **</h3>
<c:forEach var="personDTO" items="${list2 }" varStatus="i">
	index = ${i.index} &emsp; count = ${i.count} &emsp; 이름 = ${personDTO.name } &emsp; 나이 = ${personDTO.age }<br> 
	<!-- personDTO 의 name 필드와 age 필드는 private 이다. 직접 가져올 수 없다. 여기서 가리키는 name과 age 는 getName()과 getAge() 를 변수명 처럼 사용을 하고 있는 것이다 -->
</c:forEach>
<br>

</body>
</html>