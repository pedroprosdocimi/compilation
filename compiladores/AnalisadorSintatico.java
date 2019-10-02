//package compiladores;

public class AnalisadorSintatico {
	
	Token token;
	AnalisadorLexico analisadorLexico;
	String codigo;

	public AnalisadorSintatico(Token token, String codigo, AnalisadorLexico analisadorLexico) {
		this.token = token;
		this.codigo = codigo;
		this.analisadorLexico = analisadorLexico;
	}
	
	public void CasaToken(Token token_esperado) {
		System.out.println("Lexema: "+analisadorLexico.lexema+"  Token: "+token);
		if(token == token_esperado) {
			codigo = analisadorLexico.Analisar(codigo);
			token = analisadorLexico.registroLexico.token;
			
			
		}else {
			System.out.println(analisadorLexico.linha-1+":token nao esperado["+analisadorLexico.lexema+"]");
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
		if(token == Token.INTEGER || token == Token.BOOLEAN || token == Token.BYTE || token == Token.STRING) {
			
			CasaToken(IdentificaToken_D());
			CasaToken(Token.ID);
			if(token == Token.ATRIBUICAO) {
				CasaToken(Token.ATRIBUICAO);
				if(token == Token.MENOS) {
					CasaToken(Token.MENOS);
				}
				CasaToken(IdentificaConstante());
			}
			while(token == Token.VIRGULA) {
				CasaToken(Token.VIRGULA);
				CasaToken(Token.ID);
				if(token == Token.ATRIBUICAO) {
					CasaToken(Token.ATRIBUICAO);
					if(token == Token.MENOS) {
						CasaToken(Token.MENOS);
					}
					CasaToken(IdentificaConstante());
				}
			}
			CasaToken(Token.PONTO_VIRGULA);
			
		}else if(token == Token.CONST) {
			CasaToken(Token.CONST);
			CasaToken(Token.ID);
			CasaToken(Token.ATRIBUICAO);
			if(token == Token.MENOS) {
				CasaToken(Token.MENOS);
			}
			CasaToken(IdentificaConstante());
			CasaToken(Token.PONTO_VIRGULA);
		}
	}
	
	private Token IdentificaConstante() {
		if(token == Token.TRUE) {
			return Token.TRUE;
		}
		else if(token == Token.FALSE) {
			return Token.FALSE;
		}else {
			return Token.CONSTANTE;
		}
	}
	
	public void Proc_C() {
		if(token == Token.ID) {
			CasaToken(Token.ID);
			CasaToken(Token.ATRIBUICAO);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Proc_EXP();
			}
			CasaToken(Token.PONTO_VIRGULA);
		
		}else if(token == Token.WHILE) {
			CasaToken(Token.WHILE);
			CasaToken(Token.ABRE_PARENTESES);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
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
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
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
			CasaToken(Token.ID);
			CasaToken(Token.FECHA_PARENTESES);
			CasaToken(Token.PONTO_VIRGULA);
		
		}else if(token == Token.WRITE || token == Token.WRITELN) {
			CasaToken(IdentificaToken_C());
			CasaToken(Token.ABRE_PARENTESES);
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
				Proc_EXP();			
				
			}else CasaToken(Token.CONSTANTE);
			while(token == Token.VIRGULA) {
				CasaToken(Token.VIRGULA);
				if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
					Proc_EXP();			
					
				}else CasaToken(Token.CONSTANTE);
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
					if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
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
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
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
	
	public void Proc_EXP() {
		if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
			Proc_EXPS();
		}
		if(token == Token.IGUAL || token == Token.DIFERENTE_DE || token == Token.MENOR_QUE || token == Token.MAIOR_QUE || token == Token.MENOR_OU_IGUAL_QUE || token == Token.MAIOR_OU_IGUAL_QUE) {
			CasaToken(IdentificaToken_EXP());
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Proc_EXPS();
			}
		}
	}
	
	public void Proc_EXPS() {
		if(token == Token.MAIS || token == Token.MENOS) {
			CasaToken(IdentificaToken_EXPS(false));
		}
		if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
			Proc_T();
		}
		while(token == Token.MAIS || token == Token.MENOS || token == Token.OR) {
			CasaToken(IdentificaToken_EXPS(true));
			if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
				Proc_T();
			}
		}
	}
	
	public void Proc_T() {
		if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
			Proc_F();
		}
		while(token == Token.MULTIPLICACAO || token == Token.DIVISAO || token == Token.AND) {
			CasaToken(IdentificaToken_T());
			if(token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID || token == Token.TRUE || token == Token.FALSE) {
				Proc_F();
			}
		}
	}
	
	public void Proc_F() {
		if(token == Token.ABRE_PARENTESES) {
			CasaToken(Token.ABRE_PARENTESES);
			Proc_EXP();
			CasaToken(Token.FECHA_PARENTESES);
		
		}else if(token == Token.NOT) {
			CasaToken(Token.NOT);
			Proc_F();
		}else if(token == Token.ID) {
			CasaToken(Token.ID);
		}else{
			CasaToken(IdentificaConstante());
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
	
public Token IdentificaToken_C() {
		
		if(token == Token.WRITE)
			return Token.WRITE;
		
		else if(token == Token.WRITELN)
			return Token.WRITELN;
		
		return Token.ERRO;
		
	}
}
