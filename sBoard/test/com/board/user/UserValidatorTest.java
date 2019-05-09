package com.board.user;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.board.user.User;

public class UserValidatorTest {
	
	private static Validator validator;
	  @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }
	   @Test // userId가 null일 경우 
	   public void UserIdIsNull() {
	      User user = new User( null, "password","name","");
	      Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
	      assertEquals( 1, constraintViolations.size() );
	      System.out.println(constraintViolations.iterator().next().getMessage());
	   }
	   @Test 
	   public void userIdLength() {
		   // userId의 길이가 4미만일 경우 경우
		   User user = new User( "un", "password","name","");
		   Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		   assertEquals( 1, constraintViolations.size() );
		   System.out.println(constraintViolations.iterator().next().getMessage());
		   
		   // userId의 길이가 12초과일 경우 경우
		   user = new User( "1234567891011", "password","name","");
		   constraintViolations = validator.validate(user);
		   assertEquals( 1, constraintViolations.size() );
		   System.out.println(constraintViolations.iterator().next().getMessage());
	  
	   }
	   @Test // email형식이 잘못 될 경우 
	   public void email() {
	      User user = new User( "userId", "password","name","email");
	      Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
	      assertEquals( 1, constraintViolations.size() );
	      System.out.println(constraintViolations.iterator().next().getMessage());
	   }
	   @Test // 유효하지 않은 데이터가 여러개 일 경우 
	   public void invalidUser() {
	      User user = new User( "us", "password","name","email");
	      Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
	      assertEquals( 2, constraintViolations.size() );
	      Iterator<ConstraintViolation<User>> violations= constraintViolations.iterator();
	      while(violations.hasNext()) {
	    	  ConstraintViolation<User> each=violations.next();
	    	  System.out.println(each.getMessage());
	      }
	   }

}
