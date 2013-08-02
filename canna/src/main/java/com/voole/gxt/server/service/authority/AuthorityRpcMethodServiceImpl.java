package com.voole.gxt.server.service.authority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.beanlib.CollectionPropertyName;
import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.authority.AuthorityRpcMethodService;
import com.voole.gxt.server.dao.authority.AuthorityRpcMethodDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

@Service
public class AuthorityRpcMethodServiceImpl extends
		BaseService<AuthorityRpcMethod> implements AuthorityRpcMethodService {

	@Override
	public AuthorityRpcMethodDao getDao() {
		return authorityRpcMethodDao;
	}

	@Override
	public List<AuthorityRpcService> getService() throws CannaException {
		CollectionPropertyName<?>[] colProps = { new CollectionPropertyName<AuthorityRpcService>(
				AuthorityRpcService.class, "methods") };
		Set<CollectionPropertyName<?>> collectionPropertyNameSet = new HashSet<CollectionPropertyName<?>>(
				Arrays.asList(colProps));
		Hibernate3BeanReplicator excludeCollectionRep = new Hibernate3BeanReplicator(
				null, collectionPropertyNameSet, null);
		return excludeCollectionRep.copy(getDao().getService());
	}

	@Override
	public void updatePassStatus(List<Long> ids, Boolean isAlwaysPass)
			throws CannaException {
		getDao().updatePassStatus(ids, isAlwaysPass);

	}

	@Override
	public List<AuthorityRpcMethod> getAuthorityRpcMethodsForComBox()
			throws CannaException {
		return getDao().getAuthorityRpcMethodsForComBox();
	}

}
