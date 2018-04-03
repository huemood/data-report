/**
 * 
 */
package com.flower.bd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flower.bd.po.JqGridData;
import com.flower.bd.po.StudentActionPo;
import com.flower.bd.po.TeacherActionPo;



/**
 * @author bc
 *
 */
@Service
public class ExcelHistoryService {
	

	@Autowired
	private StudentHistoryService studentService;

	@Autowired
	private TeacherHistoryService teacherService;
	
	public JSONArray getStudentData(String termId,String zzid) {
		JqGridData<StudentActionPo> datas = studentService.getStudentActionPoList(1, Integer.MAX_VALUE, "groupname", "asc", termId, zzid);
		List<StudentActionPo> studentList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(studentList);
		return ja;
	}
	//add 按课程查询 by 20171016 start
	public JSONArray getCourseStudentData(String termId,String zzid,Integer courseID,String fromSource) {
		//JqGridData<StudentActionPo> datas = studentService.getStudentActionPoList(1, Integer.MAX_VALUE, "groupname", "asc", termId, zzid);
		JqGridData<StudentActionPo> datas = studentService.getStudentActionPoListByCourseIDAndFrom(1, Integer.MAX_VALUE, "groupname", "asc", termId, zzid,courseID, fromSource);
		List<StudentActionPo> studentList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(studentList);
		return ja;
	}
	//add 按课程查询 by 20171016 end
	
	//add 按课程shortname查询 by 20171026 start
	public JSONArray getCourseStudentData(String zzid,String termId,String shortName) {
		JqGridData<StudentActionPo> datas = studentService.getStudentActionPoListByShortName(1, Integer.MAX_VALUE, "groupname", "asc",zzid,termId, shortName);
		List<StudentActionPo> studentList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(studentList);
		return ja;
	}
	//add 按课程shortname查询 by 20171026 end
	public Map<String,String> getStudentHead() {
        Map<String,String> headMap = new LinkedHashMap<String,String>();
        headMap.put("courseName","课程");
        headMap.put("groupName","班级");
        headMap.put("stNo","学号");
        headMap.put("userName","姓名");
        headMap.put("clickNum","点击次数");
        headMap.put("onlineNum","上线天数");
        headMap.put("viewresNum","浏览资源数");
        headMap.put("finshtaskNum","完成作业数");
        headMap.put("postNum","发帖数");
        headMap.put("notTaskNum","未批改的作业数");
        headMap.put("notPostNum","未回复的贴数");
        return headMap;
	}
	
	public JSONArray getTeacherData(String termId,String zzid) {
		JqGridData<TeacherActionPo> datas = teacherService.getTeacherActionPoList(1, Integer.MAX_VALUE, "groupname", "asc", termId, zzid);
		List<TeacherActionPo> teacherList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(teacherList);
		return ja;
	} 
	//add 按课程查询 by 20171017 start
	public JSONArray getCourseTeacherData(String termId,Integer courseID,String fromSource) {
		JqGridData<TeacherActionPo> datas = teacherService.getTeacherActionPoListByCourseIDAndFrom(1, Integer.MAX_VALUE, "groupname", "asc", termId, courseID, fromSource);
		List<TeacherActionPo> teacherList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(teacherList);
		return ja;
	}
	//add 按课程查询 by 20171017 end
	public Map<String,String> getTeacherHead() {
        Map<String,String> headMap = new LinkedHashMap<String,String>();
        headMap.put("courseName","课程");
        headMap.put("groupName","班级");
        headMap.put("userName","姓名");
        headMap.put("clickNum","点击次数");
        headMap.put("onlineNum","上线天数");
        headMap.put("postNum","发帖数");
        return headMap;
	}
	//add 按课程shortname查询 by 20171026 start
	public JSONArray getCourseTeacherData(String zzid,String termId,String shortName) {
		JqGridData<TeacherActionPo> datas = teacherService.getTeacherActionPoListByShortName(1, Integer.MAX_VALUE, "groupname", "asc",zzid, termId, shortName);
		List<TeacherActionPo> teacherList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(teacherList);
		return ja;
	}
	//add 按课程shortname查询 by 20171026 end

}
