package org.yyf.springcloud.commons.config;

import java.io.Serializable;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Integer PAGE_SIZE = 10;

	private Integer pageSize;
	private Integer pageNo;
	private Long totalPage;
	private Long total;
	private T data;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
