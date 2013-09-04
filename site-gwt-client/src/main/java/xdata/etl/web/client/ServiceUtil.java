package xdata.etl.web.client;

import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.entity.businessmeta.Business;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcService;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcServiceAsync;
import xdata.etl.web.shared.service.authority.AuthorityRpcService;
import xdata.etl.web.shared.service.authority.AuthorityRpcServiceAsync;
import xdata.etl.web.shared.service.businessmeta.BusinessRpcService;
import xdata.etl.web.shared.service.businessmeta.BusinessRpcServiceAsync;
import xdata.etl.web.shared.service.businessmeta.BusinessToHbaseTableMappingRpcService;
import xdata.etl.web.shared.service.businessmeta.BusinessToHbaseTableMappingRpcServiceAsync;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnRpcServiceAsync;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableRpcServiceAsync;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableVersionRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableVersionRpcServiceAsync;
import xdata.etl.web.shared.service.menu.MenuNodeRpcService;
import xdata.etl.web.shared.service.menu.MenuNodeRpcServiceAsync;
import xdata.etl.web.shared.service.user.UserGroupRpcService;
import xdata.etl.web.shared.service.user.UserGroupRpcServiceAsync;
import xdata.etl.web.shared.service.user.UserRpcService;
import xdata.etl.web.shared.service.user.UserRpcServiceAsync;

import com.google.gwt.core.shared.GWT;

public class ServiceUtil {
	public static final RpcCaller<Integer, MenuNode> MenuNodeRpcCaller = new RpcCaller<Integer, MenuNode>(
			GWT.<MenuNodeRpcServiceAsync> create(MenuNodeRpcService.class));
	public static final RpcCaller<Integer, User> UserRpcCaller = new RpcCaller<Integer, User>(
			GWT.<UserRpcServiceAsync> create(UserRpcService.class));
	public static final RpcCaller<Integer, UserGroup> UserGroupRpcCaller = new RpcCaller<Integer, UserGroup>(
			GWT.<UserGroupRpcServiceAsync> create(UserGroupRpcService.class));
	public static final RpcCaller<Integer, AuthorityGroup> AuthorityGroupRpcCaller = new RpcCaller<Integer, AuthorityGroup>(
			GWT.<AuthorityGroupRpcServiceAsync> create(AuthorityGroupRpcService.class));
	public static final RpcCaller<Integer, Authority> AuthorityRpcCaller = new RpcCaller<Integer, Authority>(
			GWT.<AuthorityRpcServiceAsync> create(AuthorityRpcService.class));
	public static final RpcCaller<Integer, HbaseTableColumn> HbaseTableColumnRpcCaller = new RpcCaller<Integer, HbaseTableColumn>(
			GWT.<HbaseTableColumnRpcServiceAsync> create(HbaseTableColumnRpcService.class));
	public static final RpcCaller<Integer, HbaseTable> HbaseTableRpcCaller = new RpcCaller<Integer, HbaseTable>(
			GWT.<HbaseTableRpcServiceAsync> create(HbaseTableRpcService.class));
	public static final RpcCaller<Integer, HbaseTableVersion> HbaseTableVersionRpcCaller = new RpcCaller<Integer, HbaseTableVersion>(
			GWT.<HbaseTableVersionRpcServiceAsync> create(HbaseTableVersionRpcService.class));
	public static final RpcCaller<Integer, Business> BusinessRpcCaller = new RpcCaller<Integer, Business>(
			GWT.<BusinessRpcServiceAsync> create(BusinessRpcService.class));

	public static final RpcCaller<Integer, BusinessToHbaseTableMapping> BusinessToHbaseTableMappingRpcCaller = new RpcCaller<Integer, BusinessToHbaseTableMapping>(
			GWT.<BusinessToHbaseTableMappingRpcServiceAsync> create(BusinessToHbaseTableMappingRpcService.class));
}
