package net.woodstock.rockapi.ejb.impl;

import javax.ejb.Stateless;

import net.woodstock.rockapi.ejb.Calculator;

@Stateless(name = "Calculator")
// @LocalBinding(jndiBinding = "Calculator")
// @RemoteBinding(jndiBinding = "Calculator")
// @SecurityDomain("EJB")
public class CalculatorBean implements Calculator {

	@Override
	// @RolesAllowed("admin")
	public Integer sum(Integer i1, Integer i2) {
		return new Integer(i1.intValue() + i2.intValue());
	}

}
