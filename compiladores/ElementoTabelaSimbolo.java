//package compiladores;

public class ElementoTabelaSimbolo {
	Token token;
	String lexema;	
	
	public ElementoTabelaSimbolo(Token token, String lexema) {
		this.token = token;
		this.lexema = lexema;
	}	
	
	public String getLexema() {
		return lexema;
	}
}
