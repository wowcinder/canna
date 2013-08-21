/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.hbasequery;

import java.util.Date;
import java.util.List;

import xdata.etl.web.shared.hbasequery.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
public interface HbaseQueryService {
	List<HbaseRecord<String>> getData(String table, String[] versions);

	Short dummyShort();

	Integer dummyInteger();

	Long dummyLong();

	Boolean dummyBoolean();

	Date dummyDate();

	Character dummyCharacter();

}
