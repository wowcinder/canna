package xdata.etl.web.client.service;

import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.client.service.authority.AuthorityGroupServiceAsync;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.client.service.authority.AuthorityServiceAsync;
import xdata.etl.web.client.service.hbasemeta.HbaseTableColumnService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableColumnServiceAsync;
import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableServiceAsync;
import xdata.etl.web.client.service.hbasemeta.HbaseTableVersionService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableVersionServiceAsync;
import xdata.etl.web.client.service.menu.MenuGroupService;
import xdata.etl.web.client.service.menu.MenuGroupServiceAsync;
import xdata.etl.web.client.service.menu.MenuService;
import xdata.etl.web.client.service.menu.MenuServiceAsync;
import xdata.etl.web.client.service.user.UserGroupService;
import xdata.etl.web.client.service.user.UserGroupServiceAsync;
import xdata.etl.web.client.service.user.UserService;
import xdata.etl.web.client.service.user.UserServiceAsync;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;

public class ServiceUtil {
	public static final EntityRpcCaller<Integer, Menu> MenuRpcCaller = new EntityRpcCaller<Integer, Menu>(
			GWT.<MenuServiceAsync> create(MenuService.class));
	public static final EntityRpcCaller<Integer, MenuGroup> MenuGroupRpcCaller = new EntityRpcCaller<Integer, MenuGroup>(
			GWT.<MenuGroupServiceAsync> create(MenuGroupService.class));
	public static final EntityRpcCaller<Integer, User> UserRpcCaller = new EntityRpcCaller<Integer, User>(
			GWT.<UserServiceAsync> create(UserService.class));
	public static final EntityRpcCaller<Integer, UserGroup> UserGroupRpcCaller = new EntityRpcCaller<Integer, UserGroup>(
			GWT.<UserGroupServiceAsync> create(UserGroupService.class));
	public static final EntityRpcCaller<Integer, AuthorityGroup> AuthorityGroupRpcCaller = new EntityRpcCaller<Integer, AuthorityGroup>(
			GWT.<AuthorityGroupServiceAsync> create(AuthorityGroupService.class));
	public static final EntityRpcCaller<Integer, Authority> AuthorityRpcCaller = new EntityRpcCaller<Integer, Authority>(
			GWT.<AuthorityServiceAsync> create(AuthorityService.class));
	public static final EntityRpcCaller<Integer, HbaseTableColumn> HbaseTableColumnRpcCaller = new EntityRpcCaller<Integer, HbaseTableColumn>(
			GWT.<HbaseTableColumnServiceAsync> create(HbaseTableColumnService.class));
	public static final EntityRpcCaller<Integer, HbaseTable> HbaseTableRpcCaller = new EntityRpcCaller<Integer, HbaseTable>(
			GWT.<HbaseTableServiceAsync> create(HbaseTableService.class));
	public static final EntityRpcCaller<Integer, HbaseTableVersion> HbaseTableVersionRpcCaller = new EntityRpcCaller<Integer, HbaseTableVersion>(
			GWT.<HbaseTableVersionServiceAsync> create(HbaseTableVersionService.class));
}
