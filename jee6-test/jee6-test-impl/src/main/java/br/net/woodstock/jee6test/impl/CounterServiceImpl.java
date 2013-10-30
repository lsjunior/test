package br.net.woodstock.jee6test.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import br.net.woodstock.jee6test.api.CounterService;

@Local(CounterService.class)
@Singleton(name = "counterService")
public class CounterServiceImpl implements CounterService, Serializable {

	private static final long	serialVersionUID	= 1;

	private int					value;

	public CounterServiceImpl() {
		super();
	}

	@Override
	@Lock(LockType.READ)
	public Integer getValue() {
		this.value++;
		return Integer.valueOf(this.value);
	}

}
