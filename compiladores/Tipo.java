package compiladores;

public enum Tipo {

		INTEIRO(1),
		LOGICO(2),
		BYTE(3),
		STRING(4),
		VAZIO(5);
		
		public final Integer indice;
		
		private Tipo(Integer indice) {
			this.indice = indice;
		}

}
