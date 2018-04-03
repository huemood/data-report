/**
 * 
 */
package com.flower.bd.po.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.TeacherActionPo;

/**
 * @author lx
 *
 */
@Mapper
public interface TeacherActionHistoryMapper {
	
	List<TeacherActionPo> getTeacherActionListByPage(Map<String,Object> map);
	
	Integer getTeacherActionCount(@Param("termId") String termId,@Param("zzid") String zzid);
	//add 按课程查询 by 20171017 start
	Integer getTeacherActionCountByCourseIDAndFrom(@Param("courseID") Integer courseID,@Param("fromSource") String fromSource,@Param("termId") String termId);
	List<TeacherActionPo> getTeacherActionListByCourseIDAndFromByPage(Map<String,Object> map);
	//add 按课程查询 by 20171017 end
	
	//add 按课程shortname查询 by 20171026 start
	Integer getTeacherActionCountByShortName(@Param("zzid") String zzid,@Param("termId") String termId,@Param("shortName") String shortName);
	List<TeacherActionPo> getTeacherActionListByShortNameByPage(Map<String,Object> map);
	//add 按课程shortname查询 by 20171026 end
}
