//package compiladores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
	public static void main(String[] args) throws IOException{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		AnalisadorLexico analisadorLexico = new AnalisadorLexico(1);
		
		int caracter = 0;
		String codigo = "";
		
		while((caracter = bf.read()) != -1) {
			if(caracter != 13){
				codigo += (char)caracter;
			}
		}
	    		
		codigo = analisadorLexico.Analisar(codigo);
		AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(analisadorLexico.registroLexico.token, codigo, analisadorLexico);
		analisadorSintatico.Proc_S();
	}
}
/*package compiladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {
	public static void main(String[] args) throws IOException{
		
	    Path wiki_path = Paths.get("C:/Users/Pedro/eclipse-workspace/compiladores/src/compiladores/", "exemplo6.l");
	    
		AnalisadorLexico analisadorLexico = new AnalisadorLexico(1);
		
		byte[] wikiArray = Files.readAllBytes(wiki_path); //lendo o arquivo
		byte[] codigoFonte = new byte[wikiArray.length]; //vetor de byte para retirar o '\r' da string
		
		int j = 0;
		for(int i = 0; i<wikiArray.length;i++) {
			if(wikiArray[i] != 13) { // caracter \r
				codigoFonte[j] = wikiArray[i];
				j++;
			}
		}
		
	    String codigo = new String(codigoFonte, "ASCII");
		codigo = analisadorLexico.Analisar(codigo);
		AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(analisadorLexico.registroLexico.token, codigo, analisadorLexico);
		analisadorSintatico.Proc_S();
	}
}*/