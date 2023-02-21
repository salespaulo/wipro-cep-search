package br.com.wipro.cep.search.client.error;

public class ProvedorBuscaException extends Exception {
	
	private static final long serialVersionUID = -7634576456237281825L;

	public ProvedorBuscaException(String msg) {
		super(msg);
	}

	public ProvedorBuscaException(String msg, Exception cause) {
		super(msg, cause);
	}

}
