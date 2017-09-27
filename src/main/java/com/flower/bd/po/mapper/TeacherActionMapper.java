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
 * @author bc
 *
 */
@Mapper
public interface TeacherActionMapper {
	
	List<TeacherActionPo> getTeacherActionListByPage(Map<String,Object> map);
	
	Integer getTeacherActionCount(@Param("termId") String termId,@Param("zzid") String zzid);
}
