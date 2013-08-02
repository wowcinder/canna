package com.voole.gxt.server.util;

import static com.google.gwt.user.client.rpc.RpcRequestBuilder.STRONG_NAME_HEADER;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;

public class GWTRemoteServiceServletHelper implements
		SerializationPolicyProvider {
	private static Logger logger = LoggerFactory
			.getLogger(GWTRemoteServiceServletHelper.class);

	private static void log(Object msg, Throwable t) {
		logger.warn(msg.toString(), t);
	}

	private static void log(Object msg) {
		logger.warn(msg.toString());
	}

	private final Map<String, SerializationPolicy> serializationPolicyCache = new HashMap<String, SerializationPolicy>();
	private final Object delegate;

	public GWTRemoteServiceServletHelper(Object delegate) {
		this.delegate = delegate;
	}

	public Object getDelegate() {
		return delegate;
	}

	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	private HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	private ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public void execute() throws ServletException, IOException,
			SerializationException {
		String requestPayload = readContent(getRequest());
		String responsePayload = processCall(requestPayload);
		writeResponse(responsePayload);
	}

	public String processCall(String payload) throws SerializationException {
		checkPermutationStrongName();
		try {
			RPCRequest rpcRequest = RPC.decodeRequest(payload,
					delegate.getClass(), this);
			onAfterRequestDeserialized(rpcRequest);
			return RPC.invokeAndEncodeResponse(delegate,
					rpcRequest.getMethod(), rpcRequest.getParameters(),
					rpcRequest.getSerializationPolicy(), rpcRequest.getFlags());
		} catch (IncompatibleRemoteServiceException ex) {
			log("An IncompatibleRemoteServiceException was thrown while processing this call.",
					ex);
			return RPC.encodeResponseForFailure(null, ex);
		} catch (RpcTokenException tokenException) {
			log("An RpcTokenException was thrown while processing this call.",
					tokenException);
			return RPC.encodeResponseForFailure(null, tokenException);
		}
	}

	protected void onAfterRequestDeserialized(RPCRequest rpcRequest) {
	}

	protected final String getPermutationStrongName() {
		return getRequest().getHeader(STRONG_NAME_HEADER);
	}

	protected void checkPermutationStrongName() throws SecurityException {
		if (getPermutationStrongName() == null) {
			throw new SecurityException(
					"Blocked request without GWT permutation header (XSRF attack?)");
		}
	}

	protected String readContent(HttpServletRequest request)
			throws ServletException, IOException {
		return RPCServletUtils.readContentAsGwtRpc(request);
	}

	private void writeResponse(String responsePayload) throws IOException {
		boolean gzipEncode = RPCServletUtils.acceptsGzipEncoding(getRequest())
				&& shouldCompressResponse(responsePayload);

		RPCServletUtils.writeResponse(getServletContext(), getResponse(),
				responsePayload, gzipEncode);
	}

	protected boolean shouldCompressResponse(String responsePayload) {
		return RPCServletUtils
				.exceedsUncompressedContentLengthLimit(responsePayload);
	}

	@Override
	public SerializationPolicy getSerializationPolicy(String moduleBaseURL,
			String strongName) {

		SerializationPolicy serializationPolicy = getCachedSerializationPolicy(
				moduleBaseURL, strongName);
		if (serializationPolicy != null) {
			return serializationPolicy;
		}

		serializationPolicy = doGetSerializationPolicy(moduleBaseURL,
				strongName);

		if (serializationPolicy == null) {
			// Failed to get the requested serialization policy; use the default
			log("WARNING: Failed to get the SerializationPolicy '"
					+ strongName
					+ "' for module '"
					+ moduleBaseURL
					+ "'; a legacy, 1.3.3 compatible, serialization policy will be used.  You may experience SerializationExceptions as a result.");
			serializationPolicy = RPC.getDefaultSerializationPolicy();
		}

		// This could cache null or an actual instance. Either way we will not
		// attempt to lookup the policy again.
		putCachedSerializationPolicy(moduleBaseURL, strongName,
				serializationPolicy);

		return serializationPolicy;
	}

	private SerializationPolicy getCachedSerializationPolicy(
			String moduleBaseURL, String strongName) {
		synchronized (serializationPolicyCache) {
			return serializationPolicyCache.get(moduleBaseURL + strongName);
		}
	}

	private void putCachedSerializationPolicy(String moduleBaseURL,
			String strongName, SerializationPolicy serializationPolicy) {
		synchronized (serializationPolicyCache) {
			serializationPolicyCache.put(moduleBaseURL + strongName,
					serializationPolicy);
		}
	}

	protected SerializationPolicy doGetSerializationPolicy(
			String moduleBaseURL, String strongName) {
		return loadSerializationPolicy(getServletContext(), getRequest(),
				moduleBaseURL, strongName);
	}

	static SerializationPolicy loadSerializationPolicy(ServletContext context,
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		String contextPath = request.getContextPath();
		String modulePath = null;
		if (moduleBaseURL != null) {
			try {
				modulePath = new URL(moduleBaseURL).getPath();
			} catch (MalformedURLException ex) {
				// log the information, we will default
				log("Malformed moduleBaseURL: " + moduleBaseURL, ex);
			}
		}

		SerializationPolicy serializationPolicy = null;

		/*
		 * Check that the module path must be in the same web app as the servlet
		 * itself. If you need to implement a scheme different than this,
		 * override this method.
		 */
		if (modulePath == null || !modulePath.startsWith(contextPath)) {
			String message = "ERROR: The module path requested, "
					+ modulePath
					+ ", is not in the same web application as this servlet, "
					+ contextPath
					+ ".  Your module may not be properly configured or your client and server code maybe out of date.";
			log(message);
		} else {
			// Strip off the context path from the module base URL. It should be
			// a
			// strict prefix.
			String contextRelativePath = modulePath.substring(contextPath
					.length());

			String serializationPolicyFilePath = SerializationPolicyLoader
					.getSerializationPolicyFileName(contextRelativePath
							+ strongName);

			// Open the RPC resource file and read its contents.
			InputStream is = context
					.getResourceAsStream(serializationPolicyFilePath);
			try {
				if (is != null) {
					try {
						serializationPolicy = SerializationPolicyLoader
								.loadFromStream(is, null);
					} catch (ParseException e) {
						log("ERROR: Failed to parse the policy file '"
								+ serializationPolicyFilePath + "'", e);
					} catch (IOException e) {
						log("ERROR: Could not read the policy file '"
								+ serializationPolicyFilePath + "'", e);
					}
				} else {
					String message = "ERROR: The serialization policy file '"
							+ serializationPolicyFilePath
							+ "' was not found; did you forget to include it in this deployment?";
					log(message);
				}
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// Ignore this error
					}
				}
			}
		}

		return serializationPolicy;
	}

}
