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
import com.flower.bd.po.mapper.StudentActionMapper;

/**
 * @author bc
 *
 */
@Service
public class StudentService {
	
	@Autowired
	private StudentActionMapper sam;
	
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
