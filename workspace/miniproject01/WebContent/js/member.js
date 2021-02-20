function checkWrite(){
         document.getElementById("nameDiv").innerText = "";
         document.getElementById("idDiv").innerText = "";
	  
         
         
         if(document.getElementById("name").value== "")  //alert("이름을 입력하세요");
            document.getElementById("nameDiv").innerText = "이름을 입력하세요";
         else if(document.getElementById("id").value== "" ) //alert("아이디를 입력하세요");
            document.getElementById("idDiv").innerText = "아이디를 입력하세요";
         else if(document.writeForm.pwd.value == "" ) alert("비밀번호를 입력하세요");
         else if(document.writeForm.pwd.value != document.writeForm.repwd.value) alert("비밀번호가 맞지 않습니다");
		 else if(document.writeForm.check.value != document.writeForm.id.value) alert("중복확인을 하지 않았습니다.")
         else document.writeForm.submit();
         
}

function checkModify(){
         document.getElementById("nameDiv").innerText = "";
	  
         if(document.getElementById("name").value== "")  //alert("이름을 입력하세요");
            document.getElementById("nameDiv").innerText = "이름을 입력하세요";
         else if(document.modifyForm.pwd.value == "" ) alert("비밀번호를 입력하세요");
         else if(document.modifyForm.pwd.value != document.modifyForm.repwd.value) alert("비밀번호가 맞지 않습니다");

         else document.modifyForm.submit();
         
}



//function inputIdChk(){
//	document.writeForm.idDuplication.value ="idUncheck";
//}



function login(){
   if(document.loginForm.id.value == "" ) alert("아이디를 입력하세요");
   else if(document.loginForm.pwd.value == "" ) alert("비밀번호를 입력하세요");
   else document.loginForm.submit();
}

function signUp(){
   location.href = "http://localhost:8080/memberServlet/member/writeForm.html";

}

function checkId(){
   //변수설정
   //-자료형이 없다
   //var는 불편해서 let을 사용함
   let id = document.getElementById("id").value;
   //console.log("중복체크");
   if(id =="")
      document.getElementById("idDiv").innerText = "먼저 아이디를 입력하세요";
   else 
      /*window.open("checkId.jsp?id="+ id, 'idcheck',"width = 450 height = 100"); //작은따옴표 큰따옴표 구분을 따로 하지 않는다.*/
	  window.open("/miniproject01/member/checkId.do?id=" + id, 'idcheck',"width = 450 height = 100");
		
}

function sendCheckValue(){
	opener.document.writeForm.idDuplication.value="idCheck";
}

function checkPost(){
	window.open("checkPost.do","","width=700 height=500 scrollbars=yes");
}


