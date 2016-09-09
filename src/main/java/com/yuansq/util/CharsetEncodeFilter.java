package com.yuansq.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetEncodeFilter implements Filter {

	private static String DEFALT_ENCODING = "UTF-8";
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		req.setCharacterEncoding(DEFALT_ENCODING);
		resp.setCharacterEncoding(DEFALT_ENCODING);
		resp.setContentType("text/html;charset="+DEFALT_ENCODING);
		filterChain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		String encoding = config.getInitParameter("encoding");
		if(encoding != null&& !"".equals(encoding)) {
			DEFALT_ENCODING = encoding;
		}
	}

}