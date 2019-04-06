package com.board.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.user.PasswordMismatchException;
import com.board.user.User;
import com.board.user.UserNotFoundException;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {
	public static final String SESSION_USER_ID = "userId";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =request.getParameter(SESSION_USER_ID);
		String password =request.getParameter("password");
		try{
			User.login(userId, password);
			// session�� ������ �� ���� (Servlet)
			HttpSession session=request.getSession();
			session.setAttribute(SESSION_USER_ID, userId);
			response.sendRedirect("/");
		}catch(UserNotFoundException e){
			forwardJSP(request, response, "�������� �ʴ� ������Դϴ�. �ٽ� �α����ϼ���.");
		 }catch(PasswordMismatchException e){
			 forwardJSP(request, response, "��й�ȣ�� Ʋ���ϴ�. �ٽ� �α����ϼ���.");
		 }
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response,String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

}
