package aten.com.backoffice.util.service;

import javax.servlet.http.HttpServletRequest;

public interface proxyInfo {

	public String proxy(HttpServletRequest request, String Url) throws Exception;
	
}
