<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.user.User, com.board.db.Database" %>
<%
	String userId =request.getParameter("userID");
	String password =request.getParameter("password");
	
	User user=Database.findByUserID(userId);
	if(user==null){
		// 사용자가 존재하지 않는다는 것을 에러 메시지
	}
	if(password.equals(user.getPassword())){
		// 로그인 처리 -> user객체에게 password를 보내서 물어보기 
	}
%>