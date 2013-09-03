package net.woodstock.rockapi.wssecurity;

import javax.xml.ws.BindingProvider;

public class TestCalculator {

	public static void test1() throws Exception {
		Calculator calculator = new CalculatorService().getCalculatorPort();

		BindingProvider provider = (BindingProvider) calculator;
		provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "lourival");
		provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "password");

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
