package core.jdbc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	// session에 userId가 있는 지 확인하는 메서드
	public static boolean isEmpty(HttpSession session, String key) {
		Object object=session.getAttribute(key);
		if(object==null) {
			return true;
		}
		return false;
	}	
	// session에 있는 userId를 반환하는 메서드
	public static String getStringValue(HttpSession session, String key) {
		if(isEmpty(session, key)) {
			return null;
		}
		return(String)session.getAttribute(key);
	}
}
