package net.woodstock.rockapi.ejb.test;

import java.util.Collection;

import net.woodstock.rockapi.ejb.PeopleManager;
import net.woodstock.rockapi.entity.People;

public class TestPeople extends TestEJB {

	public void test1() throws Exception {
		PeopleManager peopleManager = (PeopleManager) this.lookup("PeopleManager/remote");

		for (int i = 0; i < 10; i++) {
			peopleManager.save(new People(new Integer(i), "Test" + i));
		}

		Collection<People> peoples = peopleManager.list();
		for (People people : peoples) {
			System.out.println(people);
		}
	}

}
