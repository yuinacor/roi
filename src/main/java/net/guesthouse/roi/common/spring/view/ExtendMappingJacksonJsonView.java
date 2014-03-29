/*
 * @(#)ExtendMappingJacksonJsonView.java $version 2013. 5. 15.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.guesthouse.roi.common.spring.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * ExtendMappingJacksonJsonView filter
 * 
 * @author JaeHyuk Kwak
 */
public class ExtendMappingJacksonJsonView extends MappingJacksonJsonView {
	@Override
	@SuppressWarnings("unchecked")
	protected Object filterModel(Map<String, Object> model) {

		Map<String, Object> superResult = (Map<String, Object>)super.filterModel(model);

		if (superResult == null || superResult.size() == 0) {
			return superResult;
		}

		Map<String, Object> result = new HashMap<String, Object>();
		for (String key : superResult.keySet()) {
			result.put(key, superResult.get(key));
		}

		if (result.size() == 1) {
			return result.values().toArray()[0];
		}

		return result;
	}
}
