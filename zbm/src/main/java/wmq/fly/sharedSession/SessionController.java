package wmq.fly.sharedSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@Value("${server.port}")
	private String PORT;

	@RequestMapping("/index")
	public String index() {
		return "index:" + PORT;
	}

	
	@RequestMapping("/setSession")
	public String setSession(HttpServletRequest request, String sessionKey, String sessionValue) {
		
		HttpSession session = request.getSession(true);
		session.setAttribute(sessionKey, sessionValue);
		return "success,port:" + PORT;
	}

	
	@RequestMapping("/getSession")
	public String getSession(HttpServletRequest request, String sessionKey) {
		HttpSession session =null;
		try {
		 session = request.getSession(false);
		} catch (Exception e) {
		  e.printStackTrace();
		}
		String value=null;
		if(session!=null){
			value = (String) session.getAttribute(sessionKey);
		}
		return "sessionValue:" + value + ",port:" + PORT;
	}

}