package xdata.etl.kafka.transform;

import xdata.etl.exception.KafkaTransformException;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class AssistTransformer implements KafkaTransformer {


	@Override
	public KafkaTransformResult transform(Class<? extends HbaseEntity> clazz,
			String raw, KafkaTransformHandler... handlers)
			throws KafkaTransformException {
		return null;
	}


}
