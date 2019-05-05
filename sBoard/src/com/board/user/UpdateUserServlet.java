package com.board.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인한 후 개인정보수정을 할 수 있게 만들기 위해 
		// 로그인이 되어 있는지 확인 -> session에 userId가 있는지 확인
		// session에 userId가 있는지 확인하고 없다면 index.jsp이동
		HttpSession session=request.getSession();

		// 내 계정이 아닌데 수정이 가능함 -> session에 저장된 userId와 같은지 확인
		// session에 있는 userId를 가져오기
		String sessionUserId=SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);		
		if(sessionUserId==null) {
			response.sendRedirect("/sBoard");
			return;
		}
		
		String userId=request.getParameter("userId");
		if(!sessionUserId.equals(userId)) {
			response.sendRedirect("/sBoard");
			return;
		}
		
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		
		User user=new User(userId, password,name,email);
		UserDAO userDAO=new UserDAO();
		
		try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/sBoard");
	}

}
