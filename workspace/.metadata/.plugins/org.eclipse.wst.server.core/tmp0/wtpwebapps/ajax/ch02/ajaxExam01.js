var httpRequest = null;
function startMethod(){
	httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = resultProcess;
	httpRequest.open("GET","ajaxExam01.xml",true);//xml의 처리데이터를가져오겠다.
	httpRequest.send();
}

function resultProcess(){
	if(httpRequest.readyState == 4){
      if(httpRequest.status == 200){
			var div = document.getElementById("displayArea");
			
			div.innerHTML = httpRequest.responseText;
			/*div.style.visibility = "visible";*/
			div.style.display = "block";
      }
   }
}