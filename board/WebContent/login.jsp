<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./commons/_head.jspf" %>
</head>
<body>
<%@ include file="./commons/_nav.jspf" %>
	<div class="container">
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<div class="jumbotron" style="padding-top:20px;">
				<form method="post" action="/users/login">
						<h2 style="text-align:center">로그인 화면</h2>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userId" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary form-control" value="로그인">								
					</div>

					<c:if test="${ not empty errorMessage}">
					<div class="form-group">
						<label class="error" style="color:red;">${errorMessage}</label>
					</div>
					</c:if>

				</form>
			</div>
		</div>
		<div class="col-lg-3"></div>
	</div>
<%@ include file="./commons/_body.jspf" %>
</body>
</html>