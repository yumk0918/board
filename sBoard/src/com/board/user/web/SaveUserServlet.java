package com.board.user.web;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.user.User;
import com.board.user.UserDAO;

import core.jdbc.CharacterEncodingFilter;
import core.jdbc.MyValidatorFactory;

@WebServlet("/users/save")
public class SaveUserServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(SaveUserServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 자바빈 매핑 (commons-beanutils)
		User user=new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException|InvocationTargetException e1) {
			throw new ServletException(e1);
		}
		// 로그 메시지
		// level이 debug일 때만 실행 (약간의 성능 향상 -> 가독성이 떨어짐)
		// LOG4J : if(logger.isDebugEnabled()) { 
		logger.debug("User {}:",user);
		
		// 유효성 확인하기
		Validator validator=MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if(constraintViolations.size()>0) { // 에러 처리
			request.setAttribute("user", user); // 사용자가 입력한 값을 그대로 전달
			String errorMessage=constraintViolations.iterator().next().getPropertyPath()+" : "+constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
			return;
		}
		
		UserDAO userDAO = new UserDAO();
		userDAO.addUser(user);
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
