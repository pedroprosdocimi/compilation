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
	
	public ElementoTabelaSimbolo Regra_2(boolean flag, RegistroLexico registro, ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.classe == Classe.CONST) {
			elemento.tipo = registro.tipo;
		}else if(flag) {
			if(elemento.tipo != Tipo.INTEIRO && elemento.tipo != Tipo.BYTE) {
				System.out.println(linha+":tipos incompatíveis");
				System.exit(0);
			}				
		}
		else {
			if(elemento.classe == Classe.VAZIO) {
				System.out.println(linha+":identificador nao declarado["+elemento.lexema+"]");
				System.exit(0);
			}else if(elemento.tipo != registro.tipo) {
				System.out.println(linha+":tipos incompatíveis");
				System.exit(0);
			}
		}
		return elemento;
	}
	
	public ElementoTabelaSimbolo Regra_3(RegistroLexico registro, ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.classe != Classe.VAZIO) {
			System.out.println(linha+":identificador ja declarado ["+elemento.lexema+"]");
			System.exit(0);
		}else {
			elemento.classe = Classe.CONST;
			elemento.tipo = registro.tipo;
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
		if(elemento.tipo == Tipo.INTEIRO && tipo == Tipo.BYTE) {
			
		}else if(elemento.tipo == tipo) {
			
		}
		else {
			System.out.println(linha+":tipos incompatíveis.");
			System.exit(0);
		}
		return elemento;
	}
	
	public ElementoTabelaSimbolo Regra_12(RegistroLexico registro, ElementoTabelaSimbolo elemento, Tipo tipo, int linha, Token flag) {		
		if(registro.token == Token.ID) {
			if(flag  == Token.MAIS || flag  == Token.MENOS) {
			   if((tipo == Tipo.INTEIRO && elemento.tipo == Tipo.INTEIRO)||( tipo == Tipo.BYTE && elemento.tipo == Tipo.INTEIRO)||(tipo == Tipo.INTEIRO && elemento.tipo == Tipo.BYTE)){		    
				   elemento.tipo = Tipo.INTEIRO;
			   }
			   else if(tipo == Tipo.STRING && elemento.tipo == Tipo.STRING) {
				   elemento.tipo = Tipo.STRING;
			   }
			   else if(tipo == Tipo.BYTE && elemento.tipo == Tipo.BYTE) {
				   elemento.tipo = Tipo.BYTE;
			   }
			   else {
				   System.out.println(linha+":tipos incompatíveis.");
				   System.exit(0);
			   }
			}
			else if(flag  == Token.OR) {
					if(tipo != Tipo.LOGICO || elemento.tipo != Tipo.LOGICO) {
						System.out.println(linha+":tipos incompatíveis.");
						System.exit(0);
					}
					else if(tipo == Tipo.LOGICO && elemento.tipo == Tipo.LOGICO) {
						elemento.tipo = Tipo.LOGICO;
					}
			}
		}
		else{
			if(flag  == Token.MAIS || flag  == Token.MENOS) {
			   if((tipo == Tipo.INTEIRO && registro.tipo == Tipo.INTEIRO)||( tipo == Tipo.BYTE && registro.tipo == Tipo.INTEIRO)||(tipo == Tipo.INTEIRO && registro.tipo == Tipo.BYTE)){		    
				   elemento.tipo = Tipo.INTEIRO;
			   }
			   else if(tipo == Tipo.STRING && registro.tipo == Tipo.STRING) {
				   elemento.tipo = Tipo.STRING;
			   }
			   else if(tipo == Tipo.BYTE && registro.tipo == Tipo.BYTE) {
				   elemento.tipo = Tipo.BYTE;
			   }
			   else {
				   System.out.println(linha+":tipos incompatíveis.");
				   System.exit(0);
			   }
			}
			else if(flag  == Token.OR) {
					if(tipo != Tipo.LOGICO || registro.tipo != Tipo.LOGICO) {
						System.out.println(linha+":tipos incompatíveis.");
						System.exit(0);
					}
					else if(tipo == Tipo.LOGICO && registro.tipo == Tipo.LOGICO) {
						elemento.tipo = Tipo.LOGICO;
					}
			}
		}
		return elemento;
	}
	
	public Tipo Regra_13(RegistroLexico registro,ElementoTabelaSimbolo elemento, Tipo tipo, int linha, Token flag) {		
		if(registro.token != Token.ID) {
			if(tipo == Tipo.STRING && registro.tipo == Tipo.STRING ) {
					if(flag == Token.ATRIBUICAO) {
						return Tipo.LOGICO;
				}
				else {
					System.out.println(linha+":tipos incompatíveis.");
					System.exit(0);
				}
			}
			else if((tipo == Tipo.INTEIRO && registro.tipo == Tipo.INTEIRO)||(tipo == Tipo.INTEIRO && registro.tipo == Tipo.BYTE)||(tipo == Tipo.BYTE && registro.tipo == Tipo.INTEIRO)||(tipo == Tipo.BYTE && registro.tipo == Tipo.BYTE)){
				return Tipo.LOGICO;
			}else {
				System.out.println(linha+":tipos incompatíveis.");
			    System.exit(0);
			}
		}else {
			if(tipo == Tipo.STRING && elemento.tipo == Tipo.STRING ) {
				if(flag == Token.ATRIBUICAO) {
					return Tipo.LOGICO;
				}
				else {
					System.out.println(linha+":tipos incompatíveis.");
					System.exit(0);
				}
			}
			else if((tipo == Tipo.INTEIRO && elemento.tipo == Tipo.INTEIRO)||(tipo == Tipo.INTEIRO && elemento.tipo == Tipo.BYTE)||(tipo == Tipo.BYTE && elemento.tipo == Tipo.INTEIRO)||(tipo == Tipo.BYTE && elemento.tipo == Tipo.BYTE)){
				return Tipo.LOGICO;
			}else {
				System.out.println(linha+":tipos incompatíveis.");
			    System.exit(0);
			}
		}
		
		return Tipo.VAZIO;
		
	}
	
	public ElementoTabelaSimbolo Regra_14(RegistroLexico registro,ElementoTabelaSimbolo elemento, Tipo tipo, int linha, Token flag) {
		  
		if(registro.token == Token.ID) {
			if(flag == Token.MULTIPLICACAO){
				   
				   if((tipo == Tipo.INTEIRO && elemento.tipo == Tipo.BYTE)||( tipo == Tipo.BYTE && elemento.tipo == Tipo.INTEIRO)|| (tipo == Tipo.INTEIRO && elemento.tipo == Tipo.INTEIRO)){		    
				    tipo = Tipo.INTEIRO;
				   }
				   else if (tipo == Tipo.BYTE && elemento.tipo == Tipo.BYTE) {
				    tipo = Tipo.BYTE;
				   }
				   else {
				    System.out.println(linha + ":tipos incompatíveis.");
				    System.exit(0);
				   }
				   
				   
				  }
				  else if(flag == Token.DIVISAO) {
				   
				   if((tipo == Tipo.INTEIRO && elemento.tipo == Tipo.BYTE)||(tipo == Tipo.BYTE && elemento.tipo == Tipo.INTEIRO)|| (tipo == Tipo.INTEIRO && elemento.tipo == Tipo.INTEIRO)||(tipo == Tipo.BYTE && elemento.tipo == Tipo.BYTE)) {
				    
				    tipo = Tipo.INTEIRO;
				   }
				   else {
				    System.out.println(linha + ":tipos incompatíveis.");
				    System.exit(0);
				   }
				   
				  }
				  else if(flag == Token.AND) {
				   if(tipo != Tipo.LOGICO || elemento.tipo != Tipo.LOGICO) {
				    System.out.println(linha + ":tipos incompatíveis.");
				    System.exit(0);
				   }
				   else if(tipo == Tipo.LOGICO && elemento.tipo == Tipo.LOGICO) {
				    tipo = elemento.tipo;
				   }
				  }
		}else {
			  if(flag == Token.MULTIPLICACAO){
			   
			   if((tipo == Tipo.INTEIRO && registro.tipo == Tipo.BYTE)||( tipo == Tipo.BYTE && registro.tipo == Tipo.INTEIRO)|| (tipo == Tipo.INTEIRO && registro.tipo == Tipo.INTEIRO)){		    
			    tipo = Tipo.INTEIRO;
			   }
			   else if (tipo == Tipo.BYTE && registro.tipo == Tipo.BYTE) {
			    tipo = Tipo.BYTE;
			   }
			   else {
			    System.out.println(linha + ":tipos incompatíveis.");
			    System.exit(0);
			   }
			   
			   
			  }
			  else if(flag == Token.DIVISAO) {
			   
			   if((tipo == Tipo.INTEIRO && registro.tipo == Tipo.BYTE)||(tipo == Tipo.BYTE && registro.tipo == Tipo.INTEIRO)|| (tipo == Tipo.INTEIRO && registro.tipo == Tipo.INTEIRO)||(tipo == Tipo.BYTE && registro.tipo == Tipo.BYTE)) {
			    
			    tipo = Tipo.INTEIRO;
			   }
			   else {
			    System.out.println(linha + ":tipos incompatíveis. REG14/");
			    System.exit(0);
			   }
			   
			  }
			  else if(flag == Token.AND) {
			   if(tipo != Tipo.LOGICO || registro.tipo != Tipo.LOGICO) {
			    System.out.println(linha + ":tipos incompatíveis. REG14AND");
			    System.exit(0);
			   }
			   else if(tipo == Tipo.LOGICO && registro.tipo == Tipo.LOGICO) {
			    tipo = registro.tipo;
			   }
			  }
		}
		  return elemento;
	}
	
	public void Regra_15(Tipo tipo, int linha) {
		if(tipo != Tipo.LOGICO) {
			System.out.println(linha+":tipos incompatíveis.");
			System.exit(0);
		}
	}
	
	public void Regra_26(ElementoTabelaSimbolo elemento, int linha) {
		if(elemento.classe != Classe.VAR) {
			System.out.println(linha+":classe de identificador incompatível["+elemento.lexema+"]");
		}
		if(elemento.tipo != Tipo.INTEIRO && elemento.tipo != Tipo.BYTE && elemento.tipo != Tipo.STRING) {
			System.out.println(linha+":tipos incompatíveis.");
			System.exit(0);
		}
	}
	
	public boolean Regra_20() {
		return true;
	}	
	
	public void Regra_31(Tipo c, Tipo exp, int linha) {
		if(exp == Tipo.STRING && exp == c) {
			
		}else if((exp == Tipo.INTEIRO || exp == Tipo.BYTE) && (c == Tipo.INTEIRO || c == Tipo.BYTE)) {
			
		}else {
			System.out.println(linha+":tipos incompatíveis.");
			System.exit(0);
		}
	}	
	
}
