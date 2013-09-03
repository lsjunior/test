package br.net.woodstock.test.seamtest.web;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("contactBean")
@Scope(ScopeType.APPLICATION)
public class ApplicationTestBean implements Serializable {

	private int	count;

	
	
	public int getCount() {
		return this.count;
	}

}
