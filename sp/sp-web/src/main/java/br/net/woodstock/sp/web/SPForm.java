package br.net.woodstock.sp.web;

import java.io.Serializable;

import br.net.woodstock.sp.util.Constantes;

public abstract class SPForm implements Serializable {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	public SPForm() {
		super();
	}

	public void limpar() {
		//
	}
}
