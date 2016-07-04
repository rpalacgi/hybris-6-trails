package de.hybris.platform.cuppytrailfrontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StadiumsNameEncoded {

	private final static Logger LOG = LoggerFactory.getLogger(StadiumsNameEncoded.class);

	public static String getNameEncoded(String name) {
		try {
			return URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException exception) {
			LOG.error("Problems while encoding", exception);
			return "";
		}
	}
}


