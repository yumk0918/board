<%@page import="com.board.user.UserNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.user.*, com.board.db.Database" %>
<%
	String userId=request.getParameter("userId");
	String password=request.getParameter("password");
	try{
		User.login(userId, password);
		
		// 로그인 성공 : 로그인 상태 정보를 유지 (session)
		session.setAttribute("userId", userId);
		
		// 로그인 성공 : index.jsp로 이동 -> navbar : 로그아웃으로 변환
		response.sendRedirect("/sBoard");	
	}catch(UserNotFoundException e){
		// 사용자 존재 X :  페이지 이동 + 담은 데이터를 전달 
		// session은 서버에 메모리 상에 데이터가 쌓임 -> 비용 발생
		request.setAttribute("errorMessage", "존재하지 않는 사용자입니다. 다시 로그인하세요.");
		
		RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
		
	}catch(PasswordMismatchExpcetion e){
		// 비밀번호가 틀릴 경우 : 페이지 이동 + 담은 데이터를 전달 
		request.setAttribute("errorMessage", "비밀번호가 틀립니다. 다시 로그인하세요.");

		RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
%>