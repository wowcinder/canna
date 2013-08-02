/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity;

import java.io.Serializable;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public interface RpcEntity<K extends Serializable> extends Serializable {

	void setId(K k);

	K getId();
}
