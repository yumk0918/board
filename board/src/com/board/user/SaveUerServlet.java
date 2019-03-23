package com.board.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.Database;


@WebServlet("/users/save")
public class SaveUerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SaveUerServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId =request.getParameter("userID");
		String password =request.getParameter("password");
		String name =request.getParameter("name");
		String email =request.getParameter("email");
		System.out.print(name);
		User user=new User(userId, password, name, email);
		UserDAO userDAO=new UserDAO();
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/");
	}

}
