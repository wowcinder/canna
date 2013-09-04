/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared;

import xdata.etl.web.shared.entity.businessmeta.Business;
import xdata.etl.web.shared.entity.businessmeta.c.VerticalBarSplitBusiness;
import xdata.etl.web.shared.entity.businessmeta.json.JsonBusiness;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public enum BusinessType {
	C_TYPE() {
		@Override
		public Business createBusiness() {
			return new VerticalBarSplitBusiness();
		}

	},
	JSON_TYPE() {
		@Override
		public Business createBusiness() {
			return new JsonBusiness();
		}

	};

	public abstract Business createBusiness();

	public static class Names {
		public static final String C_TYPE = "C_TYPE";
		public static final String JSON_TYPE = "JSON_TYPE";
	}
}
