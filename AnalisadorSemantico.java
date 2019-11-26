package compiladores;

public class AnalisadorSemantico {
	
	public ElementoTabelaSimbolo Regra_1(RegistroLexico registro, ElementoTabelaSimbolo elemento, Tipo tipo, int linha) {
		if(elemento.classe != Classe.VAZIO) {
			System.out.println(linha+":identificador ja declarado ["+elemento.lexema+"]");
			System.exit(0);
		}else {
			elemento.classe = Classe.VAR;
			elemento.tipo = tipo;
		}
		return elemento;
	}
	
	public ElementoTabelaSimbolo Regra_2(RegistroLexico registro, ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.classe == Classe.VAZIO) {
			System.out.println(linha+":identificador nao declarado["+elemento.lexema+"]");
			System.exit(0);
		}else if(elemento.tipo != registro.tipo) {
			System.out.println(linha+":tipos incompatíveis");
			System.exit(0);
		}
		return elemento;
	}
	
	public ElementoTabelaSimbolo Regra_3(RegistroLexico registro, ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.classe != Classe.VAZIO) {
			System.out.println(linha+":identificador ja declarado ["+elemento.lexema+"]");
			System.exit(0);
		}else {
			elemento.classe = Classe.CONST;
		}
		return elemento;
	}
	
	public ElementoTabelaSimbolo Regra_4(RegistroLexico registro, ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.classe == Classe.VAZIO) {
			System.out.println(linha+":identificador nao declarado["+elemento.lexema+"]");
			System.exit(0);
		}
		return elemento;
	}
	
	public ElementoTabelaSimbolo Regra_5(ElementoTabelaSimbolo elemento, Tipo tipo, int linha) {
		if(elemento.tipo == Tipo.VAZIO)
			elemento.tipo = tipo;
		else if(elemento.tipo != tipo) {
			System.out.println(linha+":tipos incompatíveis.");
			System.exit(0);
		}
		return elemento;
	}
	
	public void Regra_15(ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.tipo != Tipo.LOGICO) {
			System.out.println(linha+":tipos incompatíveis.");
			System.exit(0);
		}
	}
}
