package com.flower.bd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.bd.po.CoursePo;
import com.flower.bd.po.OrgPo;
import com.flower.bd.po.mapper.CourseMapper;
import com.flower.bd.po.mapper.OrgMapper;
import org.springframework.util.StringUtils;

@Service
public class CourseService {
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private OrgMapper orgMapper;
	
	public List<String> getCourseName(String courseName){
		List<String> courseNameList = courseMapper.getCourseName(courseName);
		
		return courseNameList;
		
	}
	
	public List<CoursePo> getCourseByName(String fullName,String id){
		List<CoursePo> coursePoList = new ArrayList<CoursePo>();
		
		OrgPo orgPo = orgMapper.getOrgById(id);
		//zongbu
		if (StringUtils.isEmpty(orgPo.getpId()) || orgPo.getpId().equals("NULL"))
			coursePoList = courseMapper.getCourseByName(fullName);
		else
			coursePoList = courseMapper.getCourseByNameAndZzid(fullName, id);
		return coursePoList;
	}
	
	public List<CoursePo> getShortNameAndFullNameByCourseName(String courseName){
		List<CoursePo> coursePoList = new ArrayList<CoursePo>();
		
		coursePoList = courseMapper.getShortNameAndFullNameByCourseName(courseName);
		
		return coursePoList;
	}
}
