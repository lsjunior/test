package net.woodstock.rockframework.test.wicket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.woodstock.rockframework.domain.Entity;
import net.woodstock.rockframework.domain.persistence.GenericRepository;

public class PeopleRepository implements GenericRepository {

	private static PeopleRepository	instance	= new PeopleRepository();

	private List<People>			peoples;

	private PeopleRepository() {
		super();
		this.peoples = new ArrayList<People>();
	}

	@Override
	public void delete(Entity<?> e) {
		People p = (People) e;
		this.peoples.remove(p);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Entity<?>> E get(E e) {
		int index = this.peoples.indexOf(e);
		if (index >= 0) {
			return (E) this.peoples.get(index);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Entity<?>> Collection<E> listAll(E e, Map<String, Object> params) {
		return (Collection<E>) this.peoples;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Entity<?>> Collection<E> listByExample(E e, Map<String, Object> params) {
		return (Collection<E>) this.peoples;
	}

	@Override
	public void save(Entity<?> e) {
		People p = (People) e;
		this.peoples.add(p);
	}

	@Override
	public void update(Entity<?> e) {
		this.delete(e);
		this.save(e);
	}

	public static PeopleRepository getInstance() {
		return instance;
	}
}
