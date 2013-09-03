package net.woodstock.rockframework.test.struts2.entity;

import net.woodstock.rockframework.domain.Entity;

public class ListItem implements Entity<Integer> {

	private static final long	serialVersionUID	= -6111184164689839730L;

	private Integer				id;

	private String				name;

	private Boolean				status;

	public ListItem() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
