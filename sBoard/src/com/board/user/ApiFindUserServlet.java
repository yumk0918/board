package com.board.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/api/users/find")
public class ApiFindUserServlet extends HttpServlet {
	//userId를 주면 데이터베이스에서 리턴 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		if(userId==null) {
			response.sendRedirect("/sBoard");
			return;
		}
		UserDAO userDao=new UserDAO();
		try {
			User user=userDao.findByUserId(userId);
			if(user==null) {
				return;
			}
			
			// user를 gson형식으로 변환
			final GsonBuilder builder =new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			final Gson gson=builder.create();
			
			String jsonData=gson.toJson(user);
			
			// 클라이언트에게 Content-Type을 알려줌
			response.setContentType("application/json;charset=utf-8");
			
			PrintWriter out=response.getWriter(); 
			out.print(jsonData);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
