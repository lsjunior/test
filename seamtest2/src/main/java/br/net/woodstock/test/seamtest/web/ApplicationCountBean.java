package br.net.woodstock.test.seamtest.web;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;


@Name("applicationCountBean")
@Scope(ScopeType.APPLICATION)
public class ApplicationCountBean implements Serializable {

	private static final long	serialVersionUID	= 4843857325571833614L;

	private int					count;

	public ApplicationCountBean() {
		super();
		this.count = 0;
	}

	public String add() {
		this.count++;
		FacesContext fc = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
		sc.setAttribute("count", Integer.valueOf(this.count));
		return null;
	}

	public int getCount() {
		return this.count;
	}

}
