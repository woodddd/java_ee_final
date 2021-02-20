//빈 객체 선언
var calc = {} /*Calc calc = new Calc(); 자바에서의  calc 객체와 동일하다.*/

//맴버변수 추가
calc.x = 0;
calc.y = 0;

//맴버함수
calc.setValue = function(p1,p2){ //public void setvalue(int p1, int p2) 랑 같은것임.
   this.x = p1;
   this.y = p2;
   
}

calc.plus = function(){
   return this.x + this.y;
   
}

calc.minus = function(){
   return this.x - this.y;
   
}

calc.result = function(){
   var value1 = this.plus();
   var value2 = this.minus();
   
   document.write("<p>덧셈 = " + value1 + "</p>");
   document.write("<p>뺄셈 = " + value2 + "</p>");
   
}


























