package net.woodstock.rockapi.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import net.woodstock.rockapi.entity.People;

@Remote
public interface PeopleManager {

	void delete(People people);

	People get(Integer id);

	Collection<People> list();

	void save(People people);

	void update(People people);

}
