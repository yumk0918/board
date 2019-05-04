package com.board.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {

	public static final String SESSION_USER_ID = "userId";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter(SESSION_USER_ID);
		String password=request.getParameter("password");
		
		
		try{
			User.login(userId, password);
			
			// JSP에서는 기본적으로 session을 가지고 있음
			// 서블릿에서는 request에서 담긴 session을 얻어 올 수 있음
			HttpSession session=request.getSession();
			// 로그인 성공 : 로그인 상태 정보를 유지 (session)
			session.setAttribute(SESSION_USER_ID, userId);
			
			// 로그인 성공 : index.jsp로 이동 -> navbar : 로그아웃으로 변환
			response.sendRedirect("/sBoard");	
		}catch(UserNotFoundException e){
			// 사용자 존재 X 
			forwardJSP(request, response, "존재하지 않는 사용자입니다. 다시 로그인하세요.");
			
		}catch(PasswordMismatchExpcetion e){
			// 비밀번호가 틀릴 경우
			forwardJSP(request, response, "비밀번호가 틀립니다. 다시 로그인하세요.");

		}
	}
	// 로그인 시 페이지 이동과 에러메시지를 전달하는 메서드 (중복 제거)
	// session은 서버에 메모리 상에 데이터가 쌓임 -> 비용 발생 -> forward이용
	public void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		
		RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

}
