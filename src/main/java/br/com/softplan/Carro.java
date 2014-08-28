package br.com.softplan;

import br.com.softplan.exception.PlacaInvalidaException;

public class Carro {

	private String placa;
	
	public Carro(String nomeCarro) throws PlacaInvalidaException {
		if (!nomeCarro.matches("[A-Z]{3}\\-[0-9]{4}")) throw new PlacaInvalidaException();
		this.placa = nomeCarro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

}