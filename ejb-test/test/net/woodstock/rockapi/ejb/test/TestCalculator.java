package net.woodstock.rockapi.ejb.test;

import net.woodstock.rockapi.ejb.Calculator;

public class TestCalculator extends TestEJB {

	public void test1() throws Exception {
		Calculator calculator = (Calculator) this.lookup("Calculator/remote");
		System.out.println(calculator.sum(new Integer(1), new Integer(2)));
	}

}
