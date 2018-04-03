/**
 * 
 */
package com.flower.bd.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.bd.po.JqGridData;
import com.alibaba.fastjson.JSONArray;
import com.flower.bd.po.StudentActionPo;
import com.flower.bd.po.TeacherActionPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.service.ExcelHistoryService;
import com.flower.bd.service.ExcelService;
import com.flower.bd.service.LogService;
import com.flower.bd.service.OrgService;
import com.flower.bd.service.StudentService;
import com.flower.bd.service.TeacherHistoryService;
import com.flower.bd.service.TeacherService;
import com.flower.bd.util.ExcelUtil;

/**
 * @author bc
 *
 */

@RequestMapping(value="/history")
@Controller
public class TeacherHistoryController {
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private TeacherHistoryService teacherService;
	
	@Autowired
	private ExcelHistoryService excelService;

	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/teacherMaster",method=RequestMethod.GET) 
	public String studentPage() {
		return "org/teacherMaster";
	}
	
	@RequestMapping(value="/teacherAction")
	public String teacherActionPage(HttpServletRequest req,Map<String,Object> model) {
		String zzid = req.getParameter("orgId");
		List<TermPo> termList = orgService.getAllTermHistoryPo();
		model.put("termList", termList);
		model.put("zzid", zzid);
		
		String latestReportGenerateTime = logService.getlatestReportGenerateTime();
		model.put("latestReportGenerateTime", latestReportGenerateTime);
		
		return "org/teacherAction";
	}
	
	@RequestMapping(value="/teacherActionList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody JqGridData<TeacherActionPo> getTeacherActionList(HttpServletRequest req) {
		String zzid = req.getParameter("zzid");
		String termId = req.getParameter("termId");
		int pageindex = Integer.parseInt(req.getParameter("pageindex"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		String sidx  = req.getParameter("sortName");
		String sord  = req.getParameter("sortOrder");
		
		if (StringUtils.isEmpty(sidx)) {
			sidx = "groupname";
		}

		if (StringUtils.isEmpty(sord)) {
			sord = "asc";
		}
		
		return teacherService.getTeacherActionPoList(pageindex, pageSize, sidx, sord, termId, zzid);
	}
	
	@RequestMapping(value="/teacherActionExport")
	@ResponseBody
    public void partExport(HttpServletRequest req,HttpServletResponse res){
		String zzid = req.getParameter("zzid");
		String termId = req.getParameter("termId");
	
        JSONArray ja = excelService.getTeacherData(termId, zzid);//获取业务数据集
        Map<String,String> headMap = excelService.getTeacherHead();//获取属性-列头
        String title = "教师行为分析统计表";
        ExcelUtil.downloadExcelFile(title,headMap,ja,res);
    }
}
