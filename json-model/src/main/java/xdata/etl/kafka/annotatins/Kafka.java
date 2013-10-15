package xdata.etl.kafka.annotatins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import xdata.etl.kafka.transform.KafkaTransformer;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Kafka {
	String topic();

	Class<? extends KafkaTransformer> transformer();


	boolean isSnap() default false;
}
