<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.user.User, com.board.db.Database" %>
<%
	String userId=request.getParameter("userId");
	String password=request.getParameter("password");
	
	// 로그인 성공 : 로그인 상태 정보를 유지 (session)
	if(User.login(userId, password)){
		session.setAttribute("userId", userId);
	}
	// 로그인 성공 : index.jsp로 이동 -> navbar : 로그아웃으로 변환
	response.sendRedirect("/sBoard");
%>