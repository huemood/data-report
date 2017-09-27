/**
 * 
 */
package com.flower.bd.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * 如何添加多个filter,并排序？
 * @author bc
 *
 */
@Configuration
public class WebConfiguration {
	
	@Bean
	public FilterRegistrationBean myFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		registration.setFilter(authenticationFilter);
		
		List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/*");
        registration.setUrlPatterns(urlPatterns);
        
		
		return registration;
	}

	
}
