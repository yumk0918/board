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
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="jumbotron" style="padding-top:20px;">
				<form method="post" class="form-horizontal" action="/users/save">
				<h2 style="text-align:center">회원가입 화면</h2>
					<div class="form-group">
						<label  class="control-label col-sm-2" for="userID">아이디 : </label>
						<div class="col-sm-10">
							<input type="text" class="form-control"  name="userID" maxlength="20">
						</div>
					</div>
					
					<div class="form-group">
					<label  class="control-label col-sm-2" for="password">비밀번호 : </label>
						<div class="col-sm-10">
							<input type="password" class="form-control"  name="password" maxlength="20">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="control-label col-sm-2" for="name">이름 : </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="name" maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label  class="control-label col-sm-2" for="email">이메일 : </label>
						<div class="col-sm-10">
							<input type="email" class="form-control"name="email" maxlength="20">
						</div>
					</div>
					<input type="submit" class="btn btn-primary btn-lg form-control" value="회원가입">								

				</form>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
<%@ include file="./commons/_body.jspf" %>
</body>
</html>