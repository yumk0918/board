<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session에 userId를 삭제한 후 index.jsp로 이동 
	session.removeAttribute("userId");
	response.sendRedirect("/sBoard");
%>