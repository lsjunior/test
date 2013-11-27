package br.net.woodstock.sp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;
import br.net.woodstock.sp.util.Constantes;

@Entity
@Table(name = "tb_processo")
public class Processo extends AbstractIntegerEntity {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_processo", unique = true)
	private Integer				id;

	@Column(name = "nr_processo", length = 20, nullable = false, unique = true)
	private String				numero;

	@Column(name = "nm_assunto", length = 100, nullable = false)
	private String				nome;

	public Processo() {
		super();
	}

	public Processo(final Integer id) {
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

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(final String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

}
