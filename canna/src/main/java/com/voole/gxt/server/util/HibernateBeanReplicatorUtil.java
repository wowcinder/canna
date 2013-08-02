package com.voole.gxt.server.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.beanlib.CollectionPropertyName;
import net.sf.beanlib.hibernate.HibernateBeanReplicator;
import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;

public class HibernateBeanReplicatorUtil {

	private static HibernateBeanReplicator simpleRep;
	private static Hibernate3BeanReplicator excludeCollectionRep;

	public static HibernateBeanReplicator getSimpleRep() {
		if (simpleRep == null) {
			createSimpleRep();
		}
		return simpleRep;
	}

	public static HibernateBeanReplicator getExcludeCollectionRep() {
		if (excludeCollectionRep == null) {
			createExcludeCollectionRep();
		}
		return excludeCollectionRep;
	}

	public static <T> T simpleCopy(T t) {
		return getSimpleRep().copy(t);
	}

	public static <T> T excludeCollectionCopy(T t) {
		return getExcludeCollectionRep().copy(t);
	}

	private synchronized static void createExcludeCollectionRep() {
		if (excludeCollectionRep == null) {
			CollectionPropertyName<?>[] colProps = {};
			Set<CollectionPropertyName<?>> collectionPropertyNameSet = new HashSet<CollectionPropertyName<?>>(
					Arrays.asList(colProps));
			excludeCollectionRep = new Hibernate3BeanReplicator(null,
					collectionPropertyNameSet, null);
		}

	}

	private synchronized static void createSimpleRep() {
		if (simpleRep == null) {
			simpleRep = new Hibernate3BeanReplicator();
		}
	}

}
