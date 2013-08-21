package xdata.etl.web.server;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import xdata.etl.web.server.service.login.LoginService;

@Controller
@RequestMapping("etl/rpc")
public class GwtRpcController {
	@Resource
	LoginService service;

	public GwtRpcController() {
	}

	@RequestMapping("{rpcPath:[^\\.]+\\.rpc}")
	public void rpc(@PathVariable String rpcPath, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println(rpcPath);
	}
}
