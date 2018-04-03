/**
 * 
 */
package com.flower.bd.po;

/**
 * @author bc
 *
 */
public class StudentActionPo {

	private String courseName;
	private String groupName;
	private String stNo;
	private String userName;
	private int clickNum;
	private int onlineNum;
	private int viewresNum;
	private int finshtaskNum;
	private int postNum;
	private int notTaskNum;
	private int notPostNum;
	//add 按课程查询 by 20171016 start
	private int courseID;
	private String fromSource;
	//add 按课程查询 by 20171016 end
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStNo() {
		return stNo;
	}

	public void setStNo(String stNo) {
		this.stNo = stNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	public int getViewresNum() {
		return viewresNum;
	}

	public void setViewresNum(int viewresNum) {
		this.viewresNum = viewresNum;
	}

	public int getFinshtaskNum() {
		return finshtaskNum;
	}

	public void setFinshtaskNum(int finshtaskNum) {
		this.finshtaskNum = finshtaskNum;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getNotTaskNum() {
		return notTaskNum;
	}

	public void setNotTaskNum(int notTaskNum) {
		this.notTaskNum = notTaskNum;
	}

	public int getNotPostNum() {
		return notPostNum;
	}

	public void setNotPostNum(int notPostNum) {
		this.notPostNum = notPostNum;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}

	
	
}
