function log(msg){
	var console = document.getElementById("debugConsole");//div태그
	if(console != null){
		console.innerHTML += msg + "<br/>";
	}
}
