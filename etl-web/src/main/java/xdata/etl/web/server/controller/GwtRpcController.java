package xdata.etl.web.server.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.client.service.user.UserGroupService;
import xdata.etl.web.client.service.user.UserService;
import xdata.etl.web.server.util.SpringMVCGwtRpcProxyUtilInterface;

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
}
