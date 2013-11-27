package br.net.woodstock.sp.web.acesso;

import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.web.SPForm;

public class UsuarioForm extends SPForm {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	private Integer				id;

	private String				login;

	private String				nome;

	private String				senha;

	private Boolean				ativo;

	public UsuarioForm() {
		super();
	}

	@Override
	public void limpar() {
		super.limpar();
		this.setAtivo(null);
		this.setId(null);
		this.setLogin(null);
		this.setNome(null);
		this.setSenha(null);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(final Boolean ativo) {
		this.ativo = ativo;
	}

}
