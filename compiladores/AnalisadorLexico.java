package compiladores;

public class AnalisadorLexico {	
	
	int estadoInicial;
	int estadoFinal;
	int estadoAtual;
	int linha;
	int pos;
	String lexema;
	RegistroLexico registroLexico;
	TabelaDeSimbolos tabelaSimbolos;
	
	public AnalisadorLexico(int linha) {
		this.estadoInicial = 0;
		this.estadoFinal = 13;
		this.estadoAtual = estadoInicial;
		this.linha = linha;
		this.lexema = "";
		this.tabelaSimbolos = new TabelaDeSimbolos();
	}
	
	public boolean ehLetra(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}

	public static boolean ehDigito(char c) {
		return (c >= '0' && c <= '9');
	}

	public static boolean ehAritimetico(char c) {
		return (c == '+' || c == '-' || c == '/' || c == '*');
	}

	private static boolean ehHexadecimal(char c){
		return ('0' <= c && c <= '9') || ('A' <= c && c <= 'F');
	}

	private boolean caracterValido(char c){
		return ehLetra(c) || ehDigito(c) || (8 <= c && c <= 11) || (32 <= c && c <= 33) || (38 <= c && c <= 47) || (58 <= c && c <= 63) || 
				(c == 91 || c == 93|| c == 95) || (c == 96) || (c == 123) || (c == 125);
	}	

	
	public String Analisar(String t){
		
		int i=0;
		char c;
		estadoAtual = 0;
		pos = 0;
		lexema = "";
		
		while(estadoAtual != estadoFinal){
			
			c = t.charAt(i);
			//System.out.print(c);
			pos++;
			if(c == 10) linha++;
			
			if(c == 0) return "";
			
			if(!caracterValido(c)) {
				System.out.println(linha+":caractere invalido.");
				break;
			}
			
			switch (estadoAtual) {
				
				case 0:
					
					estadoAtual = Caso_0(c);
					break;
					
				case 1:
					
		            estadoAtual = Caso_1(c);
		            break;
		            
				case 2:
					
		            estadoAtual = Caso_2(c);		                
		            break;
		            
				case 3:

					estadoAtual = Caso_3(c);
		            break;
		        
				case 4:

					estadoAtual = Caso_4(c);
		            break;
		            
				case 5:

					estadoAtual = Caso_5(c);
		            break; 
		            
				case 6:

					estadoAtual = Caso_6(c);
		            break; 
		            
				case 7:

					estadoAtual = Caso_7(c);               
		            break; 
		            
				case 8:

		            estadoAtual = Caso_8(c);
		            break; 
		            
				case 9:

					estadoAtual = Caso_9(c);
		            break;
		            
				case 10:

					estadoAtual = Caso_10(c);
		            break; 
		            
				case 11:

					estadoAtual = Caso_11(c);
					break; 
					
				case 12:

					estadoAtual = Caso_12(c);
					break;   
										
				case 14:

					estadoAtual = Caso_14(c);  
			        break; 
			        
				 case 15:

					 estadoAtual = Caso_15(c);
					 break;  
					 
				 case 16:
					 
					 estadoAtual = Caso_16(c);
					 break;
					 
				 default: 
					 pos--;
			}
			
			i++;			
		}
		return CodigoRestante(t, pos);
	}
	
	public String CodigoRestante(String codigoFonte, int tamLexema) {
		return codigoFonte.substring(tamLexema, codigoFonte.length());
	}
	
	public int Caso_0(char c) {		
                   
		//Se for \n ou ESPAÇO, continua no estado 
        if(c == 10 || c == 32){
        	return 0; 
        }
            
        else if (  c == ')' || c == '(' || c == ';' || c == ',' || c == '+' || c == '-' || c == '*' || c == 26){     // EOF == 26            
            lexema += c;
            registroLexico = InserirRegistroLexicoPorCaracterer(c); 
            return estadoFinal;
        }
            
        else if(c == '_'){
            lexema += c;
            return 1;
        }
            
        else if(ehLetra(c)){
            lexema += c;
            return 2;
        }
            
        else if (c == '0'){
            lexema += c;
            return 4; 
        }

        else if (ehDigito(c) && c != '0'){
            lexema += c;
            return 3; 
        }
            
        //lendo aspas simples (')
        else if (c == 39){
            lexema += c;
            return 7;
        }
            
        else if (c == '='){
            lexema += c;
            return 9;
        }
            
        else if (c == '<'){
            lexema += c;
            return 14;
        }
        
        else if (c == '>'){
            lexema += c;
            return 16;
        }
            
        else if (c == '!'){
            lexema += c;
            return 15;
        } 
            
        else if(c == '/'){
            return 10;                
        }
            
        else{
        	return -1; 
            //ERRO (caracter não é válido)
        }
        
		         
    }
	
	public int Caso_1(char c) {	
		
		if(c == '_'){
            lexema += c;
            return 1;
        }
		
        else if(ehLetra(c)|| ehDigito(c)){
            lexema += c;
            return 2;
        }
		
        else{
        	return -1; 
            //ERRO (caracter não é válido)
        } 
	}
	
	public int Caso_2(char c) {
		
		if(ehLetra(c)|| ehDigito(c) || c == '_'){
            lexema += c;
            return 2;
        }
		
        else{
        	
        	if(tabelaSimbolos.PesquisarNaTabela(lexema)==null)
        		tabelaSimbolos.InserirNaTabela(Token.ID, lexema);
        	
        	registroLexico = new RegistroLexico(Token.ID, lexema, tabelaSimbolos.PesquisarNaTabela(lexema), null);
        	Devolve();
        	
            return estadoFinal;            
        }
    }
	
	public int Caso_3(char c) {
		
		if(ehDigito(c)){
            lexema += c;
            return 3;
        }
		
        else{
        	
        	registroLexico = new RegistroLexico(Token.CONSTANTE, lexema, null, TipoConstante.INTEIRO);
        	
        	Devolve();
            return estadoFinal;
        }
    }
	
	public int Caso_4(char c) {
		
		if( ehDigito(c)){
            lexema += c;
            return 3;
        }
		
        else if(c == 'h'){
            lexema += c;
            return 5;
        }
		
        else{
        	
        	registroLexico = new RegistroLexico(Token.CONSTANTE, lexema, null, TipoConstante.INTEIRO);
        	Devolve();
        	
            return estadoFinal;
        }  
	}
	
	public int Caso_5(char c) {
		
        if(ehHexadecimal(c)){
            lexema += c;
            return 6;
        }
        
        return -1;  
	}
	
	public int Caso_6(char c) {
		
		if(ehHexadecimal(c)){
			
            lexema += c;
            
            registroLexico = new RegistroLexico(Token.CONSTANTE, lexema, null, TipoConstante.HEXA);
            
            return estadoFinal;
        }
		
		return -1;  
	}
	
	public int Caso_7(char c) {
		
		if(c == 39){ //Aspas Simples
            lexema += c;
            return 8;
        }
		
		if(c != 10 ||c != 26 || c != 39){ // Verificar se é \n ou EOF
            lexema += c;
            return 7;
        }		 
		
		return -1;  
    }
	
	
	public int Caso_8(char c) {
		
		if(c != 39){  //Aspas Simples
			
			registroLexico = new RegistroLexico(Token.CONSTANTE, lexema, null, TipoConstante.STRING);
			Devolve();
			
            return estadoFinal;
        }
		
        else if(c == 39){ //Aspas Simples
            lexema += c;
            return 7;
        } 
		
		return -1;  
	}
	
	public int Caso_9(char c) {
		
		if(c == '='){  
			
            lexema += c;
            registroLexico = new RegistroLexico(Token.IGUAL, lexema, null, null);
            
            return estadoFinal;
        }
		
        else if(c != '='){      
        	
        	registroLexico = new RegistroLexico(Token.ATRIBUICAO, lexema, null, null);
        	Devolve();
        	
            return estadoFinal;
        } 
		
		return -1;  
	}
	
	public int Caso_10(char c) {
		
		if(c != '/' && c != '*'){  
			
			registroLexico = new RegistroLexico(Token.DIVISAO, lexema, null, null);
			Devolve();
			
            return estadoFinal;
        }		

        else if(c == '*'){
            return 11;
        } 
		
		return -1;  
	}
	
	public int Caso_11(char c) {
		
		if(c != 26 && c != '*'){  //EOF
            return 11;
        }
		
        else if(c == '*'){
            return 12;
        } 
		
		return -1;  
    }
	
	
	public int Caso_12(char c) {
		
		if(c != '/'){  
            return 11;
        }
        
		if(c == '/'){
            return 0;
        }
		
		return -1;  
	}
	
	public void Caso_13(char c) { 
	}
	
	public int Caso_14(char c) {
		
		if(c == '='){  
			
            lexema += c;            
            registroLexico = new RegistroLexico(Token.MENOR_QUE, lexema, null, null);
            
            return estadoFinal;
        }
		
        else if(c != '='){
        	
        	registroLexico = new RegistroLexico(Token.MENOR_OU_IGUAL_QUE, lexema, null, null);
        	Devolve();
        	
            return estadoFinal;
        } 
		
		return -1;  
	}
	
	public int Caso_15(char c) {
		
		if(c == '='){  
			
            lexema += c;
            registroLexico = new RegistroLexico(Token.DIFERENTE_DE, lexema, null, null);
            
            return estadoFinal;
        }
		
		return -1;  
	}
	
	public int Caso_16(char c) {
		
		if(c == '='){  
			
            lexema += c;            
            registroLexico = new RegistroLexico(Token.MAIOR_QUE, lexema, null, null);
            
            return estadoFinal;
        }
		
        else if(c != '='){
        	
        	registroLexico = new RegistroLexico(Token.MAIOR_OU_IGUAL_QUE, lexema, null, null);
        	Devolve();
        	
            return estadoFinal;
        } 
		
		return -1;  
	}	
	
	public void Devolve() {
		pos--;
	}

	public RegistroLexico InserirRegistroLexicoPorCaracterer(char c) {
		
		if (c == ')')
			return new RegistroLexico(Token.FECHA_PARENTESES, ")", null, null);
		
		if (c == '(') 
			return new RegistroLexico(Token.ABRE_PARENTESES, "(", null, null);
		
		if (c == ';')
			return new RegistroLexico(Token.PONTO_VIRGULA, ";", null, null);
		
		if (c == ',')
			return new RegistroLexico(Token.VIRGULA, ",", null, null);
			
		if (c == '+')
			return new RegistroLexico(Token.MAIS, "+", null, null);
			
		if (c == '-')
			return new RegistroLexico(Token.MENOS, "-", null, null);
			
		if (c == '*') 
			return new RegistroLexico(Token.MULTIPLICACAO, "*", null, null);
		
		if (c == 26) 
			return new RegistroLexico(Token.EOF, "EOF", null, null);
		
		return new RegistroLexico(Token.ERRO, "", null, null);
		
	}
	
}



