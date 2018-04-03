/**
 * 
 */
package com.flower.bd.po;

import java.util.Date;

/**
 * @author lx
 *
 */
public class OperationLogPo {
	
	private Long id;
	private String userID;
	private String operationType;
	private Date operationTime;
	private String operationTimeStart;
	private String operationTimeEnd;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getOperationTimeStart() {
		return operationTimeStart;
	}
	public void setOperationTimeStart(String operationTimeStart) {
		this.operationTimeStart = operationTimeStart;
	}
	public String getOperationTimeEnd() {
		return operationTimeEnd;
	}
	public void setOperationTimeEnd(String operationTimeEnd) {
		this.operationTimeEnd = operationTimeEnd;
	}
}
