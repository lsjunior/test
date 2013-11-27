package br.net.woodstock.sp.web.cadastro.je;

import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.web.cadastro.PessoaForm;

public class PessoaFormJE extends PessoaForm {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	private String				tituloEleitor;

	public PessoaFormJE() {
		super();
	}

	@Override
	public void limpar() {
		super.limpar();
		this.setTituloEleitor(null);
	}

	public String getTituloEleitor() {
		return this.tituloEleitor;
	}

	public void setTituloEleitor(final String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

}
