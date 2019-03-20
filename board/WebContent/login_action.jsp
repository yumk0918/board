<%@page import="com.board.user.PasswordMismatchException"%>
<%@page import="com.board.user.UserNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.user.User, com.board.db.Database" %>
<%
	String userId =request.getParameter("userID");
	String password =request.getParameter("password");
	
/* 	UserTest에 구현함
	User user=Database.findByUserID(userId);
	if(user==null){
		// 사용자가 존재하지 않는다는 것을 에러 메시지
	}
	if(password.equals(user.getPassword())){
		// 로그인 처리 -> user객체에게 password를 보내서 물어보기 
	} */
	try{
		User.login(userId, password);
		session.setAttribute("userId", userId);
		response.sendRedirect("/board/index.jsp");
	}catch(UserNotFoundException e){
		request.setAttribute("errorMessage", "존재하지 않는 사용자입니다. 다시 로그인하세요.");
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	 }catch(PasswordMismatchException e){
		request.setAttribute("errorMessage", "비밀번호가 틀립니다. 다시 로그인하세요.");
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	 }
%>