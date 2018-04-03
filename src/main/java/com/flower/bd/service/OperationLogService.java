package com.flower.bd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.bd.po.JqGridData;
import com.flower.bd.po.OperationLogPo;
import com.flower.bd.po.Page;
import com.flower.bd.po.mapper.OperationLogMapper;

import org.springframework.util.StringUtils;

@Service
public class OperationLogService {
	@Autowired
	private OperationLogMapper olm;
	
	public JqGridData<OperationLogPo> getOperationLogPoList(int page,int rows,String sidx,String sord,OperationLogPo operationLogPo){
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("userOperationLogEntity", operationLogPo);
		
		int count = 0;
		Integer countTmp = olm.getOperationLogCount(searchMap);
		if (countTmp != null)
			count = countTmp;
		
		Page pagePo = new Page();
		pagePo.setPage(page);
		pagePo.setRows(rows);
		pagePo.setCount(count);
		pagePo.setSidx(sidx);
		pagePo.setSord(sord);
		
		searchMap.put("page", pagePo);
		
		List<OperationLogPo> olpList = olm.getUserOperationLogListByPage(searchMap);
		JqGridData<OperationLogPo> jqGridData = new JqGridData<OperationLogPo>();
		jqGridData.setPage(pagePo.getPage());
		jqGridData.setRecords(pagePo.getCount());
		jqGridData.setTotal(pagePo.getTotalPages());
		jqGridData.setRows(olpList);
		
		return jqGridData;
	}
	public int instert(OperationLogPo operationLogPo){
		return olm.insert(operationLogPo);
	}
}
