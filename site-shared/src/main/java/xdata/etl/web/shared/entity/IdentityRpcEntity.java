/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@MappedSuperclass
public abstract class IdentityRpcEntity<K extends Serializable> implements
		RpcEntity<K> {
	private static final long serialVersionUID = -431838425185553286L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private K id;

	public IdentityRpcEntity() {
	}

	@Override
	public void setId(K k) {
		this.id = k;
	}

	@Override
	public K getId() {
		return id;
	}

}
