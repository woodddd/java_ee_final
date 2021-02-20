function checkWrite(){
	
	//초기화를 해 주게 되면 다시 버튼이 눌렸을 때, 이미 생성되었던 이너텍스트가 초기화 된 후, 다시 조건을 판별해서 생성함 
	
	document.getElementById("nameDiv").innerText =  "";
	document.getElementById("idDiv").innerText = "";
 	//if(document.writeForm.name.value=="") 
	if(document.getElementById("name").value=="")
 		//alert("이름을 입력하세요");
	document.getElementById("nameDiv").innerText = "이름을 입력하세요";
 	//else if(document.writeForm.id.value=="")
	else if(document.getElementById("id").value=="") 
 		document.getElementById("idDiv").innerText = "id를 입력하세요";
 	else if(document.writeForm.pwd.value=="") 
 		alert("비밀번호를 입력하세요");
 	else if(document.writeForm.pwd.value != document.writeForm.repwd.value) 
 		alert("비밀번호가 맞지 않습니다");
 	else 
 		document.writeForm.submit();
}

function page_write(){
      
   location.href = "http://localhost:8080/memberServlet/member/writeForm.html";
}

function checkLogin(){
	document.getElementById("loginidDiv").innerText = "";
	document.getElementById("loginpwdDiv").innerText = "";
	
	if(document.getElementById("loginid").value==""){
		document.getElementById("loginidDiv").innerText = "id를 입력하세요";
	}else if(document.getElementById("loginpwd").value==""){
		document.getElementById("loginpwdDiv").innerText = "비밀번호를 입력하세요";
	}else{
		document.loginForm.submit();
	}
	
	
	
	
}

function checkPost(){
	console.log("우편번호");
	
	 new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        //extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        //extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();

}