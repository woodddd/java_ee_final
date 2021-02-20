function checkWrite(){
	
	 document.getElementById("nameDiv").innerText = "";
     document.getElementById("idDiv").innerText = "";
	
	if(document.getElementById("name").value==""){
		document.getElementById("nameDiv").innerText = "이름을 입력하세요.";
	}else if(document.getElementById("id").value ==""){
		document.getElementById("idDiv").innerText = "아이디를 입력하세요.";
	}else if(document.writeForm.pwd.value == ""){
		alert("비밀번호를 입력해주세요.");
	}else if(document.writeForm.pwd.value != document.writeForm.repwd.value){
		alert("비밀번호가 일치하지 않습니다.");
	}else if(document.writeForm.check.value != document.writeForm.id.value){
		alert("중복확인을 하지 않았습니다.");
	}else{
		document.writeForm.submit();
	}
}


function checkId(){
	let id = document.getElementById("id").value;
	
	if(id == "")
		document.getElementById("idDiv").innerText = "먼저 아이디를 입력하세요";
	else
		window.open("/myproject/member/checkId.do?id="+id,'idcheck',"width = 450 height = 100");
}

function checkPost(){
	window.open("checkPost.do","","width=700 height=500 scrollbars=yes");
}