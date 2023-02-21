package br.com.wipro.cep.search.frete;

import java.math.BigDecimal;

import br.com.wipro.cep.search.core.RegioesBrasileira;

public interface ITabelaDePrecoDoFrete {

	BigDecimal buscarPorRegiao(RegioesBrasileira regiao);
	void salvarPorRegiao(RegioesBrasileira regiao, BigDecimal value);

}
