package br.com.softplan.exception;


public class CarroNaoEstacionadoException extends EstacionamentoException {

	private static final long serialVersionUID = 2599620667774960833L;

	public CarroNaoEstacionadoException() {
		super("Carro a ser retirado não está estacionado.");
	}

}
