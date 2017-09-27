/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flower.bd.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flower.bd.po.MessageInfo;
import com.flower.bd.po.UserInfoPo;
import com.flower.bd.service.UserInfoService;
import com.flower.bd.util.GlobalConstant;


@Controller
public class WelcomeController {

		
	@Autowired
	private UserInfoService userInfoService;
	

	@RequestMapping("/")
	public String welcome( Map<String, Object> model) {
		return "login";
	}
	
		
	@RequestMapping(value="/loginCheck",method=RequestMethod.POST )
	public String login(HttpServletRequest req,Map<String, Object> model) {
		//检查用户是否存在
		String userid = req.getParameter("username");
		String pwd    = req.getParameter("password");
		UserInfoPo userInfoPo = userInfoService.getUserInfoPo(userid, pwd);
		if (userInfoPo != null) {
			HttpSession session = req.getSession();
			session.setAttribute(GlobalConstant.USER_INFO, userInfoPo);
			
			return "redirect:/index";
			
		} else {
			model.put("message", "用户名密码错");
			return "forward:/";
		}
	}
	
	
	@RequestMapping("/logout")
	public String logOut(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		UserInfoPo userinfo = (UserInfoPo) session.getAttribute(GlobalConstant.USER_INFO);
		if (userinfo != null){
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/pwdChg")
	public @ResponseBody MessageInfo passwordChange(HttpServletRequest req) {
		
		String userId = ((UserInfoPo)req.getSession().getAttribute(GlobalConstant.USER_INFO)).getUserid();
		String oldPwd = req.getParameter("oldPwd");
		String newPwd = req.getParameter("newPwd");
		
		boolean flag = userInfoService.changePwd(userId, oldPwd,newPwd);
		MessageInfo mi = new MessageInfo();
		if (flag) {
			mi.setResult(flag);
			mi.setInfo("密码修改成功.");
		} else {
			mi.setResult(flag);
			mi.setInfo("原密码不正确，请您重新输入.");
		}
		return mi;
		
	}
}
