package net.woodstock.rockapi.ejb.test;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.TestCase;

public class TestEJB extends TestCase {

	private static final boolean	USE_EAR					= true;

	private static final String		EAR_NAME				= "ear-test";

	protected static final String	CLIENT_PROPERTY			= "ejb-client.properties";

	protected static final String	CLIENT_PROPERTY_LOGIN	= "ejb-client-login.properties";

	private String getName(String name) {
		if (TestEJB.USE_EAR) {
			name = TestEJB.EAR_NAME + "/" + name;
		}
		return name;
	}

	protected Object lookup(String name) throws Exception {
		// Properties properties = new Properties();
		// InputStream inputStream = TestEJB.class.getClassLoader().getResourceAsStream(CLIENT_PROPERTY);
		// properties.load(inputStream);
		// Context context = new InitialContext(properties);

		// Use jndi.properties
		Context context = new InitialContext();

		name = this.getName(name);
		return context.lookup(name);
	}

}
