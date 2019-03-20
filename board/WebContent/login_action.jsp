<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.user.User, com.board.db.Database" %>
<%
	String userId =request.getParameter("userID");
	String password =request.getParameter("password");
	
	User user=Database.findByUserID(userId);
	if(password.equals(user.getPassword()));
%>