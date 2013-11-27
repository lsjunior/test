package br.net.woodstock.sp.web;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("configBean")
@Scope(ScopeType.APPLICATION)
public class ConfigAction {

	public static final int	PAGE_SIZE	= 10;

	public ConfigAction() {
		super();
	}

	public int getPageSize() {
		return ConfigAction.PAGE_SIZE;
	}

}
