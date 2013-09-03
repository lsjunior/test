package br.net.woodstock.test.seamtest.web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

@Name("numeradorProcesso")
@Scope(ScopeType.APPLICATION)
@Synchronized
public class NumeradorProcesso {

	private int						anoCorrente;

	private Map<Integer, Integer>	ultimosNumeros;

	@Create
	public void init() {
		this.carregarAnoCorrente();
		this.carregarMapa();
	}

	@SuppressWarnings("unchecked")
	private void carregarMapa() {
		this.ultimosNumeros = new HashMap<Integer, Integer>();

	}

	public Integer getProximoNumero(final Integer numeroOrigem) {
		Integer proximoNumero;
		if (this.anoCorrente != Calendar.getInstance().get(Calendar.YEAR)) {
			this.carregarAnoCorrente();
			this.carregarMapa();
		}

		if (this.ultimosNumeros.containsKey(numeroOrigem)) {
			proximoNumero = this.ultimosNumeros.get(numeroOrigem);
		} else {
			proximoNumero = this.getNumeroInicial();
		}
		this.ultimosNumeros.put(numeroOrigem, ++proximoNumero);
		return proximoNumero;
	}

	private Integer getNumeroInicial() {
		return 0;
	}

	private void carregarAnoCorrente() {
		this.anoCorrente = Calendar.getInstance().get(Calendar.YEAR);
	}

}