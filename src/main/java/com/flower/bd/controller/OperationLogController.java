/**
 * lx
 */
package com.flower.bd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.flower.bd.po.CoursePo;
import com.flower.bd.po.JqGridData;
import com.flower.bd.po.OperationLogPo;
import com.flower.bd.po.StudentActionPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.po.UserInfoPo;
import com.flower.bd.service.CourseService;
import com.flower.bd.service.ExcelService;
import com.flower.bd.service.LogService;
import com.flower.bd.service.OperationLogService;
import com.flower.bd.service.OrgService;
import com.flower.bd.service.StudentService;
import com.flower.bd.util.ExcelUtil;
import com.flower.bd.util.GlobalConstant;

@Controller
public class OperationLogController {
	@Autowired
	private OperationLogService olService;
	
	@RequestMapping(value="/operationLog",method=RequestMethod.GET)
	public String operationLogPage(){
		return "/course/operationLog";
	}
	
	@RequestMapping(value="/operationLogList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody JqGridData<OperationLogPo> getOperationLogList(HttpServletRequest req){
		String userID = req.getParameter("userID");
		String operationType = req.getParameter("operationType");
		String operationTimeStart = req.getParameter("operationTimeStart");
		String operationTimeEnd = req.getParameter("operationTimeEnd");
				
		int pageindex = Integer.parseInt(req.getParameter("pageindex"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		String sidx  = req.getParameter("sortName");
		String sord  = req.getParameter("sortOrder");
		
		if (StringUtils.isEmpty(sidx)) {
			sidx = "operationTime";
		}		
		if (StringUtils.isEmpty(sord)) {
			sord = "DESC";
		}
		
		OperationLogPo operationLogPo = new OperationLogPo();
		operationLogPo.setUserID(userID);
		operationLogPo.setOperationType(operationType);
		operationLogPo.setOperationTimeStart(operationTimeStart);
		operationLogPo.setOperationTimeEnd(operationTimeEnd);
		
		return olService.getOperationLogPoList(pageindex, pageSize, sidx, sord, operationLogPo);
	}
	
}
