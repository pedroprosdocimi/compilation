package compiladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;;

public class TabelaDeSimbolos {	
	
	HashMap<String, ElementoTabelaSimbolo> tabelaSimbolos;
	List<ElementoTabelaSimbolo> palavrasReservadas;
	
	public TabelaDeSimbolos() {
		
		this.tabelaSimbolos = new HashMap<String, ElementoTabelaSimbolo>();
		this.palavrasReservadas = DefinirPalavrasReservadas();
		
		for(int i = 0; i < palavrasReservadas.size(); i++) {
	    	tabelaSimbolos.put(palavrasReservadas.get(i).lexema, palavrasReservadas.get(i));
	    }
	}	 
	 	 
	 public ElementoTabelaSimbolo InserirNaTabela(Token token, String lexema, Classe classe, Tipo tipo) {
		 tabelaSimbolos.put(lexema, new ElementoTabelaSimbolo(token, lexema, classe, tipo));	 
		 return tabelaSimbolos.get(lexema);
	 }
	 
	 public ElementoTabelaSimbolo PesquisarNaTabela(String lexema) {
		 
		 if(tabelaSimbolos.get(lexema) != null)
			 return tabelaSimbolos.get(lexema);
		 else 
			 return null;
	 }	 
	 
	 public ElementoTabelaSimbolo AtualizarElemento(ElementoTabelaSimbolo elemento) {
		 tabelaSimbolos.put(elemento.lexema, elemento);
		 return tabelaSimbolos.get(elemento.lexema);
	 }
	 
	 public Tipo RetornaTipo(String lexema) {
		 if(lexema.equals("true") || lexema.equals("false"))
			 return Tipo.LOGICO;
		 
		 else if(lexema.equals("integer"))
			 return Tipo.INTEIRO;
		 
		 else if(lexema.equals("byte"))
			 return Tipo.BYTE;
		
		 else if(lexema.equals("string"))
			return Tipo.STRING;
					
			return Tipo.VAZIO;
	 }
	 public Token RetornaToken(String lexema) {
		 
		 if(lexema.equals("const")) 
			 return Token.CONST;
		 
		 else if(lexema.equals("integer")) 
			 return Token.INTEGER;
		 
		 else if(lexema.equals("byte")) 
			 return Token.BYTE;
		 
		 else if(lexema.equals("string")) 
			 return Token.STRING;
		 
		 else if(lexema.equals("while")) 
			 return Token.WHILE;
		 
		 else if(lexema.equals("if")) 
			 return Token.IF;
		 
		 else if(lexema.equals("else")) 
			 return Token.ELSE;
		 
		 else if(lexema.equals("and")) 
			 return Token.AND;
		 
		 else if(lexema.equals("or")) 
			 return Token.OR;
		 
		 else if(lexema.equals("not")) 
			 return Token.NOT;
		 
		 else if(lexema.equals("begin")) 
			 return Token.BEGIN;
		 
		 else if(lexema.equals("end")) 
			 return Token.END;
		 
		 else if(lexema.equals("then")) 
			 return Token.THEN;
		 
		 else if(lexema.equals("readln")) 
			 return Token.READLN;
		 
		 else if(lexema.equals("main")) 
			 return Token.MAIN;
		 
		 else if(lexema.equals("write")) 
			 return Token.WRITE;
		 
		 else if(lexema.equals("writeln")) 
			 return Token.WRITELN;
		 
		 else if(lexema.equals("boolean")) 
			 return Token.BOOLEAN;	
		 
		 else if(lexema.equals("true") || lexema.equals("false"))
			 return Token.CONSTANTE;
		 
		 else
			 return Token.ID;
		 
	 }
	 
	 private static List<ElementoTabelaSimbolo> DefinirPalavrasReservadas(){
		 
		 List<ElementoTabelaSimbolo> tabelaSimbolos = new ArrayList<>();			 
		 
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.CONST, "const", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.INTEGER, "integer", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.BYTE, "byte", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.STRING, "string", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.WHILE, "while", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.IF, "if", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.ELSE, "else", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.AND, "and", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.OR, "or", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.NOT, "not", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.IGUAL, "=", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.ATRIBUICAO, "==", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.ABRE_PARENTESES, "(", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.FECHA_PARENTESES, ")", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MENOR_QUE, "<", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIOR_QUE, ">", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.DIFERENTE_DE, "!=", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIOR_OU_IGUAL_QUE, ">=", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MENOR_OU_IGUAL_QUE, "<=", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.VIRGULA, ",", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIS, "+", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MENOS, "-", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MULTIPLICACAO, "*", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.DIVISAO, "/", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.PONTO_VIRGULA, ";", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.BEGIN, "begin", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.END, "end", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.THEN, "then", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.READLN, "readln", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.MAIN, "main", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.WRITE, "write", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.WRITELN, "writeln", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.CONSTANTE, "true", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.CONSTANTE, "false", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.BOOLEAN, "boolean", null, null));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.CONSTANTE, "false", null, Tipo.LOGICO));
		 tabelaSimbolos.add(new ElementoTabelaSimbolo(Token.CONSTANTE, "true", null, Tipo.LOGICO));
		 
		 return tabelaSimbolos;
	 }
}