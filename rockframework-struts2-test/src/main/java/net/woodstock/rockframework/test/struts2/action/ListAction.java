package net.woodstock.rockframework.test.struts2.action;

import java.util.ArrayList;
import java.util.List;

import net.woodstock.rockframework.test.struts2.entity.ListItem;
import net.woodstock.rockframework.web.struts2.Action;

import org.springframework.context.annotation.Scope;

@net.woodstock.rockframework.web.struts2.spring.Action
@Scope(value = "prototype")
public class ListAction extends Action {

	private static final long	serialVersionUID	= 1L;

	private List<ListItem>		items;

	public ListAction() {
		super();
		this.items = new ArrayList<ListItem>();
	}

	@Override
	public String execute() throws Exception {
		for (int i = 0; i < 10; i++) {
			ListItem item = new ListItem();
			item.setId(new Integer(i));
			item.setName("List Item " + i);
			item.setStatus(new Boolean(i % 2 == 0));
			this.items.add(item);
		}
		return net.woodstock.rockframework.web.struts2.Constants.SUCCESS;
	}

	public String update() throws Exception {
		for (ListItem item : this.items) {
			System.out.println("ID: " + item.getId() + " Name: " + item.getName() + " Status: " + item.getStatus());
		}

		return net.woodstock.rockframework.web.struts2.Constants.SUCCESS;
	}

	public List<ListItem> getItems() {
		return this.items;
	}

	public void setItems(List<ListItem> items) {
		this.items = items;
	}

	// Test
	public String check(ListItem item) {
		return "Item [" + item.getName() + "]";
	}

}
