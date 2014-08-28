package br.com.softplan;

public class CalculadoraDePrecosShopping extends CalculadoraDePrecosComHoraAdicional {

	@Override
	protected int getHoraInicial() {
		return 3;
	}

	@Override
	protected int getValorInicial() {
		return 6;
	}

	@Override
	protected int getValorAdicional() {
		return 2;
	}



}
