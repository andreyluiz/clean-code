package br.com.softplan;

public class CalculadoraDePrecosAeroporto extends CalculadoraDePrecosComHoraAdicional {

	@Override
	protected int getHoraInicial() {
		return 5;
	}

	@Override
	protected int getValorInicial() {
		return 5;
	}

	@Override
	protected int getValorAdicional() {
		return 3;
	}

}