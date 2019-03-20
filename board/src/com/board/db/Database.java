package com.board.db;

import java.util.HashMap;
import java.util.Map;

import com.board.user.User;

public class Database {
	// static 메모리 어딘가에 사용자 정보를 저장하기 위해
	private static Map<String, User> users=new HashMap<String, User>(); 
	public static void addUser(User user) {
		users.put(user.getUserId(),user);
		System.out.println("user :"+ user);
	}
	public static User findByUserID(String userId) {
		return users.get(userId);
	}
}
