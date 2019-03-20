<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.user.User, com.board.db.Database" %>
<%
	request.setCharacterEncoding("UTF-8");
	String userId =request.getParameter("userID");
	String password =request.getParameter("password");
	String name =request.getParameter("name");
	String email =request.getParameter("email");
	System.out.print(name);
	User user=new User(userId, password, name, email);
	Database.addUser(user);

	response.sendRedirect("/board/index.jsp");
%>