package com.ruzhi.demo.common;

public class ItemOrderVO extends BaseDO{
	/**
	 *
	 */
	private static final long serialVersionUID = -1602697626931248253L;
	/**
	 * itemid
	 */
	private long id;
	/**
	 * item title
	 */
	private String title;
	/**
	 * sku id
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
