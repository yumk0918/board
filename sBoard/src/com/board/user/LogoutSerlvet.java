package com.board.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/logout")
public class LogoutSerlvet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session에 userId를 삭제한 후 index.jsp로 이동 
		HttpSession session=request.getSession();
		session.removeAttribute("userId");
		response.sendRedirect("/sBoard");
	}

}
