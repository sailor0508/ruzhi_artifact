package com.ruzhi.demo.baidu;

public class DAOException  extends Exception {

	private static final long serialVersionUID = 526384187169112896L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String msg, Throwable cause) {
		super(msg, cause);
	}

}