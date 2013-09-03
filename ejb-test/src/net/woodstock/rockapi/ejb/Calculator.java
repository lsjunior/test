package net.woodstock.rockapi.ejb;

import javax.ejb.Remote;

@Remote
public interface Calculator {

	Integer sum(Integer i1, Integer i2);

}
