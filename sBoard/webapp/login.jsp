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
	<form>
	
	  <div class="form-group mt-5">
	    <label for="userId">ID</label>
	    <input type="text" class="form-control" placeholder="Enter ID">
	  <!--   <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
	  </div>
	  <div class="form-group">
	    <label for="userPs">Password</label>
	    <input type="password" class="form-control" placeholder="Enter Password">
	  </div>
	  <button type="submit" class="mt-5 btn btn-primary btn-lg btn-block">Login</button>
	</form>

	</div>
	</div>
	
	<%@ include file="./common/_body.jspf"%>
 </body>
</html>