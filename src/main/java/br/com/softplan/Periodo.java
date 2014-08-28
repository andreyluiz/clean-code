package br.com.softplan;

import java.util.Date;

import br.com.softplan.exception.HorarioDeSaidaAntesDoHorarioDeEntradaException;

public class Periodo {

	private Date horarioInicial;
	private Date horarioFinal;
	private int HORA = 3600*1000;
	
	public Periodo(Date horarioInicial, Date horarioFinal) throws HorarioDeSaidaAntesDoHorarioDeEntradaException {
		if (horarioFinal.before(horarioInicial)) throw new HorarioDeSaidaAntesDoHorarioDeEntradaException();
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
	}

	public boolean isForaHorarioExpediente(Date horario) {
		return (horario.before(this.horarioInicial) || horario.after(this.horarioFinal));
	}

	public int getTotalHorasEstacionado() {
		return (int) ((this.horarioFinal.getTime() - this.horarioInicial.getTime()) / HORA);
	}
	
}
