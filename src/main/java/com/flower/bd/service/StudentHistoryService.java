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
import com.flower.bd.po.mapper.StudentActionHistoryMapper;

/**
 * @author bc
 *
 */
@Service
public class StudentHistoryService {
	
	@Autowired
	private StudentActionHistoryMapper sam;
	
	public JqGridData<StudentActionPo> getStudentActionPoList(int page,int rows,String sidx,String sord,String termId,String zzid) {
		
		zzid = zzid + "%";//
		
		ActionCondition condition = new ActionCondition();
		condition.setTermId(termId);
		condition.setZzid(zzid);
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("condition", condition);
		
		int count = 0;//记录总条数
		Integer countTmp = sam.getStudentActionCount(termId, zzid);
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
		
		List<StudentActionPo> wps = sam.getStudentActionListByPage(searchMap);
		
		JqGridData<StudentActionPo> jqGridData = new JqGridData<StudentActionPo>() ;
		jqGridData.setPage(pagePo.getPage());
		jqGridData.setRecords(pagePo.getCount());
		jqGridData.setTotal(pagePo.getTotalPages());
		jqGridData.setRows(wps);
		
		return jqGridData;
	}
	//add 按课程查询 by 20171016 start
    public JqGridData<StudentActionPo> getStudentActionPoListByCourseIDAndFrom(int page,int rows,String sidx,String sord,String termId,String zzid,Integer courseID,String fromSource) {
		
		zzid = zzid + "%";//
		
		ActionCondition condition = new ActionCondition();
		condition.setTermId(termId);
		condition.setZzid(zzid);
		condition.setCourseID(courseID);
		condition.setFromSource(fromSource);
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("condition", condition);
		
		int count = 0;//记录总条数
		//Integer countTmp = sam.getStudentActionCount(termId, zzid);
		Integer countTmp = sam.getStudentActionCountByCourseIDAndFrom( termId,zzid,courseID, fromSource);
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
		
		//List<StudentActionPo> wps = sam.getStudentActionListByPage(searchMap);
		List<StudentActionPo> wps = sam.getStudentActionListByCourseIDAndFromByPage(searchMap);
		
		JqGridData<StudentActionPo> jqGridData = new JqGridData<StudentActionPo>() ;
		jqGridData.setPage(pagePo.getPage());
		jqGridData.setRecords(pagePo.getCount());
		jqGridData.setTotal(pagePo.getTotalPages());
		jqGridData.setRows(wps);
		
		return jqGridData;
	}
    //add 按课程查询 by 20171016 end
    
  //add 按课程shortname查询 by 20171026 start
    public JqGridData<StudentActionPo> getStudentActionPoListByShortName(int page,int rows,String sidx,String sord,String zzid,String termId,String shortName) {
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("zzid", zzid);
		searchMap.put("termId", termId);
		searchMap.put("shortName", shortName);
		
		int count = 0;//记录总条数
		//Integer countTmp = sam.getStudentActionCount(termId, zzid);
		Integer countTmp = sam.getStudentActionCountByShortName(zzid,termId, shortName);
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
		
		//List<StudentActionPo> wps = sam.getStudentActionListByPage(searchMap);
		List<StudentActionPo> wps = sam.getStudentActionListByShortNameByPage(searchMap);
		
		JqGridData<StudentActionPo> jqGridData = new JqGridData<StudentActionPo>() ;
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
		case "stNo":
			result = "st_no";
			break;
		case "onlineNum":
			result = "online_num";
			break;
		case "clickNum":
			result = "click_num";
			break;
		case "viewresNum":
			result = "viewres_num";
			break;
		case "finshtaskNum":
			result = "finshtask_num";
			break;
		case "postNum":
			result = "post_num";
			break;
		case "notTaskNum":
			result = "not_task_num";
			break;
		case "notPostNum":
			result = "not_post_num";
			break;
		default:
			result = "groupname";
			break;
		}
		return result;
	}
	

}
