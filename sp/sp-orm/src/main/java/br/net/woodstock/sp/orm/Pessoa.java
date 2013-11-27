package br.net.woodstock.sp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.net.woodstock.sp.util.Constantes;

@Entity
@Table(name = "tb_pessoa")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id_usuario")
public class Pessoa extends Usuario {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	@Column(name = "nr_cpf", length = 45, nullable = false, unique = true)
	private String				cpf;

	public Pessoa() {
		super();
	}

	public Pessoa(final Integer id) {
		super(id);
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

}
