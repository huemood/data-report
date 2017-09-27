package com.flower.bd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flower.bd.po.OrgPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.po.mapper.OrgMapper;
import com.flower.bd.po.mapper.TermMapper;

/**
 * @author bc
 *
 */
@Service
public class OrgService {
	
	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private TermMapper termMapper;
	
	public List<OrgPo> getOrgList(String id,String initId) {
		List<OrgPo> result = new ArrayList<OrgPo>();
		if (StringUtils.isEmpty(id)) {
			OrgPo orgPo = orgMapper.getOrgById(initId);
			result.add(orgPo);
		} else {
			result = orgMapper.getOrgListByPid(id);
		}
		for(int i=0;i<result.size();i++) {
			boolean isParent = result.get(i).getIsParent();
			result.get(i).setIsParent(!isParent);
		}
		
		return result;
		
	}
	
	public List<TermPo> getAllTermPo() {
		return termMapper.getAllTermList();
	}

}
