package xdata.etl.web.server.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.server.util.SpringMVCGwtRpcProxyUtilInterface;

import com.google.gwt.user.client.rpc.SerializationException;

@Controller
@RequestMapping("etl/rpc")
public class GwtRpcController {
	@Autowired
	private SpringMVCGwtRpcProxyUtilInterface proxyUtil;

	public GwtRpcController() {
	}

	@RequestMapping("test")
	public void test(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SerializationException {
		proxyUtil.getService(AuthorityService.class).processPost(request, response);
	}
}
