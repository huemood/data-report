/**
 * 
 */
package com.flower.bd.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bc
 *
 */


@Controller
public class IndexController {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Map<String,Object> model) {
		model.put("username", "xxx");
		return "index";
	}
/*	
	@RequestMapping(value="/teacher",method=RequestMethod.GET)
	public String teacher(Map<String,Object> model) {
		model.put("username", "xxx");
		return "teacher";
	}*/

}
