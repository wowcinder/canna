package xdata.etl.web.client;

import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcService;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcServiceAsync;
import xdata.etl.web.shared.service.authority.AuthorityRpcService;
import xdata.etl.web.shared.service.authority.AuthorityRpcServiceAsync;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnRpcServiceAsync;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableRpcServiceAsync;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableVersionRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableVersionRpcServiceAsync;
import xdata.etl.web.shared.service.menu.MenuGroupRpcService;
import xdata.etl.web.shared.service.menu.MenuGroupRpcServiceAsync;
import xdata.etl.web.shared.service.menu.MenuRpcService;
import xdata.etl.web.shared.service.menu.MenuRpcServiceAsync;
import xdata.etl.web.shared.service.user.UserGroupRpcService;
import xdata.etl.web.shared.service.user.UserGroupRpcServiceAsync;
import xdata.etl.web.shared.service.user.UserRpcService;
import xdata.etl.web.shared.service.user.UserRpcServiceAsync;

import com.google.gwt.core.shared.GWT;

public class ServiceUtil {
	public static final EntityRpcCaller<Integer, Menu> MenuRpcCaller = new EntityRpcCaller<Integer, Menu>(
			GWT.<MenuRpcServiceAsync> create(MenuRpcService.class));
	public static final EntityRpcCaller<Integer, MenuGroup> MenuGroupRpcCaller = new EntityRpcCaller<Integer, MenuGroup>(
			GWT.<MenuGroupRpcServiceAsync> create(MenuGroupRpcService.class));
	public static final EntityRpcCaller<Integer, User> UserRpcCaller = new EntityRpcCaller<Integer, User>(
			GWT.<UserRpcServiceAsync> create(UserRpcService.class));
	public static final EntityRpcCaller<Integer, UserGroup> UserGroupRpcCaller = new EntityRpcCaller<Integer, UserGroup>(
			GWT.<UserGroupRpcServiceAsync> create(UserGroupRpcService.class));
	public static final EntityRpcCaller<Integer, AuthorityGroup> AuthorityGroupRpcCaller = new EntityRpcCaller<Integer, AuthorityGroup>(
			GWT.<AuthorityGroupRpcServiceAsync> create(AuthorityGroupRpcService.class));
	public static final EntityRpcCaller<Integer, Authority> AuthorityRpcCaller = new EntityRpcCaller<Integer, Authority>(
			GWT.<AuthorityRpcServiceAsync> create(AuthorityRpcService.class));
	public static final EntityRpcCaller<Integer, HbaseTableColumn> HbaseTableColumnRpcCaller = new EntityRpcCaller<Integer, HbaseTableColumn>(
			GWT.<HbaseTableColumnRpcServiceAsync> create(HbaseTableColumnRpcService.class));
	public static final EntityRpcCaller<Integer, HbaseTable> HbaseTableRpcCaller = new EntityRpcCaller<Integer, HbaseTable>(
			GWT.<HbaseTableRpcServiceAsync> create(HbaseTableRpcService.class));
	public static final EntityRpcCaller<Integer, HbaseTableVersion> HbaseTableVersionRpcCaller = new EntityRpcCaller<Integer, HbaseTableVersion>(
			GWT.<HbaseTableVersionRpcServiceAsync> create(HbaseTableVersionRpcService.class));
}
