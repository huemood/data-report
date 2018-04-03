package com.flower.bd.po;

public class ActionCondition {

	private String termId;
	private String zzid;
	//add 按课程查询 by 20171016 start
	private Integer courseID;
	private String fromSource;
	//add 按课程查询 by 20171016 end
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getZzid() {
		return zzid;
	}
	public void setZzid(String zzid) {
		this.zzid = zzid;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public String getFromSource() {
		return fromSource;
	}
	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}
	
	
}
