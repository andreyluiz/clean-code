package br.com.softplan;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.softplan.exception.CarroJaEstacionadoException;
import br.com.softplan.exception.CarroNaoEstacionadoException;
import br.com.softplan.exception.EstacionamentoException;
import br.com.softplan.exception.EstacionamentoFechadoException;
import br.com.softplan.exception.EstacionamentoLotadoException;
import br.com.softplan.exception.HorarioDeSaidaAntesDoHorarioDeEntradaException;
import br.com.softplan.exception.PlacaInvalidaException;

public class EstacionamentoTest {

	Estacionamento estacionamento;
	ControleDeTickets controleDeTickets;
	private static final int QUANTIDADE_TOTAL_DE_VAGAS = 500;
	private static final int HORARIO_ABERTURA = 8;
	private static final int HORARIO_FECHAMENTO = 22;
	
	@Before
	public void antesDoTeste() throws EstacionamentoException {
		inicializarEstacionamento(QUANTIDADE_TOTAL_DE_VAGAS, Local.PRAIA);
	}
	
	public void inicializarEstacionamento(int quantidadeDeVagas, Local localEstacionamento) throws EstacionamentoException {
		controleDeTickets = new ControleDeTickets(quantidadeDeVagas);
		estacionamento = new Estacionamento(as(HORARIO_ABERTURA), as(HORARIO_FECHAMENTO), controleDeTickets, localEstacionamento);
	}
	
	@Test
	public void deveTer500VagasDisponiveis() {
		assertEquals(500, controleDeTickets.getQuantidadeVagasDisponiveis());
	}
	
	@Test
	public void deveEntrarUmCarro() throws EstacionamentoException {
		estacionamento.estacionarCarro(vectra(), as(9));
		assertEquals(499, controleDeTickets.getQuantidadeVagasDisponiveis());
	}
	
	@Test
	public void deveSairUmCarro() throws EstacionamentoException {
		estacionamento.estacionarCarro(corsa(), as(9));
		estacionamento.expulsarCarro(corsa(), as(15));
		assertEquals(500, controleDeTickets.getQuantidadeVagasDisponiveis());
	}
	
	@Test(expected = CarroJaEstacionadoException.class)
	public void naoDeveEntrarCarroDuplicado() throws EstacionamentoException {
		estacionamento.estacionarCarro(vectra(), as(9));
		estacionamento.estacionarCarro(vectra(), as(9));
	}
	
	@Test(expected = EstacionamentoLotadoException.class)
	public void estacionamentoLotado() throws EstacionamentoException {
		inicializarEstacionamento(1, Local.PRAIA);
		estacionamento.estacionarCarro(vectra(), as(9));
		estacionamento.estacionarCarro(corsa(), as(9));
	}
	
	@Test(expected = CarroNaoEstacionadoException.class)
	public void naoPodeSairCarroQueNaoExiste() throws EstacionamentoException {
		estacionamento.estacionarCarro(vectra(), as(9));
		estacionamento.expulsarCarro(corsa(), as(15));
	}
	
	@Test(expected = PlacaInvalidaException.class)
	public void naoPodeEntrarCarroComPlacaInvalida() throws EstacionamentoException {
		estacionamento.estacionarCarro(carroInvalido(), as(9));
	}
	
	@Test(expected = EstacionamentoFechadoException.class)
	public void tentouEntrarUmCarroForaDoHorario() throws EstacionamentoException {
		estacionamento.estacionarCarro(vectra(), as(5));
	}
	
	@Test(expected = EstacionamentoFechadoException.class)
	public void tentouSairUmCarroForaDoHorario() throws EstacionamentoException {
		estacionamento.expulsarCarro(vectra(), as(23));
	}
	
	@Test(expected = HorarioDeSaidaAntesDoHorarioDeEntradaException.class)
	public void umCarroNaoPodeSairAntesDeTerEntrado() throws EstacionamentoException {
		estacionamento.estacionarCarro(vectra(), as(12));
		estacionamento.expulsarCarro(vectra(), as(11));
	}
	
	@Test
	public void umCarroPermaneceuDuranteTresHorasEstacionado() throws EstacionamentoException {
		estacionamento.estacionarCarro(vectra(), as(12));
		Comprovante comprovante = estacionamento.expulsarCarro(vectra(), as(15)); 
		assertEquals(3, comprovante.totalHorasEstacionado);
	}
	
	@Test
	public void umCarroParouNaPraiaEDevePagarCincoReais() throws EstacionamentoException {
		inicializarEstacionamento(QUANTIDADE_TOTAL_DE_VAGAS, Local.PRAIA);
		estacionamento.estacionarCarro(vectra(), as(9));
		Comprovante comprovante = estacionamento.expulsarCarro(vectra(), as(12));
		
		assertEquals(5, comprovante.valorTotalEstacionamento);
	}
	
	@Test
	public void umCarroParouNoCentroPorQuatroHorasEDevePagarQuarentaReais() throws EstacionamentoException {
		inicializarEstacionamento(QUANTIDADE_TOTAL_DE_VAGAS, Local.CENTRO);
		estacionamento.estacionarCarro(vectra(), as(9));
		Comprovante comprovante = estacionamento.expulsarCarro(vectra(), as(13));
		
		assertEquals(40, comprovante.valorTotalEstacionamento);
	}
	
	@Test
	public void umCarroParouNoShoppingPorDuasHorasEDevePagarSeisReais() throws EstacionamentoException {
		inicializarEstacionamento(QUANTIDADE_TOTAL_DE_VAGAS, Local.SHOPPING);
		estacionamento.estacionarCarro(vectra(), as(9));
		Comprovante comprovante = estacionamento.expulsarCarro(vectra(), as(11));
		
		assertEquals(6, comprovante.valorTotalEstacionamento);
	}
	
	@Test
	public void umCarroParouNoAeroportoPorTresHorasEDevePagarCincoReais() throws EstacionamentoException {
		inicializarEstacionamento(QUANTIDADE_TOTAL_DE_VAGAS, Local.AEROPORTO);
		estacionamento.estacionarCarro(vectra(), as(9));
		Comprovante comprovante = estacionamento.expulsarCarro(vectra(), as(12));
		
		assertEquals(5, comprovante.valorTotalEstacionamento);
	}
	
	private Carro vectra() throws PlacaInvalidaException {
		return new Carro("AAA-1111");
	}

	private Carro corsa() throws PlacaInvalidaException {
		return new Carro("BBB-2222");
	}
	
	private Carro carroInvalido() throws PlacaInvalidaException {
		return new Carro("Newman");
	}
	
	private Date as(int hora) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.HOUR_OF_DAY, hora);
		return calendario.getTime();
	}
	
}
