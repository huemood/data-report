/**
 * 
 */
package com.flower.bd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.bd.po.OrgPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.service.OrgService;

/**
 * @author bc
 *
 */
@Controller
public class OrgController {
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value="/getOrgList",method=RequestMethod.POST)
	public @ResponseBody List<OrgPo> getOrgList(HttpServletRequest req,@RequestParam("initId") String initId) {
		String id = req.getParameter("id");
		return orgService.getOrgList(id, initId);
	}
	
//	@RequestMapping(value="/getAllTerm",method=RequestMethod.GET)
//	public @ResponseBody List<TermPo> getAllTermPo() {
//		return orgService.getAllTermPo();
//	}

}
