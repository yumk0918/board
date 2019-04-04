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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UpdateFormUserServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Object object=session.getAttribute(LoginServlet.SESSION_USER_ID);
		if(object==null) {
			response.sendRedirect("/");
			return;
		}
		String userId=(String)object;
		logger.debug("User Id {} ",userId);
		UserDAO userDao=new UserDAO();
		try {
			User user=userDao.findByUserId(userId);
			request.setAttribute("isUpdate", true);
			request.setAttribute("user", user);
			RequestDispatcher rd=request.getRequestDispatcher("/form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
