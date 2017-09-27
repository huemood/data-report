package com.flower.bd.po.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.OrgPo;


@Mapper
public interface OrgMapper {
	
	List<OrgPo> getOrgListByPid(@Param("pid") String pid);
	
	OrgPo getOrgById(@Param("id") String id);

}
