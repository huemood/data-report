/**
 * 
 */
package com.flower.bd.controller;


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

import com.flower.bd.po.JqGridData;
import com.alibaba.fastjson.JSONArray;
import com.flower.bd.po.StudentActionPo;
import com.flower.bd.po.TeacherActionPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.po.UserInfoPo;
import com.flower.bd.service.ExcelService;
import com.flower.bd.service.LogService;
import com.flower.bd.service.OrgService;
import com.flower.bd.service.StudentService;
import com.flower.bd.service.TeacherService;
import com.flower.bd.util.ExcelUtil;
import com.flower.bd.util.GlobalConstant;

/**
 * @author lx
 *
 */

@Controller
public class CourseTeacherController {
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ExcelService excelService;

	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/course/teacherMaster",method=RequestMethod.GET) 
	public String studentPage() {
		return "course/teacherMaster";
	}
	/*带自动补全
	@RequestMapping(value="/course/teacherAction")
	public String teacherActionPage(HttpServletRequest req,Map<String,Object> model) {
		String courseID = req.getParameter("courseID");
		String fromSource = req.getParameter("fromSource");
		
		List<TermPo> termList = orgService.getAllTermPo();
		
		model.put("termList", termList);
		model.put("courseID", courseID);
		model.put("fromSource", fromSource);
		
		String latestReportGenerateTime = logService.getlatestReportGenerateTime();
		model.put("latestReportGenerateTime", latestReportGenerateTime);
		
		return "course/teacherAction";
	}
	
	@RequestMapping(value="/course/teacherActionList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody JqGridData<TeacherActionPo> getTeacherActionList(HttpServletRequest req) {
		Integer courseID = new Integer(req.getParameter("courseID"));
		String fromSource = req.getParameter("fromSource");
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
		
		return teacherService.getTeacherActionPoListByCourseIDAndFrom(pageindex, pageSize, sidx, sord, termId, courseID, fromSource);
	}
	
	@RequestMapping(value="/course/teacherActionExport")
	@ResponseBody
    public void partExport(HttpServletRequest req,HttpServletResponse res){
		Integer courseID = new Integer(req.getParameter("courseID"));
		String fromSource = req.getParameter("fromSource");
		String termId = req.getParameter("termId");
	
        JSONArray ja = excelService.getCourseTeacherData(termId, courseID, fromSource);//获取业务数据集
        Map<String,String> headMap = excelService.getTeacherHead();//获取属性-列头
        String title = "教师行为分析按课程统计表";
        ExcelUtil.downloadExcelFile(title,headMap,ja,res);
    }
    带自动补全 */
	@RequestMapping(value="/course/teacherAction")
	public String teacherActionPage(HttpServletRequest req,Map<String,Object> model) {
		String shortName = req.getParameter("shortName");
		
		List<TermPo> termList = orgService.getAllTermPo();
		model.put("termList", termList);
		
		HttpSession session = req.getSession();
		UserInfoPo userInfoPo = (UserInfoPo) session.getAttribute(GlobalConstant.USER_INFO);
		model.put("zzid", userInfoPo.getZzid());
		
		model.put("shortName", shortName);
		
		String latestReportGenerateTime = logService.getlatestReportGenerateTime();
		model.put("latestReportGenerateTime", latestReportGenerateTime);
		
		return "course/teacherAction";
	}
	
	@RequestMapping(value="/course/teacherActionList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody JqGridData<TeacherActionPo> getTeacherActionList(HttpServletRequest req) {
		String zzid = req.getParameter("zzid");
		String termId = req.getParameter("termId");
		String shortName = req.getParameter("shortName");
		
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
		
		zzid = orgService.isNotHeadOfficeAdmin(zzid);
		
		return teacherService.getTeacherActionPoListByShortName(pageindex, pageSize, sidx, sord, zzid,termId, shortName);
	}
	
	@RequestMapping(value="/course/teacherActionExport")
	@ResponseBody
    public void partExport(HttpServletRequest req,HttpServletResponse res){
		String zzid = req.getParameter("zzid");
		String termId = req.getParameter("termId");
	
		String shortName = req.getParameter("shortName");
		
		zzid = orgService.isNotHeadOfficeAdmin(zzid);
		
        JSONArray ja = excelService.getCourseTeacherData(zzid,termId, shortName);//获取业务数据集
        Map<String,String> headMap = excelService.getTeacherHead();//获取属性-列头
        String title = "教师行为分析按课程统计表";
        ExcelUtil.downloadExcelFile(title,headMap,ja,res);
    }
}
