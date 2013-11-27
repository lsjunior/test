package br.net.woodstock.sp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Logs {

	private static final String	NAME	= "br.net.woodstock.sp";

	private static final Logger	LOGGER	= LoggerFactory.getLogger(Logs.NAME);

	private Logs() {
		//
	}

	public static Logger getLogger() {
		return Logs.LOGGER;
	}

}
