package xdata.etl.web.client.common.grid;

import java.io.Serializable;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.RpcEntity;

public class RpcEntityGridBuilder<K extends Serializable, V extends RpcEntity<K>, P extends RpcEntityPropertyAccess<K, V>>
		extends GridBuilder<V> {
	private P props;

	public RpcEntityGridBuilder(P props) {
		setKeyProvider(props.key());
	}

	public P getProps() {
		return props;
	}

	public void setProps(P props) {
		this.props = props;
	}

}
