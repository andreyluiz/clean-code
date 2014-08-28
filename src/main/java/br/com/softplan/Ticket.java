package br.com.softplan;

import java.util.Date;

public class Ticket {
	
	Carro carro;
	Date horarioEntrada;
	
	public Ticket(Carro carro, Date horarioEntrada) {
		this.carro = carro;
		this.horarioEntrada = horarioEntrada;
	}
	
}
