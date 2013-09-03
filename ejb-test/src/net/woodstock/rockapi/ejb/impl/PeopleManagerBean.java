package net.woodstock.rockapi.ejb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Init;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import net.woodstock.rockapi.ejb.PeopleManager;
import net.woodstock.rockapi.entity.People;

@Stateful(name = "PeopleManager")
// @LocalBinding(jndiBinding = "PeopleManager")
// @RemoteBinding(jndiBinding = "PeopleManager")
// @SecurityDomain("EJB")
public class PeopleManagerBean implements PeopleManager {

	private List<People>	peoples;

	@Override
	public void delete(People people) {
		Iterator<People> iterator = this.peoples.iterator();
		while (iterator.hasNext()) {
			People p = iterator.next();
			if (p.getId().equals(people.getId())) {
				iterator.remove();
				break;
			}
		}
	}

	@Override
	public People get(Integer id) {
		Iterator<People> iterator = this.peoples.iterator();
		while (iterator.hasNext()) {
			People p = iterator.next();
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Collection<People> list() {
		return this.peoples;
	}

	@Override
	public void save(People people) {
		this.peoples.add(people);
	}

	@Override
	public void update(People people) {
		Iterator<People> iterator = this.peoples.iterator();
		while (iterator.hasNext()) {
			People p = iterator.next();
			if (p.getId().equals(people.getId())) {
				p.setName(people.getName());
				break;
			}
		}
	}

	@Init
	public void init() {
		System.out.println("Inicializando a lista de pessoas");
		this.peoples = new ArrayList<People>();
	}

	@Remove
	public void remove() {
		System.out.println("Removendo as pessoas");
		this.peoples.clear();
	}

}
