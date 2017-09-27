/**
 * 
 */
package com.flower.bd.po;

import java.io.Serializable;

/**
 * @author slccbc
 *
 */
public class Page implements Serializable{

	private static final long serialVersionUID = 1L;
	private int page;//调用者提供,页数
	private int rows;//调用者提供,每页的记录数
	
	private int count;//调用者提供,总共的记录数
	
	private int totalPages;//输出,总页数
	private int start;//输出,起始记录数
	private String sidx;//排序字段
	private String sord;//排序方向
	
	private String searchField;//查询字段
	private String searchString;//查询内容
	
	
	public int getPage() {
		if (page > totalPages) page=totalPages;
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		if( count >0 ) {
			totalPages = (count + rows -1)/rows;
		} 
	}
	public int getTotalPages() {
		return totalPages;
	}

	public int getStart() {
		start = rows*page - rows; // 
		return start;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	

}
