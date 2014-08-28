package br.com.softplan.exception;

public class EstacionamentoFechadoException extends EstacionamentoException {

	private static final long serialVersionUID = -39695840944672953L;

	public EstacionamentoFechadoException() {
		super("O estacionamento encontra-se fechado.");
	}

}
