/**
 * 
 */
package com.flower.bd.po.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.StudentActionPo;

/**
 * @author bc
 *
 */
@Mapper
public interface StudentActionHistoryMapper {
	
	List<StudentActionPo> getStudentActionListByPage(Map<String,Object> map);
	
	Integer getStudentActionCount(@Param("termId") String termId,@Param("zzid") String zzid);
	
	//add 按课程查询带自动补全 by 20171016 start
	Integer getStudentActionCountByCourseIDAndFrom(@Param("termId") String termId,@Param("zzid") String zzid,@Param("courseID") Integer courseID,@Param("fromSource") String fromSource);
	List<StudentActionPo> getStudentActionListByCourseIDAndFromByPage(Map<String,Object> map);
	//add 按课程查询带自动补全 by 20171016 end
	
	//add 按课程shortname查询 by 20171026 start
	Integer getStudentActionCountByShortName(@Param("zzid") String zzid,@Param("termId") String termId,@Param("shortName") String shortName);
	List<StudentActionPo> getStudentActionListByShortNameByPage(Map<String,Object> map);
	//add 按课程shortname查询 by 20171026 end
}
