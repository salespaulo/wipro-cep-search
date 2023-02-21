package br.com.wipro.cep.search.frete.calc;

import br.com.wipro.cep.search.client.error.ProvedorBuscaException;
import br.com.wipro.cep.search.server.ConsultarEnderecoResposta;

public interface ICalculadoraDeFreteServico {

	public ConsultarEnderecoResposta calcularPorCep(String cep) throws ProvedorBuscaException;

}
