package br.net.woodstock.sp.web.cadastro;

import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.web.acesso.UsuarioForm;

public class PessoaForm extends UsuarioForm {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	private String				cpf;

	public PessoaForm() {
		super();
	}

	@Override
	public void limpar() {
		super.limpar();
		this.setCpf(null);
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

}
