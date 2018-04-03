package com.flower.bd.po.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.OperationLogPo;

@Mapper
public interface OperationLogMapper {
	Integer getOperationLogCount(Map<String,Object> map);
	List<OperationLogPo> getUserOperationLogListByPage(Map<String,Object> map);
	int insert(OperationLogPo operationLogPo);
}
