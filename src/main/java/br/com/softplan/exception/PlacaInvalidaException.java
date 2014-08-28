package br.com.softplan.exception;

public class PlacaInvalidaException extends EstacionamentoException {

	private static final long serialVersionUID = -2860643576285636144L;

	public PlacaInvalidaException() {
		super("Placa do carro inválida.");
	}

}
