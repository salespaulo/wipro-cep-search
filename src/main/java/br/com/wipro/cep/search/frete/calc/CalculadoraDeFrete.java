package br.com.wipro.cep.search.frete.calc;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.wipro.cep.search.core.RegioesBrasileira;
import br.com.wipro.cep.search.frete.TabelaDePrecoDoFretePadrao;

@Component
public class CalculadoraDeFrete implements ICalculadoraDeFrete {

	private final TabelaDePrecoDoFretePadrao tabelaDePrecoDoFrete;

	public CalculadoraDeFrete(TabelaDePrecoDoFretePadrao tabelaDePrecoDoFrete) {
		this.tabelaDePrecoDoFrete = tabelaDePrecoDoFrete;
	}

	public BigDecimal calcularPorEstado(String estado) {
		RegioesBrasileira regiaoPorEstado = RegioesBrasileira.porEstado(estado);

		return tabelaDePrecoDoFrete.buscarPorRegiao(regiaoPorEstado);
	}

	public TabelaDePrecoDoFretePadrao getTabelaPrecoFrete() {
		return tabelaDePrecoDoFrete;
	}

}
