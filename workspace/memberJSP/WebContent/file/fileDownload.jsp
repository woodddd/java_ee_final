<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.File"%>    
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>

<%
String fileName = request.getParameter("fileName");

String realFolder = request.getServletContext().getRealPath("/storage");//실제폴더(서버) 로부터 데이터 경로를 읽어온다.

//다운로드 받을 파일
File file = new File(realFolder, fileName);//해당 경로에 존재하는 파일이름을 가져와서 file 에 저장해라.
fileName = URLEncoder.encode(fileName,"UTF-8").replace("+"," ");//파일명에 띄어쓰기가 있다면 읽어올때 눈이+펑펑+옵니다 로 읽어오기 때문에 +를 띄어쓰기로 변환해준것.
//replace(바꿀대상,바꿀문자)
System.out.println("file = " + file);

out.clear();//jsp내장객체로 생성한 out객체를 초기화.
out = pageContext.pushBody(); //현재 페이지에서 out객체를 새롭게 생성한다 , pageContext(현재페이지 내에서)

response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
response.setHeader("Content-Length", file.length()+"");

/*
getOutputStream() has already been called for this response
JSP에서는 SERVLET으로 변환될 때 내부적으로 out객체가 자동으로 생성하기 때문에 out객체를 만들면 충돌이 일어나서 저런 메시지가 뜨는 것이다.
그래서 먼저 out를 초기화 하고 생성하면 된다. 
*/

BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

int size = (int)file.length();
byte[] b = new byte[size];
bis.read(b,0,size);//0부터 size(파일의 크기만큼) 읽어와라.
bos.write(b); // 출력파일로 보내기

bis.close();
bos.close();
%>
