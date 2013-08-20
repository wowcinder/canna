package xdata.etl.web.server.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xdata.etl.web.server.util.SpringMVCGwtRpcProxyUtilInterface;
import xdata.etl.web.shared.service.authority.AuthorityGroupService;
import xdata.etl.web.shared.service.authority.AuthorityService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableVersionService;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryService;
import xdata.etl.web.shared.service.login.LoginService;
import xdata.etl.web.shared.service.menu.MenuGroupService;
import xdata.etl.web.shared.service.menu.MenuService;
import xdata.etl.web.shared.service.user.UserGroupService;
import xdata.etl.web.shared.service.user.UserService;

import com.google.gwt.user.client.rpc.SerializationException;

@Controller
@RequestMapping("etl/rpc")
public class GwtRpcController {
	@Autowired
	private SpringMVCGwtRpcProxyUtilInterface proxyUtil;

	public GwtRpcController() {
	}

	@RequestMapping("authority")
	public void authority(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(AuthorityService.class).processPost(request,
				response);
	}

	@RequestMapping("authority_group")
	public void authorityGroup(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(AuthorityGroupService.class).processPost(request,
				response);
	}

	@RequestMapping("user")
	public void user(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(UserService.class).processPost(request, response);
	}

	@RequestMapping("user_group")
	public void userGroup(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(UserGroupService.class).processPost(request,
				response);
	}

	@RequestMapping("menu")
	public void menu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(MenuService.class).processPost(request, response);
	}

	@RequestMapping("menu_group")
	public void menuGroup(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(MenuGroupService.class).processPost(request,
				response);
	}

	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(LoginService.class).processPost(request, response);
	}

	@RequestMapping("hbase_table")
	public void hbaseTable(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseTableService.class).processPost(request,
				response);
	}

	@RequestMapping("hbase_table_version")
	public void hbaseTableVersion(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseTableVersionService.class).processPost(
				request, response);
	}

	@RequestMapping("hbase_table_column")
	public void hbaseTableColumn(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseTableColumnService.class).processPost(
				request, response);
	}

	@RequestMapping("hbase_query")
	public void hbaseQuery(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseQueryService.class).processPost(
				request, response);
	}
}
