package br.net.woodstock.test.seamtest.web;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("sessionCountBean")
@Scope(ScopeType.SESSION)
public class SessionCountBean implements Serializable {

	private static final long	serialVersionUID	= -6953015332597497929L;

	private int					count;

	public SessionCountBean() {
		super();
		this.count = 0;
	}

	public String add() {
		this.count++;
		return null;
	}

	public int getCount() {
		return this.count;
	}

}
