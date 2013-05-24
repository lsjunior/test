package br.net.woodstock.test.seamtest.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "contact")
@SequenceGenerator(name = "sq_contact", sequenceName = "sq_contact")
public class Contact implements Serializable, Comparable<Contact> {

	private static final long	serialVersionUID	= 3790298394714028245L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_contact")
	private Integer				id;

	@Column(name = "name")
	@NotNull
	@Length(min = 3, max = 100)
	private String				name;

	@Column(name = "email", unique = true)
	@NotNull
	@Email
	@Length(min = 3, max = 100)
	private String				email;

	public Contact() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public int compareTo(final Contact c) {
		if (c == null) {
			return -1;
		}
		return this.getName().compareTo(c.getName());
	}
}
