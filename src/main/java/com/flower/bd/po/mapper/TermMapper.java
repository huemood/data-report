package com.flower.bd.po.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.OrgPo;
import com.flower.bd.po.TermPo;


@Mapper
public interface TermMapper {
	
	List<TermPo> getAllTermList();
	
}
