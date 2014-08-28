package br.com.softplan.exception;

public class EstacionamentoLotadoException extends EstacionamentoException {

	private static final long serialVersionUID = -5867261905700366861L;

	public EstacionamentoLotadoException() {
		super("Estacionamento lotado.");
	}

}
