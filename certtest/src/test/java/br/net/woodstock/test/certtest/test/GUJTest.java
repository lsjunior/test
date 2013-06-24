package br.net.woodstock.test.certtest.test;

import org.junit.Test;

public class GUJTest {

	public GUJTest() {
		super();
	}

	@Test
	public void testCopy() throws Exception {
		int[] tempos = new int[] { 14, 16, 59, 70, 111, 124, 178, 198, 255, 301 };
		for (int tempo : tempos) {
			System.out.println(tempo + " min: " + this.getValor(tempo));
		}
	}

	private float getValor(final int tempo) {
		// Valores
		float valorHora = 1.5f;
		float valorDemaisHoras = 1;
		int tempoGratis = 15;
		// Validacoes		
		if (tempo <= tempoGratis) {
			return 0;
		}
		int horas = (tempo / 60);
		float valor = (horas * valorDemaisHoras) + valorHora;
		return valor;
	}

}
