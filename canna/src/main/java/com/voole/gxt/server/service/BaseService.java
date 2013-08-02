package com.voole.gxt.server.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.server.dao.authority.AuthorityGroupRpcDao;
import com.voole.gxt.server.dao.authority.AuthorityRpcMethodDao;
import com.voole.gxt.server.dao.idc.IDCCabinetDao;
import com.voole.gxt.server.dao.idc.IDCDao;
import com.voole.gxt.server.dao.idc.IDCHostingDao;
import com.voole.gxt.server.dao.idc.IDCIPDao;
import com.voole.gxt.server.dao.idc.IDCLeaseTypeDao;
import com.voole.gxt.server.dao.idc.IDCPartnerDao;
import com.voole.gxt.server.dao.idc.IDCSwitchDao;
import com.voole.gxt.server.dao.idc.IDCUPlaceDao;
import com.voole.gxt.server.dao.menu.MenuDao;
import com.voole.gxt.server.dao.menu.MenuGroupDao;
import com.voole.gxt.server.dao.project.ProjectDao;
import com.voole.gxt.server.dao.project.ProjectLeaderDao;
import com.voole.gxt.server.dao.project.ProjectManagerDao;
import com.voole.gxt.server.dao.project.ProjectPartnerDao;
import com.voole.gxt.server.dao.user.UserDao;
import com.voole.gxt.server.dao.user.UserGroupDao;
import com.voole.gxt.server.util.HibernateBeanReplicatorUtil;

public abstract class BaseService<T> implements CannaService<T> {
	@Autowired
	protected MenuDao menuDao;
	@Autowired
	protected MenuGroupDao menuGroupDao;
	@Autowired
	protected ProjectLeaderDao projectLeaderDao;
	@Autowired
	protected ProjectManagerDao projectManagerDao;
	@Autowired
	protected ProjectPartnerDao projectPartnerDao;
	@Autowired
	protected ProjectDao projectDao;
	@Autowired
	protected IDCCabinetDao idcCabinetDao;
	@Autowired
	protected IDCUPlaceDao idcUPlaceDao;
	@Autowired
	protected IDCSwitchDao idcSwitchDao;
	@Autowired
	protected IDCPartnerDao idcPartnerDao;
	@Autowired
	protected IDCLeaseTypeDao idcLeaseTypeDao;
	@Autowired
	protected IDCIPDao idcIPDao;
	@Autowired
	protected IDCHostingDao idcHostingDao;
	@Autowired
	protected IDCDao idcDao;
	@Autowired
	protected AuthorityGroupRpcDao authorityRpcGroupDao;
	@Autowired
	protected AuthorityRpcMethodDao authorityRpcMethodDao;
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected UserGroupDao userGroupDao;

	public abstract IBaseDao<T> getDao();

	@Override
	public T saveReturnObjct(T t) throws CannaException,
			ConstraintViolationException {
		return getDao().saveReturnObjct(t);
	}

	@Override
	public Long save(T t) throws CannaException, ConstraintViolationException {
		return getDao().save(t);
	}

	@Override
	public void update(T t) throws CannaException, ConstraintViolationException {
		getDao().update(t);

	}

	@Override
	public void delete(List<Long> ids) throws CannaException {
		getDao().delete(ids);

	}

	@Override
	public void delete(Long id) throws CannaException {
		getDao().delete(id);

	}

	@Override
	public List<T> get() throws CannaException {
		return HibernateBeanReplicatorUtil
				.excludeCollectionCopy(getDao().get());
	}

	@Override
	public PagingLoadResult<T> get(CannaPagingLoadConfigBean config)
			throws CannaException {
		PagingLoadResultBean<T> pr = (PagingLoadResultBean<T>) getDao().get(
				config);
		excludeCollectionCopy(pr);
		return pr;
	}

	public void excludeCollectionCopy(PagingLoadResultBean<T> pr) {
		pr.setData(HibernateBeanReplicatorUtil.excludeCollectionCopy(pr
				.getData()));
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}
}
