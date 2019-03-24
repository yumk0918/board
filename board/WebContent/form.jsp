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
				<c:choose>
					<c:when test="${empty user.userId }">
						<h2 style="text-align:center">회원가입</h2>
					</c:when>
					<c:otherwise>
						<h2 style="text-align:center">개인정보수정</h2>
					</c:otherwise>
				</c:choose>				
				
				<c:set var="actionUrl" value="/users/create"/>
				<c:if test="${not empty user.userId }">
					<c:set var="actionUrl" value="/users/update"/>
				</c:if>
				
				<form method="post" class="form-horizontal" action="${actionUrl }">

					<div class="form-group">
						<label  class="control-label col-sm-2" for="userId">아이디 : </label>
						<c:choose>
						<c:when test="${empty user.userId }">
							<div class="col-sm-10">
								<input type="text" class="form-control"  name="userId" maxlength="20" value="${user.userId}">
							</div>
						</c:when>
						<c:otherwise>
						<div class="col-sm-10" style="padding-top:7px">
							<input type="hidden" name="userId" value="${user.userId }"> 
							${user.userId }
						</div>
						</c:otherwise>
					</c:choose>	
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
							<input type="text" class="form-control" name="name" maxlength="20" value="${user.name}">
						</div>
					</div>
					<div class="form-group">
						<label  class="control-label col-sm-2" for="email">이메일 : </label>
						<div class="col-sm-10">
							<input type="email" class="form-control"name="email" maxlength="20" value="${user.email}">
						</div>
					</div>
					<c:set var="submitValue" value="회원가입" />
					<c:if test="${not empty user.userId }">
						<c:set var="submitValue" value="수정" />
					</c:if>
					<input type="submit" class="btn btn-primary btn-lg form-control" value="${submitValue }">								

				</form>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
<%@ include file="./commons/_body.jspf" %>
</body>
</html>