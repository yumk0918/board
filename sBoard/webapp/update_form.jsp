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
	<h1 class="text-center">개인정보수정</h1>
	<p></p>
	
	<!-- 수정된 내용을 서버에 전달해서 저장 -->
	<form action="/sBoard/users/update" method="post">
	<!-- 수정할 경우 서버로 값이 전달 -->
	  <input type="hidden" name="userId" value="${user.userId}"/>
	  <div class="form-group mt-5">
	    <label for="userId">사용자 아이디</label><br>
	    ${user.userId}
	  </div>
	  <div class="form-group">
	    <label for="password">비밀번호</label>
	    <input type="password" name="password" class="form-control"  value="${user.password}"> <!-- 전달받은 User객체의 getPasswor()-->
	  </div>
	  <div class="form-group">
	    <label for="name">이름</label>
	    <input type="text" name="name" class="form-control" value="${user.name}">
	  </div>
	  <div class="form-group">
	    <label for="email">이메일</label>
	    <input type="email" name="email" class="form-control"  value="${user.email}">
	  </div>
	  <button type="submit" class="mt-5 btn btn-primary btn-lg btn-block">Submit</button>
	</form>

	</div>
	</div>
	 	<%@ include file="./common/_body.jspf"%>
    </body>
</html>