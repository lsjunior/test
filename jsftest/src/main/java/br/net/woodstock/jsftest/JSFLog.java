package br.net.woodstock.jsftest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSFLog {

	private static final Logger	LOGGER	= LoggerFactory.getLogger("br.net.woodstock.jsftest");

	public static Logger getLogger() {
		return JSFLog.LOGGER;
	}

}
