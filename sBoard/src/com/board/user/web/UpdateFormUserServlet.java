package com.board.user.web;

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

import com.board.user.User;
import com.board.user.UserDAO;

import core.jdbc.CharacterEncodingFilter;
import core.jdbc.SessionUtils;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UpdateFormUserServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session에 userId가 있는지 확인하고 없다면 index.jsp이동
		HttpSession session=request.getSession();

		// session에 있는 userId를 가져오기
		String userId=SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if(userId==null) {
			response.sendRedirect("/sBoard");
			return;
		}
		logger.debug("User Id : {}",userId);
		UserDAO userDao = new UserDAO();
		User user = userDao.findByUserId(userId);
		// 개인정보수정이라는 것을 인식시킴
		request.setAttribute("isUpdate", true);
		request.setAttribute("user", user); // User객체를 전달
		RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	
		
	}
}
