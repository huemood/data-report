/**
 * 
 */
package com.flower.bd.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flower.bd.po.UserInfoPo;
import com.flower.bd.util.GlobalConstant;



/**
 * 
 * @author bc
 *
 */
public class AuthenticationFilter implements Filter {



	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession httpSession = req.getSession();
		UserInfoPo userInfoPo = (UserInfoPo) httpSession.getAttribute(GlobalConstant.USER_INFO);
		
		if (userInfoPo == null) {
			
			String requestURI = req.getRequestURI();
			//有二种情况，是放行的
			//1.用户登录验证的提交请求，或
			//2.用户登录界面
			// 
			if ( requestURI.equals("/") || requestURI.indexOf(".")>0 || requestURI.startsWith("/loginCheck") ) {
				filterChain.doFilter(request, response);
			} else {
				//转向登录界面
				req.getRequestDispatcher("/").forward(request, response);
			}
		}else{
			filterChain.doFilter(request, response);
		}

	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	

}
