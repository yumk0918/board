package com.board.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.user.User;
import com.board.user.UserDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/api/users/find")
public class ApiFindUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String userId=request.getParameter("userId");
		if(userId==null) {
			response.sendRedirect("/");
			return;
		}
		
		UserDAO userDao=new UserDAO();
			User user=userDao.findByUserId(userId);
			if(user==null) {
				return;
			}
			final GsonBuilder builder=new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			final Gson gson=builder.create();
			
			String jsonData=gson.toJson(user);
			response.setContentType("application/json;charset=UTF-8");
			
			PrintWriter out=response.getWriter();
			out.println(jsonData);
	}

}
