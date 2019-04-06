package com.board.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		User user=new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(); 
		}
		
/*		HttpSession session=request.getSession();
		String sessionUserId=SessionUtils.getStringValue(session,LoginServlet.SESSION_USER_ID);
		if(!user.isSameUser(sessionUserId)) {
			response.sendRedirect("/");
			return;
		}*/
		
		Validator validator= MyValidatorFactory.createVaildator();
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
		if(constraintViolations.size()>0) {
			request.setAttribute("user", user);
			request.setAttribute("isUpdate", true);
			String errorMessage=constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
			return;
		}
		
		UserDAO userDAO=new UserDAO();
		userDAO.updateUser(user);
		response.sendRedirect("/");
	}
	private void forwardJSP(HttpServletRequest request, HttpServletResponse response,String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd=request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
