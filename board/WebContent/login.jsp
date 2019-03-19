<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./commons/_head.jspf" %>
</head>
<body>
<%@ include file="./commons/_nav.jspf" %>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top:20px;">
				<form method="post" action="login_action.jsp">
				<h2 style="text-align:center">로그인 화면</h2>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="로그인">								
					<h4 style="text-align:right;"><a href="join.jsp">회원가입</a></h4>
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
<%@ include file="./commons/_body.jspf" %>
</body>
</html>