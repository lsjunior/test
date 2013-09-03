package net.woodstock.rockframework.jsf.test.entity;

import java.util.Collection;
import java.util.Date;

import net.woodstock.rockframework.domain.Entity;

public class Foo implements Entity<Integer> {

	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	private String				name;

	private Date				date;

	private Collection<Bar>		bars;

	public Foo() {
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Collection<Bar> getBars() {
		return this.bars;
	}

	public void setBars(Collection<Bar> bars) {
		this.bars = bars;
	}

}
