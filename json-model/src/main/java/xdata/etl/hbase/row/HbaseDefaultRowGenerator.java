package xdata.etl.hbase.row;

import xdata.etl.hbase.entity.HbaseAttachment;
import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseDefaultRowGenerator implements HbaseRowGenerator {
	@Override
	public String generate(HbaseEntity entity) {
		String row = null;
		if (entity instanceof HbaseAttachment) {
			row = generateAttachmentRowKey((HbaseAttachment) entity);
		} else {
			row = generateMainPartRowKey(entity);
		}
		entity.setRow(row);
		return row;
	}

	public String generateMainPartRowKey(HbaseEntity entity) {
		return null;
	}

	/**
	 * @return 1000~9999
	 */
	protected static Long getRandom() {
		return (long) Math.ceil(1000 + 9000 * Math.random());
	}

	public String generateAttachmentRowKey(HbaseAttachment entity) {
		entity.setMainPartRow(entity.getMainPart().getRow());
		String row = entity.getMainPartRow() + "_" + entity.getIndex();
		return row;
	}
}
