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
public interface StudentActionMapper {
	
	List<StudentActionPo> getStudentActionListByPage(Map<String,Object> map);
	
	Integer getStudentActionCount(@Param("termId") String termId,@Param("zzid") String zzid);
}
