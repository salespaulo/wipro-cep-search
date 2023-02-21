package br.com.wipro.cep.search.frete.calc;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.wipro.cep.search.client.IProvedorBuscaEnderecoCliente;
import br.com.wipro.cep.search.client.error.ProvedorBuscaException;
import br.com.wipro.cep.search.client.viacep.ViaCepProvedorBuscaResposta;
import br.com.wipro.cep.search.server.ConsultarEnderecoResposta;

@Component
public class CalculadoraDeFreteServico implements ICalculadoraDeFreteServico {

	private final ICalculadoraDeFrete calculadora;
	private final IProvedorBuscaEnderecoCliente provedor;

	public CalculadoraDeFreteServico(IProvedorBuscaEnderecoCliente provedor, ICalculadoraDeFrete calculadora) {
		this.provedor = provedor;
		this.calculadora = calculadora;
	}

	public ConsultarEnderecoResposta calcularPorCep(String cep) throws ProvedorBuscaException {
		ViaCepProvedorBuscaResposta resposta = provedor.buscarPorCep(cep);
		BigDecimal frete = calculadora.calcularPorEstado(resposta.getUf());

		return ConsultarEnderecoResposta.builder() //
				.cep(resposta.getCep()) //
				.rua(resposta.getLogradouro()) //
				.complemento(resposta.getComplemento()) //
				.bairro(resposta.getBairro()) //
				.cidade(resposta.getLocalidade()) //
				.estado(resposta.getUf()) //
				.frete(frete.floatValue()) //
				.build();
	}

}
