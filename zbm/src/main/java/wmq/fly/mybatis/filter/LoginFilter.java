package wmq.fly.mybatis.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

//@Component
//@WebFilter(urlPatterns="/**",filterName="loginFilter")
public class LoginFilter implements Filter{
	
	//排除不拦截url
	private static final String[] excludePathPatterns = { "/stuInfo/getAllStuInfoA"};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		// 获取请求url地址，不拦截excludePathPatterns中的url
		String url = req.getRequestURI();
		if (Arrays.asList(excludePathPatterns).contains(url)) {
			//放行
			chain.doFilter(request, response);
			return;
		}
		
		System.out.println("开始拦截了................");
		//业务代码
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
