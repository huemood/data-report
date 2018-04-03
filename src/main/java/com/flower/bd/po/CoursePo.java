/**
 * 
 */
package com.flower.bd.po;

import java.sql.Date;

/**
 * @author lx
 *
 */
public class CoursePo {
	
	private Long id;
	private String shortname;
	private String fullname;
	private String fromsource;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getFromsource() {
		return fromsource;
	}
	public void setFromsource(String fromsource) {
		this.fromsource = fromsource;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
}
