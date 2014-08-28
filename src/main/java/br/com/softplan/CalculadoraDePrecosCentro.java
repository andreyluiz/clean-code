package br.com.softplan;

public class CalculadoraDePrecosCentro extends CalculadoraDePrecos {

	@Override
	public int calcularValor(int horasEstacionado) {
		return horasEstacionado * 10;
	}

}
