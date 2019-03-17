package com.springboot.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class WoPage<T> {

	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(WoPage.class);

	public WoPage(List<T> rows) {
		super();
		this.rows = rows;
		this.results = Long.valueOf(rows.size());
	}

	public WoPage(List<T> rows, Long results) {
		super();
		this.rows = rows;
		this.results = results;
	}

	public WoPage() {
	}

	/**
	 * 
	 */
	public static final Long SIZE = 5L;

	/**
	 * 每页显示最大页码（当页过多时，不能一次显示所有页，只能显示部分）
	 */
	public static final Long MAX_PAGE = 5L;

	/**
	 * 点击最前和最后一页时，向前和向后偏移的页数
	 */
	public static final Long PAGE_OFFSET = MAX_PAGE / 2 - 1;

	/**
	 * 当前页索引，从1开始
	 */
	private Long page = 1L;

	/**
	 * 每页最大行数
	 */
	private Long pageSize = SIZE;

	/**
	 * 当前页面，页索引的最小值
	 */
	private Long startPage = 1L;

	/**
	 * 总行数
	 */
	private Long results = 0L;

	private List<T> rows;

	public Long getResults() {
		return results;
	}

	public void setResults(Long results) {
		this.results = results;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		// Long endPage = this.getEndPage();
		if (page >= MAX_PAGE) {
			if (page.equals(this.getPages())) {
				startPage = (page - MAX_PAGE) / PAGE_OFFSET * PAGE_OFFSET + 1L;
			} else {
				startPage = (page - MAX_PAGE) / PAGE_OFFSET * PAGE_OFFSET + 1L
						+ PAGE_OFFSET;
			}
		} else {
			startPage = 1L;
		}
		this.page = page;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getStartPage() {
		return startPage;
	}

	public void setStartPage(Long startPage) {
		this.startPage = startPage;
	}

	public Long getEndPage() {
		if (getPages() == 0) {
			return 1L;
		}
		if ((startPage + MAX_PAGE) > getPages()) {
			return getPages();
		}
		return startPage + MAX_PAGE - 1;
	}

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public Long getPages() {
		if ((results % pageSize) == 0) {
			return results / pageSize;
		}
		return results / pageSize + 1;
	}
}
