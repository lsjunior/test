package br.net.woodstock.sp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;
import br.net.woodstock.sp.util.Constantes;

@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractIntegerEntity {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", unique = true)
	private Integer				id;

	@Column(name = "nm_login", length = 45, nullable = false, unique = true)
	private String				login;

	@Column(name = "nm_usuario", length = 100, nullable = false)
	private String				nome;

	@Column(name = "ds_senha", length = 255, nullable = false)
	private String				senha;

	@Column(name = "st_usuario", nullable = false)
	private Boolean				status;

	public Usuario() {
		super();
	}

	public Usuario(final Integer id) {
		super(id);
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
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

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(final Boolean status) {
		this.status = status;
	}

}
