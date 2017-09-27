/**
 * 
 */
package com.flower.bd.po;

/**
 * @author bc
 *
 */
public class OrgPo {
	
	private String id;
	private String pId;
	private String name;
	private boolean isParent=true;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	
	

}
