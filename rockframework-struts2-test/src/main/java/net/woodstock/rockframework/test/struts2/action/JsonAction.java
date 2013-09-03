package net.woodstock.rockframework.test.struts2.action;

import java.util.ArrayList;
import java.util.Collection;

import net.woodstock.rockframework.test.struts2.entity.Bar;
import net.woodstock.rockframework.test.struts2.entity.Baz;
import net.woodstock.rockframework.test.struts2.entity.Foo;
import net.woodstock.rockframework.util.DateBuilder;
import net.woodstock.rockframework.web.struts2.Action;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

@ParentPackage(value = "default")
@org.apache.struts2.convention.annotation.Action(value = "json", results = { @Result(name = "success", location = "/xyz") })
@net.woodstock.rockframework.web.struts2.spring.Action
@Scope(value = "prototype")
public class JsonAction extends Action {

	private static final long	serialVersionUID	= 1L;

	private Collection<Foo>		foos;

	public JsonAction() {
		super();
		this.foos = new ArrayList<Foo>();
		for (int f = 0; f < 100; f++) {
			Foo foo = new Foo();
			foo.setId(new Integer(f));
			foo.setName("Foo " + f);

			DateBuilder dateBuilder = new DateBuilder();
			dateBuilder.addDays(f);
			foo.setDate(dateBuilder.getDate());

			foo.setBars(new ArrayList<Bar>());
			for (int b = 0; b < 100; b++) {
				Bar bar = new Bar();
				bar.setId(new Integer(b));
				bar.setValue("Bar " + b);
				bar.setFoo(foo);
				foo.getBars().add(bar);

				Baz baz = new Baz();
				baz.setId(new Integer(b));
				baz.setValue("Baz " + b);
				baz.setBar(bar);

				bar.setBaz(baz);
			}
			this.foos.add(foo);
		}
	}

	public Collection<Foo> getFoos() {
		return this.foos;
	}

	public void setFoos(Collection<Foo> foos) {
		this.foos = foos;
	}

}
