package de.hybris.platform.cuppytrail.utils;

import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
import groovy.text.GStringTemplateEngine;
import groovy.text.Template;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class GStringUtils {

	private static final GStringTemplateEngine gStringEngine =
			new GStringTemplateEngine(Thread.currentThread().getContextClassLoader());

	private static final MapSplitter paramSplitter = Splitter.on(',').withKeyValueSeparator(':');

	public static String of(String line, Map<String, ?> params) {
		checkArgument(StringUtils.isNotBlank(line), "Line should be not blank");
		try {
			Template stringTmpl = gStringEngine.createTemplate(line);
			return params == null || params.isEmpty() ? stringTmpl.make().toString() :
														stringTmpl.make(params).toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String of(String line) {
		return of(line, Collections.emptyMap());
	}

	public static String of(String line, String params) {
		return of(line, paramSplitter.split(params));
	}
}
