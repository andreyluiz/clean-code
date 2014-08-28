package br.com.softplan;

public enum Local {
	
	PRAIA
	{
		@Override
		public CalculadoraDePrecos getCalculadoraDePrecos() {
			return new CalculadoraDePrecosPraia();
		}
	},
	CENTRO {
		@Override
		public CalculadoraDePrecos getCalculadoraDePrecos() {
			return new CalculadoraDePrecosCentro();
		}
	},
	SHOPPING {
		@Override
		public CalculadoraDePrecos getCalculadoraDePrecos() {
			return new CalculadoraDePrecosShopping();
		}
	},
	AEROPORTO {
		@Override
		public CalculadoraDePrecos getCalculadoraDePrecos() {
			return new CalculadoraDePrecosAeroporto();
		}
	};
	
	public abstract CalculadoraDePrecos getCalculadoraDePrecos();

}
