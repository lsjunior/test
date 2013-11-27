package br.net.woodstock.sp.util.test;

import org.junit.Test;

import br.net.woodstock.sp.util.Senhas;

public class SenhaTest {

	public SenhaTest() {
		super();
	}

	@Test
	public void test1() throws Exception {
		String senha = "admin";
		String senhaCodificada = Senhas.getPasswordEncoder().encode(senha);
		System.out.println(senhaCodificada);
	}

}
