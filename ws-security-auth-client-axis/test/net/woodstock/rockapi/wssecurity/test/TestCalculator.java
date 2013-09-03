package net.woodstock.rockapi.wssecurity.test;

import javax.xml.rpc.Stub;

import net.woodstock.rockapi.wssecurity.Calculator;
import net.woodstock.rockapi.wssecurity.CalculatorServiceLocator;

public class TestCalculator {

	public static void test1() throws Exception {
		Calculator calculator = new CalculatorServiceLocator().getCalculatorPort();

		Stub stub = (Stub) calculator;
		stub._setProperty(Stub.USERNAME_PROPERTY, "lourival");
		stub._setProperty(Stub.PASSWORD_PROPERTY, "password");

		System.out.println(calculator.sum(new Integer(1), new Integer(2)));
	}

	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
