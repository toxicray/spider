package com.ray.spiderprice.domain.query;

/**
 * Package:com.ray.spiderprice.domain.query
 * *Author:ray
 * *version:...
 * *Created in 2020/1/31  11:56
 **/
public class NewHouseInfoQuery {

	private Integer pageIndex;


	private Integer pageSize;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
