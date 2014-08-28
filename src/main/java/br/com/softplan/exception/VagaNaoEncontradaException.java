package br.com.softplan.exception;

public class VagaNaoEncontradaException extends EstacionamentoException {

	private static final long serialVersionUID = -226649912939528712L;

	public VagaNaoEncontradaException() {
		super("Vaga não foi encontrada.");
	}
	
}
