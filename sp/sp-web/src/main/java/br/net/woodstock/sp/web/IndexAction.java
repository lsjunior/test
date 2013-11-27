package br.net.woodstock.sp.web;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.net.woodstock.sp.util.Constantes;

@Name("indexBean")
@Scope(ScopeType.APPLICATION)
public class IndexAction extends SPAction {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	public IndexAction() {
		super();
	}

}
