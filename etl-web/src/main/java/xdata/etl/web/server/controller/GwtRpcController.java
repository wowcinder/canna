package xdata.etl.web.server.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xdata.etl.web.server.util.SpringMVCGwtRpcProxyUtilInterface;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcService;
import xdata.etl.web.shared.service.authority.AuthorityRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableRpcService;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableVersionRpcService;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryRpcService;
import xdata.etl.web.shared.service.login.LoginRpcService;
import xdata.etl.web.shared.service.menu.MenuGroupRpcService;
import xdata.etl.web.shared.service.menu.MenuRpcService;
import xdata.etl.web.shared.service.user.UserGroupRpcService;
import xdata.etl.web.shared.service.user.UserRpcService;

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
		proxyUtil.getService(AuthorityRpcService.class).processPost(request,
				response);
	}

	@RequestMapping("authority_group")
	public void authorityGroup(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(AuthorityGroupRpcService.class).processPost(request,
				response);
	}

	@RequestMapping("user")
	public void user(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(UserRpcService.class).processPost(request, response);
	}

	@RequestMapping("user_group")
	public void userGroup(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(UserGroupRpcService.class).processPost(request,
				response);
	}

	@RequestMapping("menu")
	public void menu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(MenuRpcService.class).processPost(request, response);
	}

	@RequestMapping("menu_group")
	public void menuGroup(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(MenuGroupRpcService.class).processPost(request,
				response);
	}

	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(LoginRpcService.class).processPost(request, response);
	}

	@RequestMapping("hbase_table")
	public void hbaseTable(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseTableRpcService.class).processPost(request,
				response);
	}

	@RequestMapping("hbase_table_version")
	public void hbaseTableVersion(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseTableVersionRpcService.class).processPost(
				request, response);
	}

	@RequestMapping("hbase_table_column")
	public void hbaseTableColumn(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseTableColumnRpcService.class).processPost(
				request, response);
	}

	@RequestMapping("hbase_query")
	public void hbaseQuery(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		proxyUtil.getService(HbaseQueryRpcService.class).processPost(
				request, response);
	}
}
