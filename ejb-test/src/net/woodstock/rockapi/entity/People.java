package net.woodstock.rockapi.entity;

import net.woodstock.rockframework.domain.Entity;

public class People implements Entity<Integer> {

	private static final long	serialVersionUID	= -8519467447112623801L;

	private Integer				id;

	private String				name;

	public People() {
		super();
	}

	public People(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
