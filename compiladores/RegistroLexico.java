//package compiladores;

public class RegistroLexico {
	
	Token token;
	String lexema;
	Integer enderecoNaTabelaSimbolos;
	TipoConstante tipoConstante;
	
	public RegistroLexico(Token token, String lexema, Integer enderecoNaTabelaSimbolos, TipoConstante tipoConstante) {
		
		this.token = token;
		this.lexema = lexema;
		this.enderecoNaTabelaSimbolos = enderecoNaTabelaSimbolos;
		this.tipoConstante = tipoConstante;
	}
		
	
}
