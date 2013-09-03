package net.woodstock.rockframework.jsf.test.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import net.woodstock.rockframework.jsf.test.entity.Foo;
import net.woodstock.rockframework.jsf.test.persistence.FooRepository;

@ManagedBean(name = "fooMB")
@RequestScoped
public class FooMB {

	private static final long serialVersionUID = 1L;

	private Foo foo;

	private FooRepository fooRepository;

	private List<Foo> foos;

	public FooMB() {
		super();
		this.foo = new Foo();
		this.fooRepository = new FooRepository();
	}

	public String delete() {
		System.out.println("Delete: " + this.foo.getId());
		this.fooRepository.delete(this.foo);
		this.foos = this.fooRepository.list();
		return "success";
	}

	public String edit() {
		System.out.println("Edit: " + this.foo.getId());
		this.foo = this.fooRepository.get(this.foo);
		return "success";
	}

	public String save() {
		if ((this.foo.getId() != null) && (this.foo.getId().intValue() > 0)) {
			System.out.println("Update: " + this.foo.getId());
			this.fooRepository.update(this.foo);
		} else {
			System.out.println("Save: " + this.foo.getName());
			this.fooRepository.save(this.foo);
			this.foo = new Foo();
		}
		return "success";
	}

	// Getters
	public Foo getFoo() {
		return this.foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public List<Foo> getFoos() {
		if (this.foos == null) {
			this.foos = this.fooRepository.list();
		}
		return this.foos;
	}

	public void setFoos(List<Foo> foos) {
		this.foos = foos;
	}

}
