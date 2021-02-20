package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable; // Throwable 은 Exception 의 부모이다. 그러므로 모든 에러를처리할 수 있다.
	
	
}
