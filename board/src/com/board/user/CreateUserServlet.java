package com.board.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.board.support.MyValidatorFactory;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//4�� �̻�, 12�� ���� , ������/���ڸ� ���
		String userId =request.getParameter("userId");
		String password =request.getParameter("password");
		// 2�� �̻�, 10�� ����
		String name =request.getParameter("name");
		String email =request.getParameter("email");
		System.out.print(name);
		User user=new User(userId, password, name, email);
		
		Validator validator= MyValidatorFactory.createVaildator();
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
		if(constraintViolations.size()>0) {
			request.setAttribute("user", user);
			String errorMessage=constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
			return;
		}
		UserDAO userDAO=new UserDAO();
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/");
	}
	private void forwardJSP(HttpServletRequest request, HttpServletResponse response,String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd=request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}

}