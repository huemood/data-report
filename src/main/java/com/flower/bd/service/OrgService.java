package com.flower.bd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flower.bd.po.OrgPo;
import com.flower.bd.po.TermPo;
import com.flower.bd.po.mapper.OrgMapper;
import com.flower.bd.po.mapper.TermHistoryMapper;
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
	
	@Autowired
	private TermHistoryMapper termHistoryMapper;
	
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
	//获得历史学期数据
	public List<TermPo> getAllTermHistoryPo() {
		return termHistoryMapper.getAllTermList();
	}
	
	//根据是否总部管理员，返回zzid，是：不返回；否：返回登录账户zzid
	public String isNotHeadOfficeAdmin(String OrganizationCode){
		OrgPo orgPo = orgMapper.getOrgById(OrganizationCode);
		if (StringUtils.isEmpty(orgPo.getpId()) || orgPo.getpId().equals("NULL"))
			return null;
		return OrganizationCode;
	}
}
