package br.com.wipro.cep.search.client.error;

public class CepInvalidoProvedorBuscaException extends ProvedorBuscaException {

	public static final String MSG = "CEP inválido, é obrigatório ter 8 números.";

	private static final long serialVersionUID = 5116092200013304925L;

	public CepInvalidoProvedorBuscaException() {
		super(MSG);
	}

	public CepInvalidoProvedorBuscaException(Exception cause) {
		super(MSG, cause);
	}

}
