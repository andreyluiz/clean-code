package br.com.softplan;

import java.util.Date;

import br.com.softplan.exception.EstacionamentoException;
import br.com.softplan.exception.VagaNaoEncontradaException;

public class ControleDeTickets {

	private Ticket[] tickets;

	public ControleDeTickets(int totalDeVagas) {
		tickets = new Ticket[totalDeVagas];
	}
	
	public int getQuantidadeVagasDisponiveis() {
		return (this.tickets.length - getQuantidadeCarrosEstacionados());
	}

	public void criarTicket(Carro carro, Date horarioDeEntrada) throws VagaNaoEncontradaException {
		tickets[buscarVagaDisponivel()] = new Ticket(carro, horarioDeEntrada);
	}
	
	private int buscarVagaDisponivel() throws VagaNaoEncontradaException {
		for (int vaga = 0; vaga < tickets.length; vaga++) {
			if (tickets[vaga] == null) return vaga;
		}
		throw new VagaNaoEncontradaException();
	}

	public Ticket buscarTicket(Carro carro) throws EstacionamentoException {
		return tickets[buscarVagaCarroEstacionado(carro)];
	}
	
	public int buscarVagaCarroEstacionado(Carro carro) throws EstacionamentoException {
		for (int vaga = 0; vaga < tickets.length; vaga++) {
			if (tickets[vaga] != null && carro.equals(tickets[vaga].carro)) return vaga;
		}
		throw new VagaNaoEncontradaException();
	}

	public void removerTicket(Carro carro) throws EstacionamentoException {
		tickets[buscarVagaCarroEstacionado(carro)] = null;
	}
	
	private int getQuantidadeCarrosEstacionados() {
		int quantidade = 0;
		for (Ticket carroDaArray : tickets) {
			if (carroDaArray != null) quantidade++;
		}
		return quantidade;
	}
	
}
