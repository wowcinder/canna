/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public interface SubMappingBusinessColumn<T extends BusinessToHbaseTableMapping> {
	public T getSubMapping();

	public void setSubMapping(T subMapping);
}
