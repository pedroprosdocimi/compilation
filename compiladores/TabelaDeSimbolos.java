//package compiladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TabelaDeSimbolos {	
	
	HashMap<String, Integer> tabelaSimbolos;
	List<ElementoTabelaSimbolo> palavrasReservadas;
	
	public TabelaDeSimbolos() {
		
		this.tabelaSimbolos = new HashMap<String, Integer>();
		this.palavrasReservadas = DefinirPalavrasReservadas();
		
		for(int i = 0; i < palavrasReservadas.size(); i++) {
	    	tabelaSimbolos.put(palavrasReservadas.get(i).lexema, palavrasReservadas.get(i).token.ordinal());
	    }
		
		// Integer pos = InserirNaTabela(38, "id123", tabelaSimbolos);	    
	    //System.out.println(pos);   
	    //System.out.println(tabelaSimbolos);
	}	 
	 	 
	 public Integer InserirNaTabela(Token token, String lexema) {
		 
		 tabelaSimbolos.put(lexema, token.ordinal());
		 return tabelaSimbolos.get(lexema);
	 }
	 
	 public Integer PesquisarNaTabela(String lexema) {
		 
		 if(tabelaSimbolos.get(lexema) != null)
			 return tabelaSimbolos.get(lexema);
		 else 
			 return null;
	 }	 
	 
	 public Token RetornaToken(Integer pos) {
		 
		 if(pos == 3) 
			 return Token.CONST;
		 
		 else if(pos == 4) 
			 return Token.INTEGER;
		 
		 else if(pos == 5) 
			 return Token.BYTE;
		 
		 else if(pos == 6) 
			 return Token.STRING;
		 
		 else if(pos == 7) 
			 return Token.WHILE;
		 
		 else if(pos == 8) 
			 return Token.IF;
		 
		 else if(pos == 9) 
			 return Token.ELSE;
		 
		 else if(pos == 10) 
			 return Token.AND;
		 
		 else if(pos == 11) 
			 return Token.OR;
		 
		 else if(pos == 12) 
			 return Token.NOT;
		 
		 else if(pos == 28) 
			 return Token.BEGIN;
		 
		 else if(pos == 29) 
			 return Token.END;
		 
		 else if(pos == 30) 
			 return Token.THEN;
		 
		 else if(pos == 31) 
			 return Token.READLN;
		 
		 else if(pos == 32) 
			 return Token.MAIN;
		 
		 else if(pos == 33) 
			 return Token.WRITE;
		 
		 else if(pos == 34) 
			 return Token.WRITELN;
		 
		 else if(pos == 35) 
			 return Token.TRUE;
		 
		 else if(pos == 36) 
			 return Token.FALSE;
		 
		 else if(pos == 37) 
			 return Token.BOOLEAN;		 
		 
		 else
			 return Token.ID;
		 
	 }
	 
	 private static List<ElementoTabelaSimbolo> DefinirPalavrasReservadas(){
		 
		 List<ElementoTabelaSimbolo> tabelaSimbolos = new ArrayList<>();			 
		 
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.CONST, "const"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.INTEGER, "integer"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.BYTE, "byte"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.STRING, "string"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.WHILE, "while"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.IF, "if"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.ELSE, "else"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.AND, "and"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.OR, "or"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.NOT, "not"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.IGUAL, "="));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.ATRIBUICAO, "=="));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.ABRE_PARENTESES, "("));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.FECHA_PARENTESES, ")"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MENOR_QUE, "<"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIOR_QUE, ">"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.DIFERENTE_DE, "!="));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIOR_OU_IGUAL_QUE, ">="));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MENOR_OU_IGUAL_QUE, "<="));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.VIRGULA, ","));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIS, "+"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MENOS, "-"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MULTIPLICACAO, "*"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.DIVISAO, "/"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.PONTO_VIRGULA, ";"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.BEGIN, "begin"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.END, "end"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.THEN, "then"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.READLN, "readln"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIN, "main"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.WRITE, "write"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.WRITELN, "writeln"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.TRUE, "true"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.FALSE, "false"));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.BOOLEAN, "boolean"));
		 
		 return tabelaSimbolos;
	 }
}