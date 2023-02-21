package br.com.wipro.cep.search.client.error;

public class CepInvalidoProvedorBuscaException extends ProvedorBuscaException {
	
	private static final long serialVersionUID = 5116092200013304925L;

	public CepInvalidoProvedorBuscaException(Exception cause) {
		super("Formatação do número do CEP inválida, deve conter somente números.", cause);
	}

}
