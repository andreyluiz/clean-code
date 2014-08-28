package br.com.softplan.exception;

public class CarroJaEstacionadoException extends EstacionamentoException {

	private static final long serialVersionUID = 6274379274623559570L;

	public CarroJaEstacionadoException() {
		super("Carro a ser estacionado já se encontra estacionado.");
	}

}
