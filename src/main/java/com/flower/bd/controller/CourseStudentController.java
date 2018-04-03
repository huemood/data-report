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
import com.flower.bd.po.StudentActionPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.po.UserInfoPo;
import com.flower.bd.service.CourseService;
import com.flower.bd.service.ExcelService;
import com.flower.bd.service.LogService;
import com.flower.bd.service.OrgService;
import com.flower.bd.service.StudentService;
import com.flower.bd.util.ExcelUtil;
import com.flower.bd.util.GlobalConstant;

@Controller
public class CourseStudentController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private LogService logService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ExcelService excelService;
	//----------------------angular----------------------
	@RequestMapping(value="/course/studentMaster2",method=RequestMethod.GET)
	public String courseAngularPage(){
		return "course/angular";
	}
	@RequestMapping(value="/course/getCourseList2",method=RequestMethod.GET)
	public @ResponseBody List<CoursePo> getCourseListByFullName2(HttpServletRequest req){
		List<CoursePo> coursePoList = new ArrayList<CoursePo>();
		String fullName = req.getParameter("fullName");
		if (fullName == null)
			fullName = "国开学习网";
		coursePoList = courseService.getCourseByName(fullName,"010");
		
		return coursePoList;
	}
	//----------------------jquery----------------------
	@RequestMapping(value="/course/studentMaster",method=RequestMethod.GET)
	public String courseStudentPage(){
		return "course/studentMaster";
	}
	@RequestMapping(value="/course/getCourseName",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody List<String> getCourseNameList(HttpServletRequest req) {
		List<String> cnl = new ArrayList<String>();
		String courseName = req.getParameter("courseName");
		cnl = courseService.getCourseName(courseName);
		return cnl;
	}
	/* 带自动补全
	@RequestMapping(value="/course/getCourseList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody List<CoursePo> getCourseListByFullName(HttpServletRequest req){
		List<CoursePo> coursePoList = new ArrayList<CoursePo>();
		
		String fullName = req.getParameter("fullName");
		
		HttpSession session = req.getSession();
		UserInfoPo userInfoPo = (UserInfoPo) session.getAttribute(GlobalConstant.USER_INFO);
		String zzid = userInfoPo.getZzid();
		
		coursePoList = courseService.getCourseByName(fullName,zzid);
		
		return coursePoList;
	}
	@RequestMapping(value="/course/studentAction")
	public String studentActionPage(HttpServletRequest req,Map<String,Object> model) {
		String courseID = req.getParameter("courseID");
		String fromSource = req.getParameter("fromSource");
		
		List<TermPo> termList = orgService.getAllTermPo();
		model.put("termList", termList);
		
		HttpSession session = req.getSession();
		UserInfoPo userInfoPo = (UserInfoPo) session.getAttribute(GlobalConstant.USER_INFO);
		model.put("zzid", userInfoPo.getZzid());
		
		model.put("courseID", courseID);
		model.put("fromSource", fromSource);
		
		String latestReportGenerateTime = logService.getlatestReportGenerateTime();
		model.put("latestReportGenerateTime", latestReportGenerateTime);
		
		return "course/studentAction";
	}
	@RequestMapping(value="/course/studentActionList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody JqGridData<StudentActionPo> getStudentActionList(HttpServletRequest req) {
		String termId = req.getParameter("termId");
		String zzid = req.getParameter("zzid");
		
		Integer courseID = new Integer(req.getParameter("courseID"));
		String fromSource = req.getParameter("fromSource");
		
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
		
		//return studentService.getStudentActionPoList(pageindex, pageSize, sidx, sord, termId, zzid);
		return studentService.getStudentActionPoListByCourseIDAndFrom(pageindex, pageSize, sidx, sord, termId, zzid,courseID, fromSource);
	}
	@RequestMapping(value="/course/studentActionExport")
	@ResponseBody
    public void partExport(HttpServletRequest req,HttpServletResponse res){
		String termId = req.getParameter("termId");
		String zzid = req.getParameter("zzid");
	
		Integer courseID = new Integer(req.getParameter("courseID"));
		String fromSource = req.getParameter("fromSource");
		
        
		JSONArray ja = excelService.getCourseStudentData(termId, zzid,courseID, fromSource);//获取业务数据集
        Map<String,String> headMap = excelService.getStudentHead();//获取属性-列头
        String title = "学生行为分析按课程统计表";
        ExcelUtil.downloadExcelFile(title,headMap,ja,res);
    }
       带自动补全*/
	@RequestMapping(value="/course/getCourseList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody List<CoursePo> getCourseListByFullName(HttpServletRequest req){
		List<CoursePo> coursePoList = new ArrayList<CoursePo>();
		
		String fullName = req.getParameter("fullName");
		
		coursePoList = courseService.getShortNameAndFullNameByCourseName(fullName);
		
		return coursePoList;
	}
	@RequestMapping(value="/course/studentAction")
	public String studentActionPage(HttpServletRequest req,Map<String,Object> model) {
		String shortName = req.getParameter("shortName");
		
		List<TermPo> termList = orgService.getAllTermPo();
		model.put("termList", termList);
		
		HttpSession session = req.getSession();
		UserInfoPo userInfoPo = (UserInfoPo) session.getAttribute(GlobalConstant.USER_INFO);
		model.put("zzid", userInfoPo.getZzid());
		
		model.put("shortName", shortName);
		
		String latestReportGenerateTime = logService.getlatestReportGenerateTime();
		model.put("latestReportGenerateTime", latestReportGenerateTime);
		
		return "course/studentAction";
	}
	@RequestMapping(value="/course/studentActionList",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_UTF8_VALUE},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody JqGridData<StudentActionPo> getStudentActionList(HttpServletRequest req) {
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
						
		//return studentService.getStudentActionPoList(pageindex, pageSize, sidx, sord, termId, zzid);
		return studentService.getStudentActionPoListByShortName(pageindex, pageSize, sidx, sord, zzid,termId, shortName);
	}
	@RequestMapping(value="/course/studentActionExport")
	@ResponseBody
    public void partExport(HttpServletRequest req,HttpServletResponse res){
		String zzid = req.getParameter("zzid");
		String termId = req.getParameter("termId");
		
		String shortName = req.getParameter("shortName");
		
		zzid = orgService.isNotHeadOfficeAdmin(zzid);
		
		JSONArray ja = excelService.getCourseStudentData(zzid,termId, shortName);//获取业务数据集
        Map<String,String> headMap = excelService.getStudentHead();//获取属性-列头
        String title = "学生行为分析按课程统计表";
        ExcelUtil.downloadExcelFile(title,headMap,ja,res);
    }
}
