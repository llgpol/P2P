package com.yueqian.base.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询的结果对象
 */

public class PageResult {

	private List listData ;  //当前页的结果集数据
	private Integer totalCount ;
	
	private Integer currentPage  = 1;
	private Integer pageSize  = 10 ;
	
	private Integer prevPage  ;
	private Integer nextPage  ;
	private Integer totalPage  ;
	
	public List getListData() {
		return listData;
	}

	public void setListData(List listData) {
		this.listData = listData;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	//如果总数据条数为0 返回一个空集 
	public static PageResult empty(Integer pageSize){
		return new PageResult(new ArrayList<>(), 0, 1, pageSize);
	}
	
	//如果totalPage为0 返回的页数为第一页
	public int getTotalPage(){
		return  totalPage == 0 ? 1 : totalPage ;
	}	

	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		
		//计算数据
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize :  this.totalCount/ this.pageSize + 1 ;
	
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage -1 :1 ;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;  
	}
	
}
