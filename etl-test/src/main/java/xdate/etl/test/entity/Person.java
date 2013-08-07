/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdate.etl.test.entity;

import java.io.Serializable;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public class Person implements Serializable {
	private Integer id;
	private Address address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
