package br.com.wipro.cep.search.client;

import br.com.wipro.cep.search.client.error.ProvedorBuscaException;
import br.com.wipro.cep.search.client.viacep.ViaCepProvedorBuscaResposta;

public interface IProvedorBuscaEnderecoCliente {

	public ViaCepProvedorBuscaResposta buscarPorCep(String cep) throws ProvedorBuscaException;

}
