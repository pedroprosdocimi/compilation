package compiladores;

public class AnalisadorSintatico {
	
	Token token;
	AnalisadorLexico analisadorLexico;
	String codigo;
	AnalisadorSemantico analisadorSemantico;

	public AnalisadorSintatico(Token token, String codigo, AnalisadorLexico analisadorLexico, AnalisadorSemantico analisadorSemantico) {
		this.token = token;
		this.codigo = codigo;
		this.analisadorLexico = analisadorLexico;
		this.analisadorSemantico = analisadorSemantico;
	}
	
	public void CasaToken(Token token_esperado) {
		System.out.println("Lexema: "+analisadorLexico.lexema+"  Token: "+token);
		if(token == token_esperado) {
			codigo = analisadorLexico.Analisar(codigo);
			token = analisadorLexico.registroLexico.token;
			
			
		}else {
			System.out.println(analisadorLexico.linha+1+":token nao esperado["+analisadorLexico.lexema+"]");
			System.exit(0);
		}
	}
	
	
	
	public void Proc_S() {
		while(token == Token.INTEGER || token == Token.BOOLEAN || token == Token.BYTE || token == Token.STRING || token == Token.CONST) {
			Proc_D();
		}
		if(token == Token.MAIN) {
			CasaToken(Token.MAIN);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
				Proc_C();
			}
			while((token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA)) {
				Proc_C();
			}
			if(token == Token.END) {
				CasaToken(Token.END);
			}
					
		}
		
	}
	
	public void Proc_D() {
		Tipo tipo;
		ElementoTabelaSimbolo elementoId;
		boolean flag = false;
		if(token == Token.INTEGER || token == Token.BOOLEAN || token == Token.BYTE || token == Token.STRING) {
			tipo = IdentificaTipo();
			CasaToken(IdentificaToken_D());
			elementoId = Regra1(tipo);
			CasaToken(Token.ID);			
			if(token == Token.ATRIBUICAO) {
				CasaToken(Token.ATRIBUICAO);
				if(token == Token.MENOS || token == Token.MAIS) {
					if(token == Token.MENOS) {
						flag = Regra20();
					}
					CasaToken(VerificaMaisMenos(token));
				}
				Regra2(elementoId, flag);
				CasaToken(Token.CONSTANTE);
			}
			while(token == Token.VIRGULA) {
				CasaToken(Token.VIRGULA);
				//elementoId = Regra1();
				CasaToken(Token.ID);
				if(token == Token.ATRIBUICAO) {
					CasaToken(Token.ATRIBUICAO);
					if(token == Token.MENOS || token == Token.MAIS) {
						if(token == Token.MENOS) {
							flag = Regra20();
						}							
						CasaToken(VerificaMaisMenos(token));
					}
					Regra2(elementoId, flag);
					CasaToken(Token.CONSTANTE);
				}
			}
			CasaToken(Token.PONTO_VIRGULA);
			
		}else if(token == Token.CONST) {
			CasaToken(Token.CONST);
			elementoId = Regra3();
			CasaToken(Token.ID);
			CasaToken(Token.ATRIBUICAO);
			if(token == Token.MENOS || token == Token.MAIS) {
				if(token == Token.MENOS) {
					flag = Regra20();
				}
				CasaToken(VerificaMaisMenos(token));
			}
			Regra2(elementoId, flag);
			CasaToken(Token.CONSTANTE);
			CasaToken(Token.PONTO_VIRGULA);
		}
	}
	
	private Token VerificaMaisMenos(Token token) {
		if(token == Token.MENOS) {
			return Token.MENOS;
		}else
			return Token.MAIS;
	}
	
	public void Proc_C() {
		ElementoTabelaSimbolo elementoId;
		Tipo tipo = Tipo.VAZIO;
		if(token == Token.ID) {
			elementoId = Regra4();
			CasaToken(Token.ID);
			CasaToken(Token.ATRIBUICAO);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				tipo = Proc_EXP();
				Regra5(elementoId, tipo);
			}
			CasaToken(Token.PONTO_VIRGULA);
		
		}else if(token == Token.WHILE) {
			CasaToken(Token.WHILE);
			CasaToken(Token.ABRE_PARENTESES);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Regra15();
				Proc_EXP();				
			}
			CasaToken(Token.FECHA_PARENTESES);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
				Proc_C();
				
			}else if(token == Token.BEGIN) {
				CasaToken(Token.BEGIN);
				while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
					Proc_C();
				}
				CasaToken(Token.END);
			}
		}else if(token == Token.IF) {
			CasaToken(Token.IF);
			CasaToken(Token.ABRE_PARENTESES);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {				
				Proc_EXP();
			}
			CasaToken(Token.FECHA_PARENTESES);
			CasaToken(Token.THEN);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.BEGIN) {
				Proc_C1();
			}			
			
		}else if(token == Token.READLN) {
			CasaToken(Token.READLN);
			CasaToken(Token.ABRE_PARENTESES);
			Regra26();
			CasaToken(Token.ID);
			CasaToken(Token.FECHA_PARENTESES);
			CasaToken(Token.PONTO_VIRGULA);
		
		}else if(token == Token.WRITE || token == Token.WRITELN) {
			CasaToken(IdentificaToken_C());
			CasaToken(Token.ABRE_PARENTESES);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				tipo = Regra30();
				Proc_EXP();					
			}
			while(token == Token.VIRGULA) {
				CasaToken(Token.VIRGULA);
				if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
					Regra31(tipo);
					Proc_EXP();
				}
			}
			CasaToken(Token.FECHA_PARENTESES);
			CasaToken(Token.PONTO_VIRGULA);					
		}else if(token == Token.PONTO_VIRGULA) {
			CasaToken(Token.PONTO_VIRGULA);
		}
	}
	
	public void Proc_C1() {
		if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
			Proc_C();
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
					Proc_C();
				}
				if(token == Token.IF) {
					CasaToken(Token.IF);
					CasaToken(Token.ABRE_PARENTESES);
					if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
						Regra15();
						Proc_EXP();								
					}
					CasaToken(Token.FECHA_PARENTESES);
					CasaToken(Token.THEN);
					CasaToken(Token.BEGIN);
					while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
						Proc_C();
					}
					CasaToken(Token.END);
					
					if(token == Token.ELSE) {
						CasaToken(Token.ELSE);
						CasaToken(Token.BEGIN);
						while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
							Proc_C();
						}
						CasaToken(Token.END);
					}
				}
			}
		}else if(token == Token.BEGIN) {
			CasaToken(Token.BEGIN);
			while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
				Proc_C();
			}
			CasaToken(Token.END);
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				Proc_C2();
			}
		}
	}
	
	public void Proc_C2() {
		if(token == Token.BEGIN) {
			CasaToken(Token.BEGIN);
			while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.PONTO_VIRGULA) {
				Proc_C();
			}
			CasaToken(Token.END);
		}else if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
			Proc_C();
			CasaToken(Token.THEN);
			CasaToken(Token.BEGIN);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
				Proc_C();
			}
			CasaToken(Token.END);
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				CasaToken(Token.BEGIN);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
					Proc_C();
				}
				CasaToken(Token.END);
			}
		}else if(token == Token.IF) {
			CasaToken(Token.IF);
			CasaToken(Token.ABRE_PARENTESES);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Regra15();
				Proc_EXP();								
			}
			CasaToken(Token.FECHA_PARENTESES);
			CasaToken(Token.THEN);
			CasaToken(Token.BEGIN);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
				Proc_C();
			}
			CasaToken(Token.END);
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				CasaToken(Token.BEGIN);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN|| token == Token.PONTO_VIRGULA) {
					Proc_C();
				}
				CasaToken(Token.END);
			}
		}
	}
	
	public Tipo Proc_EXP() {
		Tipo tipo = null;
		Token flag;
		if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
			tipo = Proc_EXPS();
		}
		if(token == Token.IGUAL || token == Token.DIFERENTE_DE || token == Token.MENOR_QUE || token == Token.MAIOR_QUE || token == Token.MENOR_OU_IGUAL_QUE || token == Token.MAIOR_OU_IGUAL_QUE) {
			flag = IdentificaToken_EXP();
			CasaToken(IdentificaToken_EXP());
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				tipo = Regra13(tipo, flag);
				Proc_EXPS();
								
			}else {
				System.out.println(analisadorLexico.linha+1+":token nao esperado["+analisadorLexico.lexema+"]");
				System.exit(0);
			}
		}
		return tipo;
	}
	
	public Tipo Proc_EXPS() {
		Tipo tipo = null;
		Token flag;
		if(token == Token.MAIS || token == Token.MENOS) {
			CasaToken(IdentificaToken_EXPS(false));			
		}
		if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
			tipo = Proc_T();			
		}		
		while(token == Token.MAIS || token == Token.MENOS || token == Token.OR) {
			flag = IdentificaToken_EXPS(true);
			CasaToken(IdentificaToken_EXPS(true));
			if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Regra12(tipo, flag);
				tipo = Proc_T();
			}else {
				System.out.println(analisadorLexico.linha+1+":token nao esperado["+analisadorLexico.lexema+"]");
				System.exit(0);
			}
		}
		return tipo;
	}
	
	public Tipo Proc_T() {
		Tipo tipo = null;
		Token flag;
		if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
			tipo = Proc_F();
		}
		while(token == Token.MULTIPLICACAO || token == Token.DIVISAO || token == Token.AND) {
			flag = IdentificaToken_T();
			CasaToken(IdentificaToken_T());
			if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				tipo = Proc_F();
			}else {
				System.out.println(analisadorLexico.linha+1+":token nao esperado["+analisadorLexico.lexema+"]");
				System.exit(0);
			}
		}
		return tipo;
	}
	
	public Tipo Proc_F() {
		Tipo tipo;
		if(token == Token.ABRE_PARENTESES) {
			CasaToken(Token.ABRE_PARENTESES);
			tipo = Proc_EXP();
			CasaToken(Token.FECHA_PARENTESES);
			return tipo;
		}else if(token == Token.NOT) {
			CasaToken(Token.NOT);
			tipo = Proc_F();
			return tipo;
		}else if(token == Token.ID) {
			tipo = analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema).tipo;
			CasaToken(Token.ID);
			return tipo;
		}else if(token == Token.CONSTANTE){
			tipo = analisadorLexico.registroLexico.tipo;
			CasaToken(Token.CONSTANTE);
			return tipo;
		}else {
			System.out.println(analisadorLexico.linha+1+":token nao esperado["+analisadorLexico.lexema+"]");
			System.exit(0);
			return null;
		}
		
	}
	
	public Token IdentificaToken_T() {
		
		if(token == Token.MULTIPLICACAO)
			return Token.MULTIPLICACAO;
		
		else if(token == Token.DIVISAO)
			return Token.DIVISAO;

		else if(token == Token.AND)
			return Token.AND;
		
		return Token.ERRO;
	}

	public Token IdentificaToken_EXPS(boolean incluiOr) {
		
		if(token == Token.MAIS)
			return Token.MAIS;
		
		else if(token == Token.MENOS)
			return Token.MENOS;

		else if(incluiOr && token == Token.OR)
			return Token.OR;
		
		return Token.ERRO;
	}
		
		
	public Token IdentificaToken_EXP() {
		
		if(token == Token.IGUAL)
			return Token.IGUAL;
		
		else if(token == Token.DIFERENTE_DE)
			return Token.DIFERENTE_DE;
		
		else if(token == Token.MENOR_QUE)
			return Token.MENOR_QUE;
		
		else if(token == Token.MAIOR_QUE)
			return Token.MAIOR_QUE;
		
		else if(token == Token.MENOR_OU_IGUAL_QUE)
			return Token.MENOR_OU_IGUAL_QUE;
		
		else if(token == Token.MAIOR_OU_IGUAL_QUE)
			return Token.MAIOR_OU_IGUAL_QUE;
		
		return Token.ERRO;
	}
	
	public Token IdentificaToken_D() {
		
		if(token == Token.INTEGER)
			return Token.INTEGER;
		
		else if(token == Token.BOOLEAN)
			return Token.BOOLEAN;
		
		else if(token == Token.BYTE)
			return Token.BYTE;
		
		else if(token == Token.STRING)
			return Token.STRING;
		
		return Token.ERRO;
		
	}
	
	public Tipo IdentificaTipo() {
		if(token == Token.INTEGER)
			return Tipo.INTEIRO;
		
		else if(token == Token.BOOLEAN)
			return Tipo.LOGICO;
		
		else if(token == Token.BYTE)
			return Tipo.BYTE;
		
		else if(token == Token.STRING)
			return Tipo.STRING;
		
		return Tipo.VAZIO;
	}
	
	public Token IdentificaToken_C() {
			
			if(token == Token.WRITE)
				return Token.WRITE;
			
			else if(token == Token.WRITELN)
				return Token.WRITELN;
			
			return Token.ERRO;
			
		}
	
	public ElementoTabelaSimbolo Regra1(Tipo tipo) {
		return analisadorLexico.tabelaSimbolos.AtualizarElemento(analisadorSemantico.Regra_1(analisadorLexico.registroLexico, analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema),tipo, analisadorLexico.linha));
	}
	
	public void Regra2(ElementoTabelaSimbolo elementoId, boolean flag) {
		analisadorLexico.tabelaSimbolos.AtualizarElemento(analisadorSemantico.Regra_2(flag, analisadorLexico.registroLexico, elementoId, analisadorLexico.linha));
	}
	
	public ElementoTabelaSimbolo Regra3() {
		return analisadorLexico.tabelaSimbolos.AtualizarElemento(analisadorSemantico.Regra_3(analisadorLexico.registroLexico, analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema), analisadorLexico.linha));
	}
	
	public ElementoTabelaSimbolo Regra4() {
		return analisadorLexico.tabelaSimbolos.AtualizarElemento(analisadorSemantico.Regra_4(analisadorLexico.registroLexico, analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema), analisadorLexico.linha));
	}
	
	public ElementoTabelaSimbolo Regra5(ElementoTabelaSimbolo elementoId, Tipo tipo) {
		return analisadorLexico.tabelaSimbolos.AtualizarElemento(analisadorSemantico.Regra_5(elementoId, tipo, analisadorLexico.linha));
	}
	
	public ElementoTabelaSimbolo Regra12(Tipo tipo, Token flag) {	
		return analisadorLexico.tabelaSimbolos.AtualizarElemento(analisadorSemantico.Regra_12(analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema), tipo, analisadorLexico.linha, flag));
	}
	
	public Tipo Regra13(Tipo tipo, Token flag) {
		ElementoTabelaSimbolo elemento = analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema);
		return analisadorSemantico.Regra_13(analisadorLexico.registroLexico, elemento, tipo, analisadorLexico.linha, flag);
	}
	
	public void Regra15() {
		analisadorSemantico.Regra_15(analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema), analisadorLexico.linha);
	}
	
	public void Regra26() {
		analisadorSemantico.Regra_26(analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema), analisadorLexico.linha);
	}
	
	public boolean Regra20() {
		return analisadorSemantico.Regra_20();
	}
	
	public Tipo Regra30() {
		return analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema).tipo;
	}
	
	public void Regra31(Tipo c) {
		analisadorSemantico.Regra_31(c, analisadorLexico.tabelaSimbolos.PesquisarNaTabela(analisadorLexico.registroLexico.lexema).tipo, analisadorLexico.linha);
	}
}
