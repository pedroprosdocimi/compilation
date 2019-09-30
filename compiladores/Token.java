package compiladores;

public enum Token {
	
	ERRO(0),
	ID(1),
	CONSTANTE(2),
	CONST(3),
	INTEGER(4),
	BYTE(5),
	STRING(6),
	WHILE(7),
	IF(8),
	ELSE(9),
	AND(10),
	OR(11),
	NOT(12),
	ATRIBUICAO(13),
	IGUAL(14),	
	ABRE_PARENTESES(15),
	FECHA_PARENTESES(16),
	MENOR_QUE(17),
	MAIOR_QUE(18),
	DIFERENTE_DE(19),
	MAIOR_OU_IGUAL_QUE(20),
	MENOR_OU_IGUAL_QUE(21),
	VIRGULA(22),
	MAIS(23),
	MENOS(24),
	MULTIPLICACAO(25),
	DIVISAO(26),
	PONTO_VIRGULA(27),
	BEGIN(28),
	END(29),
	THEN(30),
	READLN(31),
	MAIN(32),
	WRITE(33),
	WRITELN(34),
	TRUE(35),
	FALSE(36),
	BOOLEAN(37);
	
	public final Integer indice;
	
	private Token(Integer indice) {
		this.indice = indice;
	}
}
