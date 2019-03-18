package com.eutopia.shadow.everyday.grouping.DTO;

import java.util.List;

import com.eutopia.shadow.everyday.grouping.domain.HxAccountTrade;

public class HxAccountTradeDTO {
	
	private List<HxAccountTrade> content;
	
	private int pageSize;
	
	/**
	 * 页数
	 */
	private int pageNumber;
	
	/**
	 * 总页数
	 */
	private int totalPageNumber;

	public List<HxAccountTrade> getContent() {
		return content;
	}

	public void setContent(List<HxAccountTrade> content) {
		this.content = content;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	
}
