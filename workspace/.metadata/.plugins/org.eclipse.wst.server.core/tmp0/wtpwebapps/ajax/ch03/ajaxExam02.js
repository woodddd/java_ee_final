var httpRequest = null;

function startMethod(){
   httpRequest = new XMLHttpRequest();
   httpRequest.onreadystatechange = resultProcess;
   httpRequest.open("GET", "ajaxExam02.xml", true);
   httpRequest.send(null);
}

function resultProcess(){
   if(httpRequest.readyState == 4) {//데이터 수신완료
      if(httpRequest.status == 200) {//요청성공
         processXML();
      }
   }
}

function processXML(){
   //기존의 내용을 제거
   var resultDisplay = document.getElementById("resultDisplay");
	//만약 내용이 존재한다면, 내용을 모두 삭제
   while(resultDisplay.hasChildNodes()){//자식 데이터가 있다면 true.//resultDisplay의 결과로 엔터친 값들까지 포함되어 결과가 7개가 나옴.
      resultDisplay.removeChild(resultDisplay.firstChild);//엔터값, 결과값 모두 다 지움.
      
   }//while
   
   //-----------------
   var xmlDoc = httpRequest.responseXML;
   
   var trTag = "";
   var tdTag = "";
   
   var subjects = xmlDoc.getElementsByTagName("subject");
   for(var i=0; i<subjects.length; i++){
      trTag = document.createElement("tr");
   
      var childs = subjects[i].childNodes;
      for(var j=0; j<childs.length; j++){
         
         if(childs[j].firstChild != null){
            var text = childs[j].firstChild; //Text
            
            tdTag = document.createElement("td");
            tdTag.appendChild(text);
            
            trTag.appendChild(tdTag);
            
         }//if
         
         
      }//for j
      
      document.getElementById("resultDisplay").appendChild(trTag);
   }//for i
   
}