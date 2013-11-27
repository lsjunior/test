package br.net.woodstock.sp.util;

import br.net.woodstock.rockframework.security.digest.PasswordEncoder;
import br.net.woodstock.rockframework.security.digest.impl.SHA256PasswordEncoder;

public abstract class Senhas {

	private static final PasswordEncoder	PASSWORD_ENCODER	= new SHA256PasswordEncoder();

	public static PasswordEncoder getPasswordEncoder() {
		return Senhas.PASSWORD_ENCODER;
	}

}
