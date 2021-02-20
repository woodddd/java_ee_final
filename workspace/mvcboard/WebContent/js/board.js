function checkBoardWrite(){
	document.getElementById("subjectDiv").innerText = "";
	document.getElementById("contentDiv").innerText = "";
	
	if(document.getElementById("subject").value==""){
		document.getElementById("subjectDiv").innerText = "제목을 입력하세요.";
	}else if(document.getElementById("content").value==""){
		document.getElementById("contentDiv").innerText = "내용을 입력하세요.";
	}else{
		document.boardForm.submit();
	}
}

function checkBoardReply(){
	document.getElementById("subjectDiv").innerText = "";
	document.getElementById("contentDiv").innerText = "";
	
	if(document.getElementById("subject").value==""){
		document.getElementById("subjectDiv").innerText = "제목을 입력하세요.";
	}else if(document.getElementById("content").value==""){
		document.getElementById("contentDiv").innerText = "내용을 입력하세요.";
	}else{
		document.boardReplyForm.submit();
	}
}