package br.com.softplan;

import java.util.Date;

import br.com.softplan.exception.CarroJaEstacionadoException;
import br.com.softplan.exception.CarroNaoEstacionadoException;
import br.com.softplan.exception.EstacionamentoException;
import br.com.softplan.exception.EstacionamentoFechadoException;
import br.com.softplan.exception.EstacionamentoLotadoException;
import br.com.softplan.exception.VagaNaoEncontradaException;


public class Estacionamento {

	private ControleDeTickets controleDeTickets;
	private Periodo periodoFuncionamento;
	private Local localEstacionamento;
	
	public Estacionamento(Date horarioAbertura, Date horarioFechamento, ControleDeTickets controleDeTickets, Local localEstacionamento) throws EstacionamentoException {
		this.controleDeTickets = controleDeTickets;
		periodoFuncionamento = new Periodo(horarioAbertura, horarioFechamento);
		this.localEstacionamento = localEstacionamento;
	}

	public void estacionarCarro(Carro carro, Date horarioDeEntrada) throws EstacionamentoException {
		if (periodoFuncionamento.isForaHorarioExpediente(horarioDeEntrada)) throw new EstacionamentoFechadoException();
		if (this.controleDeTickets.getQuantidadeVagasDisponiveis() == 0) throw new EstacionamentoLotadoException();
		if (isCarroEstacionado(carro)) throw new CarroJaEstacionadoException();
		
		this.controleDeTickets.criarTicket(carro, horarioDeEntrada);
	}
	
	public Comprovante expulsarCarro(Carro carro, Date horarioSaida) throws EstacionamentoException {
		if (periodoFuncionamento.isForaHorarioExpediente(horarioSaida)) throw new EstacionamentoFechadoException();
		if (!isCarroEstacionado(carro)) throw new CarroNaoEstacionadoException();
		
		Ticket ticket = this.controleDeTickets.buscarTicket(carro);
		Periodo periodoEstacionado = new Periodo(ticket.horarioEntrada, horarioSaida);
		
		int horasEstacionado = periodoEstacionado.getTotalHorasEstacionado();
		
		this.controleDeTickets.removerTicket(carro);
		
		int valorTotal = localEstacionamento.getCalculadoraDePrecos().calcularValor(horasEstacionado);
		
		return new Comprovante(horasEstacionado, valorTotal);
	}
	
	private boolean isCarroEstacionado(Carro carro) throws EstacionamentoException {
		try {
			this.controleDeTickets.buscarVagaCarroEstacionado(carro);
			return true;
		} catch (VagaNaoEncontradaException e) {
			return false;
		}
	}
	
}
