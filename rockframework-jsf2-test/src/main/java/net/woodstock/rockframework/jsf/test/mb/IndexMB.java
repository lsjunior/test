package net.woodstock.rockframework.jsf.test.mb;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "indexMB")
@ApplicationScoped
public class IndexMB {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Message On IndexMB";
	}

}
