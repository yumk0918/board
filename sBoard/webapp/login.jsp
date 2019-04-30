<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
	<%@ include file="./common/_head.jspf"%>
  </head>
  <body>
	<%@ include file="./common/_navbar.jspf"%>
	
	<div class="jumbotron jumbotron">
	<div class="container">
	<h1 class="text-center">로그인</h1>
	<p></p>
	<form action="./login_action.jsp" method="post">
	
	  <div class="form-group mt-5">
	    <label for="userId">ID</label>
	    <input type="text" name="userId" class="form-control" placeholder="Enter ID">
	  </div>
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" name="password" class="form-control" placeholder="Enter Password">
	  </div>
	  
	  <button type="submit" class="mt-5 mb-2 btn btn-primary btn-lg btn-block">Login</button>
<% 
	// 전달받은 errorMessage를 저장
	// errorMessage가 null이 아닌 경우에만 화면에 에러 메시지가 보이게 한다.
	Object errorMessage=request.getAttribute("errorMessage");
	if(errorMessage !=null)
	{
%>
	<small id="error" class="form-text text-center text-danger"><%=errorMessage%></small>
<%
	}
%>	
	</form>

	</div>
	</div>
	
	<%@ include file="./common/_body.jspf"%>
 </body>
</html>