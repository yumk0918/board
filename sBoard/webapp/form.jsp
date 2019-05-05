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
	<c:choose>
		<c:when test="${empty user.userId }">
			<h1 class="text-center">회원가입</h1>
			<c:set var="actionURL" value="/sBoard/users/save"/>
		</c:when>
		<c:otherwise>
			<h1 class="text-center">개인정보수정</h1>
			<!-- 수정된 내용을 서버에 전달해서 저장 -->
			<c:set var="actionURL" value="/sBoard/users/update"/>
		</c:otherwise>
	</c:choose>
	<p></p>
	
	<form action=${actionURL } method="post">
	  <div class="form-group mt-5">
	    <label for="userId">사용자 아이디</label>
	    
	  	<c:choose>
			<c:when test="${empty user.userId }">
			    <input type="text" name="userId" class="form-control" value="${user.userId}">
			</c:when>
			<c:otherwise>
			<!-- 수정할 경우 서버로 값이 전달 -->
			 <input type="hidden" name="userId" value="${user.userId}"/>
				<br>${user.userId}
			</c:otherwise>
		</c:choose>
	  </div>
	  <div class="form-group">
	    <label for="password">비밀번호</label>
	    <input type="password" name="password" class="form-control" value="${user.password}">
	  </div>
	  <div class="form-group">
	    <label for="name">이름</label>
	    <input type="text" name="name" class="form-control" value="${user.name}">
	  </div>
	  <div class="form-group">
	    <label for="email">이메일</label>
	    <input type="email" name="email" class="form-control" value="${user.email}">
	  </div>
	  <button type="submit" class="mt-5 btn btn-primary btn-lg btn-block">Submit</button>
	</form>

	</div>
	</div>
	 	<%@ include file="./common/_body.jspf"%>
    </body>
</html>