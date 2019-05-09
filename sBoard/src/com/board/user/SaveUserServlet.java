package com.board.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

import org.apache.commons.beanutils.BeanUtilsBean;

import com.board.support.MyValidatorFactory;

@WebServlet("/users/save")
public class SaveUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 자바빈 매핑 (commons-beanutils)
		User user=new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException|InvocationTargetException e1) {
			throw new ServletException(e1);
		}
		
		// 유효성 확인하기
		Validator validator=MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if(constraintViolations.size()>0) { // 에러 처리
			request.setAttribute("user", user); // 사용자가 입력한 값을 그대로 전달
			String errorMessage=constraintViolations.iterator().next().getPropertyPath()+" : "+constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
			return;
		}
		
		UserDAO userDAO=new UserDAO();
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/sBoard");
	}
	
	// 회원가입 시 페이지 이동과 에러메시지를 전달하는 메서드
	public void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		
		RequestDispatcher rd=request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
