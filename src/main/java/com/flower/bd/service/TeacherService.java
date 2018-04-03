/**
 * 
 */
package com.flower.bd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.bd.po.JqGridData;
import com.flower.bd.po.Page;
import com.flower.bd.po.ActionCondition;
import com.flower.bd.po.StudentActionPo;
import com.flower.bd.po.TeacherActionPo;
import com.flower.bd.po.mapper.StudentActionMapper;
import com.flower.bd.po.mapper.TeacherActionMapper;

/**
 * @author bc
 *
 */
@Service
public class TeacherService {
	
	@Autowired
	private TeacherActionMapper tam;
	
	public JqGridData<TeacherActionPo> getTeacherActionPoList(int page,int rows,String sidx,String sord,String termId,String zzid) {
		
		zzid = zzid + "%";//
		
		ActionCondition condition = new ActionCondition();
		condition.setTermId(termId);
		condition.setZzid(zzid);
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("condition", condition);
		
		int count = 0;//记录总条数
		Integer countTmp = tam.getTeacherActionCount(termId, zzid);
		if (countTmp != null) {
			count = countTmp.intValue();
		}
		Page pagePo = new Page();
		pagePo.setPage(page);
		pagePo.setRows(rows);
		pagePo.setCount(count);
		pagePo.setSidx(getField(sidx));
		pagePo.setSord(sord);
		
		searchMap.put("page", pagePo);
		
		List<TeacherActionPo> wps = tam.getTeacherActionListByPage(searchMap);
		
		JqGridData<TeacherActionPo> jqGridData = new JqGridData<TeacherActionPo>() ;
		jqGridData.setPage(pagePo.getPage());
		jqGridData.setRecords(pagePo.getCount());
		jqGridData.setTotal(pagePo.getTotalPages());
		jqGridData.setRows(wps);
		
		return jqGridData;
	}
	//add 按课程查询 by 20171017 start
	public JqGridData<TeacherActionPo> getTeacherActionPoListByCourseIDAndFrom(int page,int rows,String sidx,String sord,String termId,Integer courseID,String fromSource) {
		
		ActionCondition condition = new ActionCondition();
		condition.setTermId(termId);
		condition.setCourseID(courseID);
		condition.setFromSource(fromSource);
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("condition", condition);
		
		int count = 0;//记录总条数
		Integer countTmp = tam.getTeacherActionCountByCourseIDAndFrom(courseID, fromSource, termId);
		if (countTmp != null) {
			count = countTmp.intValue();
		}
		Page pagePo = new Page();
		pagePo.setPage(page);
		pagePo.setRows(rows);
		pagePo.setCount(count);
		pagePo.setSidx(getField(sidx));
		pagePo.setSord(sord);
		
		searchMap.put("page", pagePo);
		
		List<TeacherActionPo> wps = tam.getTeacherActionListByCourseIDAndFromByPage(searchMap);
		
		JqGridData<TeacherActionPo> jqGridData = new JqGridData<TeacherActionPo>() ;
		jqGridData.setPage(pagePo.getPage());
		jqGridData.setRecords(pagePo.getCount());
		jqGridData.setTotal(pagePo.getTotalPages());
		jqGridData.setRows(wps);
		
		return jqGridData;
	}
	//add 按课程查询 by 20171017 end
	
	//add 按课程shortname查询 by 20171026 start
	public JqGridData<TeacherActionPo> getTeacherActionPoListByShortName(int page,int rows,String sidx,String sord,String zzid,String termId,String shortName) {
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("zzid", zzid);
		searchMap.put("termId", termId);
		searchMap.put("shortName", shortName);
		
		int count = 0;//记录总条数
		Integer countTmp = tam.getTeacherActionCountByShortName(zzid,termId, shortName);
		if (countTmp != null) {
			count = countTmp.intValue();
		}
		Page pagePo = new Page();
		pagePo.setPage(page);
		pagePo.setRows(rows);
		pagePo.setCount(count);
		pagePo.setSidx(getField(sidx));
		pagePo.setSord(sord);
		
		searchMap.put("page", pagePo);
		
		List<TeacherActionPo> wps = tam.getTeacherActionListByShortNameByPage(searchMap);
		
		JqGridData<TeacherActionPo> jqGridData = new JqGridData<TeacherActionPo>() ;
		jqGridData.setPage(pagePo.getPage());
		jqGridData.setRecords(pagePo.getCount());
		jqGridData.setTotal(pagePo.getTotalPages());
		jqGridData.setRows(wps);
		
		return jqGridData;
	}
	//add 按课程shortname查询 by 20171026 end
	
	//因bootstrap table取的是po属性，因此需要转换成字段
	private String getField(String attrName) {
		String result = "";
		switch (attrName) {
		case "onlineNum":
			result = "online_num";
			break;
		case "clickNum":
			result = "click_num";
			break;
		case "postNum":
			result = "post_num";
			break;
		default:
			result = "groupname";
			break;
		}
		return result;
	}
	

}
