/*
 * @(#)ExtendMappingJacksonJsonpView.java $version 2012. 4. 20.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.guesthouse.roi.common.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MappingJacksonJsonpView filter
 * 
 * content type : application/javascript
 * jsonp 응답용으로 다시 확장
 * 
 * @author handee.sohn
 */
public class ExtendMappingJacksonJsonpView extends ExtendMappingJacksonJsonView {

	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

	/**
	 * Prepares the view given the specified model, merging it with static
	 * attributes and a RequestContext attribute, if necessary.
	 * Delegates to renderMergedOutputModel for the actual rendering.
	 * @see #renderMergedOutputModel
	 */
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if ("GET".equals(request.getMethod().toUpperCase())) {
			Map<String, String[]> params = request.getParameterMap();

			if (params.containsKey("callback")) {
				response.getOutputStream().write(new String(params.get("callback")[0] + "(").getBytes());
				super.render(model, request, response);
				response.getOutputStream().write(new String(");").getBytes());
				response.setContentType("application/javascript");
			}

			else {
				super.render(model, request, response);
			}
		}

		else {
			super.render(model, request, response);
		}
	}
}
