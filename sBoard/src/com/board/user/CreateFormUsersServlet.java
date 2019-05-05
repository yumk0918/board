package com.board.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/createForm")
public class CreateFormUsersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", new User()); // User의 빈 객체를 생성
		RequestDispatcher rd=request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
