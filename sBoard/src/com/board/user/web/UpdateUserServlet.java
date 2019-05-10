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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.board.user.User;
import com.board.user.UserDAO;

import core.jdbc.MyValidatorFactory;
import core.jdbc.SessionUtils;

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
		
		// 자바빈 매핑 (commons-beanutils)
		User user=new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException|InvocationTargetException e1) {
			throw new ServletException(e1);
		}
		
		// session에 있는 userId와 User객체에 저장된 userId를 비교
		if(!user.isSameUser(sessionUserId)) {
			response.sendRedirect("/sBoard");
			return;
		}
		
		UserDAO userDAO=new UserDAO();		
		// 유효성 확인하기
		Validator validator=MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if(constraintViolations.size()>0) { // 에러 처리
			// 개인정보수정이라는 것을 인식시킴
			request.setAttribute("isUpdate", true);
			request.setAttribute("user", user); // 사용자가 입력한 값을 그대로 전달
			String errorMessage=constraintViolations.iterator().next().getPropertyPath()+" : "+constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
				return;
		}
				
		userDAO.updateUser(user);
		response.sendRedirect("/sBoard");
	}
	// 개입정보수정 시 페이지 이동과 에러메시지를 전달하는 메서드
	public void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		
		RequestDispatcher rd=request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
