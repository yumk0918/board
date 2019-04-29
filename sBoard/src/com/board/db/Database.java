package com.board.db;

import java.util.*;
import com.board.user.User;

public class Database {
	// static : 계속 저장
	// 키는 String으로 하면 되지만, 값은 4가지 정보를 담아야 한다.
	// 4가지 정보를 담을 class(객체)를 만들어야 한다.
	private static Map<String, User> users=new HashMap<String, User>();
	
	// User 객체에 값을 저장하는 메서드
	public static void addUser(User user) {
		
		// userId를 key값으로, User클래스 객체를 value로 저장
		// userId는 중복 X -> 예외처리
		users.put(user.getUserId(), user);
		
		System.out.println("user : "+user.toString());
	}
}
