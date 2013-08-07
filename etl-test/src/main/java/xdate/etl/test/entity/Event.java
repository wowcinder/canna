/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdate.etl.test.entity;

import java.io.Serializable;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public class Event implements Serializable {
	private Integer id;

	/**
	 * 
	 */
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
