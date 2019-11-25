package compiladores;

public enum Classe {	
		
		VAR(1),
		CONST(2),
		VAZIO(3);
		
		public final Integer indice;
		
		private Classe(Integer indice) {
			this.indice = indice;
		}

}
