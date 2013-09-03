package net.woodstock.rockframework.test.struts2.sso;

import java.security.Principal;

import net.woodstock.rockframework.web.tomcat.AbstractFormAuthenticator;
import net.woodstock.rockframework.web.tomcat.SimplePrincipal;

public class Struts2FormAthenticator extends AbstractFormAuthenticator {

	@Override
	protected Principal validate(String username, String password) {
		if ("junior".equals(username) && ("junior".equals(password))) {
			return new SimplePrincipal("junior", "junior", "admin");
		}
		return null;
	}
}
