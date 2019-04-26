<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style type="text/css">
    div.jumbotron{
		margin-top: 5%;
		margin-left: 15%;
    	width: 70%;
    	height: 80%;
    }
    body div.jumbotron{
        font-size: 1.3rem;
    }
     div.jumbotron div.container{
    	width: 60%;
    } 

	</style>
    <title>sBoard</title>
  </head>
  <body>
	<!-- Navbar -->
		<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
	  <a class="navbar-brand mb-0 h1 ml-5" href="/sBoard/index.jsp">sBoard</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="/sBoard/form.jsp">회원가입<span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/sBoard/login.jsp">로그인</a>
	      </li>
	     <li class="nav-item">
	        <a class="nav-link" href="#">개인정보수정</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">로그아웃</a>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0">
	      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
	      <button class="btn btn-outline-secondary mr-5 my-2 my-sm-0" type="submit">Search</button>
	    </form>
	  </div>
	</nav>
	<div class="jumbotron jumbotron">
	<div class="container">
	<h1 class="text-center">회원가입</h1>
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
	  <div class="form-group">
	    <label for="userName">Name</label>
	    <input type="text" class="form-control" placeholder="Enter Name">
	  </div>
	  <div class="form-group">
	    <label for="userEmail">Email address</label>
	    <input type="email" class="form-control" placeholder="Enter Email">
	  </div>
	  <button type="submit" class="mt-5 btn btn-primary btn-lg btn-block">Submit</button>
	</form>

	</div>
	</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>