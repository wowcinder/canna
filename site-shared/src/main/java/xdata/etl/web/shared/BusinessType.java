/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared;

import xdata.etl.web.shared.entity.businessmeta.Business;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusiness;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessToHbaseTableMapping;
import xdata.etl.web.shared.entity.businessmeta.json.JsonBusiness;
import xdata.etl.web.shared.entity.businessmeta.json.JsonBusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public enum BusinessType {
	C_TYPE() {
		@Override
		public Business createBusiness() {
			return new CTypeBusiness();
		}

		@Override
		public BusinessToHbaseTableMapping createBusinessToHbaseTableMapping() {
			return new CTypeBusinessToHbaseTableMapping();
		}

	},
	JSON_TYPE() {
		@Override
		public Business createBusiness() {
			return new JsonBusiness();
		}

		@Override
		public BusinessToHbaseTableMapping createBusinessToHbaseTableMapping() {
			return new JsonBusinessToHbaseTableMapping();
		}

	};

	public abstract Business createBusiness();

	public abstract BusinessToHbaseTableMapping createBusinessToHbaseTableMapping();

	public static class Names {
		public static final String C_TYPE = "C_TYPE";
		public static final String JSON_TYPE = "JSON_TYPE";
	}

	public static enum BusinessColumnType {
		SIMPLE, SUB_MAPPING;

		public static class Names {
			public static final String SIMPLE = "SIMPLE";
			public static final String SUB_MAPPING = "SUB_MAPPING";
		}
	}
}
