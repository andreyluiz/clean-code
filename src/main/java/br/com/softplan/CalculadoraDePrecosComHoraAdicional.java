package br.com.softplan;

public abstract class CalculadoraDePrecosComHoraAdicional extends CalculadoraDePrecos {

	@Override
	public int calcularValor(int horasEstacionado) {
		if (horasEstacionado <= this.getHoraInicial()) return this.getValorInicial();
		int horasAMais = horasEstacionado - this.getHoraInicial();
		return this.getValorInicial() + (horasAMais * this.getValorAdicional());
	}

	protected abstract int getHoraInicial();
	protected abstract int getValorInicial();
	protected abstract int getValorAdicional();

}
