package compiladores;

public class AnalisadorSintatico {
	
	Token token;
	AnalisadorLexico analisadorLexico = new AnalisadorLexico(1);
	String codigo;

	public void CasaToken(Token token_esperado) {
		if(token == token_esperado) {
			token = token_esperado;
			codigo = analisadorLexico.Analisar(codigo);
		}else {
			System.out.println("Erro Sintatico");
		}
	}
	
	public void Proc_S() {
		while(token == Token.INTEGER || token == Token.BOOLEAN || token == Token.BYTE || token == Token.STRING || token == Token.CONST) {
			Proc_D();
			if(token == Token.MAIN) {
				CasaToken(Token.MAIN);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
					Proc_C();
				}
				while((token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN)) {
					Proc_C();
				}
				if(token == Token.END) {
					CasaToken(Token.END);
				}
					
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
				CasaToken(Token.CONSTANTE);
			}
			while(token == Token.VIRGULA) {
				CasaToken(Token.VIRGULA);
				CasaToken(Token.ID);
				if(token == Token.ATRIBUICAO) {
					CasaToken(Token.ATRIBUICAO);
					if(token == Token.MENOS) {
						CasaToken(Token.MENOS);
					}
					CasaToken(Token.CONSTANTE);
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
			CasaToken(Token.CONSTANTE);
			CasaToken(Token.PONTO_VIRGULA);
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
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Proc_EXP();
			}
			CasaToken(Token.FECHA_PARENTESES);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
				Proc_C();
				
			}else if(token == Token.BEGIN) {
				CasaToken(Token.BEGIN);
				while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
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
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN || token == Token.THEN) {
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
			if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
				Proc_EXP();			
				
			}else CasaToken(Token.CONSTANTE);
			while(token == Token.VIRGULA) {
				CasaToken(Token.VIRGULA);
				if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
					Proc_EXP();			
					
				}else CasaToken(Token.CONSTANTE);
			}
			CasaToken(Token.FECHA_PARENTESES);
			CasaToken(Token.PONTO_VIRGULA);					
		}
	}
	
	public void Proc_C1() {
		if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
			Proc_C();
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
					Proc_C();
				}
				if(token == Token.IF) {
					CasaToken(Token.IF);
					CasaToken(Token.ABRE_PARENTESES);
					if(token == Token.MAIS || token == Token.MENOS || token == Token.ABRE_PARENTESES || token == Token.NOT || token == Token.CONSTANTE || token == Token.ID) {
						Proc_EXP();								
					}
					CasaToken(Token.FECHA_PARENTESES);
					CasaToken(Token.THEN);
					CasaToken(Token.BEGIN);
					while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
						Proc_C();
					}
					CasaToken(Token.END);
					
					if(token == Token.ELSE) {
						CasaToken(Token.ELSE);
						CasaToken(Token.BEGIN);
						while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
							Proc_C();
						}
						CasaToken(Token.END);
					}
				}
			}
		}else if(token == Token.THEN) {
			CasaToken(Token.THEN);
			CasaToken(Token.BEGIN);
			while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
				Proc_C();
			}
			CasaToken(Token.END);
			if(token == Token.ELSE) {
				Proc_C2();
			}
		}
	}
	
	public void Proc_C2() {
		if(token == Token.BEGIN) {
			CasaToken(Token.BEGIN);
			while(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
				Proc_C();
			}
			CasaToken(Token.END);
		}else if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
			Proc_C();
			CasaToken(Token.THEN);
			CasaToken(Token.BEGIN);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
				Proc_C();
			}
			CasaToken(Token.END);
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				CasaToken(Token.BEGIN);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
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
			CasaToken(Token.BEGIN);
			if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
				Proc_C();
			}
			CasaToken(Token.END);
			if(token == Token.ELSE) {
				CasaToken(Token.ELSE);
				CasaToken(Token.BEGIN);
				if(token == Token.ID || token == Token.WHILE || token == Token.IF || token == Token.READLN || token == Token.WRITE || token == Token.WRITELN) {
					Proc_C();
				}
				CasaToken(Token.END);
			}
		}
	}
	
	public void Proc_EXP() {
		
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
