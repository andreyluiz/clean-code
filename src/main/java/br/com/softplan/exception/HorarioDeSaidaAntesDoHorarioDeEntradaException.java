package br.com.softplan.exception;

public class HorarioDeSaidaAntesDoHorarioDeEntradaException extends EstacionamentoException {

	private static final long serialVersionUID = -6753808002801872780L;

	public HorarioDeSaidaAntesDoHorarioDeEntradaException() {
		super("Hor�rio de sa�da � menor que o hor�rio de entrada.");
	}

}
