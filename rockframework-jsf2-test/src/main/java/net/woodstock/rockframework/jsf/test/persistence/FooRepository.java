package net.woodstock.rockframework.jsf.test.persistence;

import java.util.ArrayList;
import java.util.List;

import net.woodstock.rockframework.jsf.test.entity.Foo;

public class FooRepository {

	private static List<Foo>	foos;

	public FooRepository() {
		super();
		if (FooRepository.foos == null) {
			FooRepository.foos = new ArrayList<Foo>();
			for (int i = 1; i <= 5; i++) {
				Foo foo = new Foo();
				foo.setId(new Integer(i));
				foo.setName("Test " + i);
				FooRepository.foos.add(foo);
			}
		}
	}

	public void delete(Foo foo) {
		int index = -1;
		int count = 0;
		for (Foo f : FooRepository.foos) {
			if (f.getId().equals(foo.getId())) {
				index = count;
				break;
			}
			count++;
		}
		if (index != -1) {
			FooRepository.foos.remove(index);
		}
	}

	public Foo get(Foo foo) {
		for (Foo f : FooRepository.foos) {
			if (f.getId().equals(foo.getId())) {
				return f;
			}
		}
		return null;
	}

	public List<Foo> list() {
		return FooRepository.foos;
	}

	public void save(Foo foo) {
		FooRepository.foos.add(foo);
	}

	public void update(Foo foo) {
		this.delete(foo);
		this.save(foo);
	}

}
