package br.com.wipro.cep.search.client.error;

public class CepNaoEncontradoProvedorBuscaException extends ProvedorBuscaException {

	private static final long serialVersionUID = 7640892020887519113L;

	public CepNaoEncontradoProvedorBuscaException() {
		super("CEP não encontrado, tente novamente.");
	}
}
