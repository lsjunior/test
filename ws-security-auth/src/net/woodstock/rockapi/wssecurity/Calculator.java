package net.woodstock.rockapi.wssecurity;

import javax.jws.WebService;

@WebService(name = "Calculator", portName = "CalculatorPort", serviceName = "CalculatorService", targetNamespace = "http://rockapi.woodstock.net/wssecurity")
public class Calculator {

	public Integer sum(Integer a, Integer b) {
		int i = a.intValue() + b.intValue();
		return new Integer(i);
	}

}
