//package compiladores;

public enum TipoConstante {
	
	INTEIRO(1),
	STRING(2),
	HEXA(3);
	
	public final Integer indice;
	
	private TipoConstante(Integer indice) {
		this.indice = indice;
	}

}
