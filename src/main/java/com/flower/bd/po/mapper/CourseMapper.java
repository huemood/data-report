package com.flower.bd.po.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.CoursePo;

@Mapper
public interface CourseMapper {
	List<String> getCourseName(@Param("courseName") String courseName);
	
	List<CoursePo> getShortNameAndFullNameByCourseName(@Param("courseName") String courseName);
	
	List<CoursePo> getCourseByName(@Param("fullname") String fullName);
	
	List<CoursePo> getCourseByNameAndZzid(@Param("fullname") String fullName,@Param("zzid") String zzid);
}
