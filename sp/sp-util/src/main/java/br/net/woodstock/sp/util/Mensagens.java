package br.net.woodstock.sp.util;

import java.util.ResourceBundle;

import br.net.woodstock.rockframework.core.utils.Conditions;

public abstract class Mensagens {

	public static final String			MSG_OPERACAO_OK				= "msg.operacao.ok";

	public static final String			MSG_ERRO_CPF_EXISTENTE		= "msg.erro.cpf.existente";

	public static final String			MSG_ERRO_FILTRO_VAZIO		= "msg.erro.filtro.vazio";

	public static final String			MSG_ERRO_LOGIN_EXISTENTE	= "msg.erro.login.existente";

	private static final String			RESOURCE					= "sp-messages";

	private static final ResourceBundle	RESOURCE_BUNDLE				= ResourceBundle.getBundle(Mensagens.RESOURCE);

	private Mensagens() {
		//
	}

	public static String getMensagem(final String key) {
		String str = Mensagens.RESOURCE_BUNDLE.getString(key);
		if (Conditions.isEmpty(str)) {
			return key;
		}
		return str;
	}

}
