package br.net.woodstock.jsftest.cadastro;

import java.io.Serializable;

import br.net.woodstock.rockframework.core.util.EqualsBuilder;
import br.net.woodstock.rockframework.core.util.HashCodeBuilder;
import br.net.woodstock.rockframework.core.util.Identifiable;
import br.net.woodstock.rockframework.core.util.ToStringBuilder;

public class Usuario implements Identifiable<Integer>, Serializable, Comparable<Usuario> {

	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	private String				nome;

	private String				email;

	public Usuario() {
		super();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public int compareTo(Usuario o) {
		return this.getNome().compareToIgnoreCase(o.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return new EqualsBuilder().add(this.getId(), other.getId()).add(this.getNome(), other.getNome()).add(this.getEmail(), other.getEmail()).build().booleanValue();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().add(this.getId()).add(this.getNome()).add(this.getEmail()).build().intValue();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(Usuario.class).add("id", this.getId()).add("nome", this.getNome()).add("email", this.getEmail()).build();
	}

}
