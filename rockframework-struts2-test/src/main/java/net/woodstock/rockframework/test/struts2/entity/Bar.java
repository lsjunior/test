package net.woodstock.rockframework.test.struts2.entity;

import net.woodstock.rockframework.domain.Entity;

public class Bar implements Entity<Integer> {

	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	private String				value;

	private Foo					foo;

	private Baz					baz;

	public Bar() {
		super();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Foo getFoo() {
		return this.foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public Baz getBaz() {
		return this.baz;
	}

	public void setBaz(Baz baz) {
		this.baz = baz;
	}

}
