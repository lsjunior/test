package br.net.woodstock.sp.web.acesso;

import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.web.SPFiltro;

public class UsuarioFiltro extends SPFiltro {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	private String				nome;

	public UsuarioFiltro() {
		super();
	}

	@Override
	public void limpar() {
		super.limpar();
		this.setNome(null);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

}
