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
public class ExcelService {
	

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;
	
	public JSONArray getStudentData(String termId,String zzid) {
		JqGridData<StudentActionPo> datas = studentService.getStudentActionPoList(1, Integer.MAX_VALUE, "groupname", "asc", termId, zzid);
		List<StudentActionPo> studentList = datas.getRows();
		JSONArray ja = new JSONArray();
		ja.addAll(studentList);
		return ja;
	} 
	
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


}
