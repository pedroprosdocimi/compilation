package compiladores;

public class ElementoTabelaSimbolo {
	Token token;
	String lexema;	
	Classe classe;
	Tipo tipo;
	
	public ElementoTabelaSimbolo(Token token, String lexema, Classe classe, Tipo tipo) {
		this.token = token;
		this.lexema = lexema;
		this.classe = classe;
		this.tipo = tipo;
	}	
	
	public String getLexema() {
		return lexema;
	}
}
