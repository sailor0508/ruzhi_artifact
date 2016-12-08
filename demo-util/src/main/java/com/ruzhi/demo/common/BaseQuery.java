package com.ruzhi.demo.common;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BaseQuery implements Serializable {

	private static final long serialVersionUID = 5851626224202148357L;
	private static final Integer defaultPageSize = 20;
	private static final Integer defaultFristPage = 1;
	private static final Integer defaultTotleItem = 0;
	private Integer totalItem;
	private Integer pageSize = defaultPageSize;
	private Integer currentPage;
	private String order = null;
	private Integer asc = null;

	public Integer getAsc() {
		return asc;
	}

	public void setAsc(Integer asc) {
		this.asc = asc;
	}

	// for paging
	private int startRow = 0;
	private int endRow = defaultPageSize;


	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return Returns the defaultPageSize.
	 */
	protected Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public boolean isFirstPage() {
		return this.getCurrentPage() == 1;
	}

	public int getPreviousPage() {
		int back = this.getCurrentPage() - 1;

		if (back <= 0) {
			back = 1;
		}

		return back;
	}

	public boolean isLastPage() {
		return this.getTotalPage() == this.getCurrentPage();
	}

	public int getNextPage() {
		int back = this.getCurrentPage() + 1;

		if (back > this.getTotalPage()) {
			back = this.getTotalPage();
		}

		return back;
	}

	/**
	 * @return Returns the currentPage.
	 */
	public Integer getCurrentPage() {
		if (this.currentPage == null || this.currentPage == 0) {
			return defaultFristPage;
		}
		return this.currentPage;
	}

	public void setCurrentPageString(String pageString) {
		if (isBlankString(pageString)) {
			this.setCurrentPage(defaultFristPage);
		}

		try {
			Integer integer = new Integer(pageString);

			this.setCurrentPage(integer);
		} catch (NumberFormatException ignore) {
			this.setCurrentPage(defaultFristPage);
		}
	}

	/**
	 * @param cPage
	 * The currentPage to set.
	 */
	public void setCurrentPage(Integer cPage) {
		if ((cPage == null) || (cPage <= 0)) {
			this.currentPage = null;
		} else {
			this.currentPage = cPage;
		}
	}

	@SuppressWarnings("unused")
	private void setStartEndRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return Returns the pageSize.
	 */
	public Integer getPageSize() {
		if (this.pageSize == null) {
			return getDefaultPageSize();
		}

		return this.pageSize;
	}

	public boolean hasSetPageSize() {
		return this.pageSize != null;
	}

	public void setPageSizeString(String pageSizeString) {
		if (isBlankString(pageSizeString)) {
			return;
		}

		try {
			Integer integer = new Integer(pageSizeString);

			this.setPageSize(integer);
		} catch (NumberFormatException ignore) {
		}
	}

	/**
	 * @param pageSizeString 页面大小
	 *
	 * @return boolean
	 */
	private boolean isBlankString(String pageSizeString) {
		return pageSizeString == null || pageSizeString.trim().length() == 0;
	}

	/**
	 * @param pSize
	 * The pageSize to set.
	 */
	public void setPageSize(Integer pSize) {
		if ((pSize == null) || (pSize <= 0)) {
			this.pageSize = null;
		} else {
			this.pageSize = pSize;
		}
	}

	/**
	 * @return Returns the totalItem.
	 */
	public Integer getTotalItem() {
		if (this.totalItem == null) {
			return defaultTotleItem;
		}

		return totalItem;
	}

	/**
	 * @param tItem
	 * The totalItem to set.
	 */
	public void setTotalItem(Integer tItem) {
		if (tItem == null) {
			throw new IllegalArgumentException("TotalItem can't be null.");
		}

		this.totalItem = tItem;

		int current = this.getCurrentPage();
		int lastPage = this.getTotalPage();

		if (current > lastPage) {
			this.setCurrentPage(lastPage);
		}
	}

	public int getTotalPage() {
		int pgSize = this.getPageSize();
		int total = this.getTotalItem();
		int result = total / pgSize;

		if ((total == 0) || ((total % pgSize) != 0)) {
			result++;
		}

		return result;
	}

	/**
	 * @return Returns the endRow.
	 */
	public int getEndRow() {
		this.endRow = this.startRow + this.getPageSize();
		return this.endRow;
	}

	/**
	 * @param endRow
	 * The endRow to set.
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	/**
	 * @return Returns the startRow.
	 */
	public int getStartRow() {
		return this.startRow + this.getPageSize() * (this.getCurrentPage() - 1);
	}

	/**
	 * @param startRow
	 * The startRow to set.
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public List<String> getPages() {
		List<String> l = new ArrayList<String>(10);
		int leftStart = 1;
		int leftEnd = 2;
		int mStart = this.getCurrentPage() - 2;
		int mEnd = this.getCurrentPage() + 2;
		int rStart = this.getTotalPage() - 1;
		int rEnd = this.getTotalPage();
		if (mStart <= leftEnd) {
			leftStart = 0;
			leftEnd = 0;
			mStart = 1;
		}
		if (mEnd >= rStart) {
			rStart = 0;
			rEnd = 0;
			mEnd = this.getTotalPage();
		}
		if (leftEnd > leftStart) {
			for (int i = leftStart; i <= leftEnd; ++i) {
				l.add(String.valueOf(i));
			}
		}
		if(leftEnd+1<mStart)l.add("...");
		for (int i = mStart; i <= mEnd; ++i) {
			l.add(String.valueOf(i));
		}
		if(mEnd+1<rStart)l.add("...");
		if (rEnd > rStart) {
			for (int i = rStart; i <= rEnd; ++i) {
				l.add(String.valueOf(i));
			}
		}
		return l;
	}


	public void init() {
		this.currentPage = getCurrentPage() ;
	}



}