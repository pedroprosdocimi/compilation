package compiladores;

public class RegistroLexico {
	
	Token token;
	String lexema;
	Tipo tipo;
	
	public RegistroLexico(Token token, String lexema, Tipo tipo) {
		
		this.token = token;
		this.lexema = lexema;
		this.tipo = tipo;
	}
		
	
}
