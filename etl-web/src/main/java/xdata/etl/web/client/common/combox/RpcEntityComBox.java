package xdata.etl.web.client.common.combox;

import java.io.Serializable;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.data.shared.LabelProvider;

public class RpcEntityComBox<K extends Serializable, V extends RpcEntity<K>>
		extends EtlComBox<V> {

	public RpcEntityComBox(RpcEntityPropertyAccess<K, V> props,
			LabelProvider<V> labelProvider) {
		super(props.key(), labelProvider);
	}
}
