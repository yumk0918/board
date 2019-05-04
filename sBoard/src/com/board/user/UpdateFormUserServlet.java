package com.board.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session에 있는 userId를 가져 옴
		HttpSession session=request.getSession();
		Object object=session.getAttribute(LoginServlet.SESSION_USER_ID);
		if(object==null) {
			response.sendRedirect("/sBoard");
			return;
		}
		String userId=(String)object;
		System.out.println("User Id : "+userId);
		UserDAO userDao=new UserDAO();
		try {
			User user=userDao.findByUserId(userId);
			request.setAttribute("user", user); // User객체를 전달
			RequestDispatcher rd=request.getRequestDispatcher("/update_form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) { }
		
	}
}
