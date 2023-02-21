package br.com.wipro.cep.search.frete;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import br.com.wipro.cep.search.core.RegioesBrasileira;

@Component
public class TabelaDePrecoDoFretePadrao implements ITabelaDePrecoDoFrete {
	public static final String MOEDA_PADRAO = "BRL";

	private final Currency simboloDaMoeda;
	private Map<RegioesBrasileira, BigDecimal> tabela = new HashMap<>();

	public TabelaDePrecoDoFretePadrao() {
		this.simboloDaMoeda = Currency.getInstance(MOEDA_PADRAO);
		// valores padrão
		this.tabela.put(RegioesBrasileira.NORTE, new BigDecimal("20.83"));
		this.tabela.put(RegioesBrasileira.SUL, new BigDecimal("17.30"));
		this.tabela.put(RegioesBrasileira.NORDESTE, new BigDecimal("15.98"));
		this.tabela.put(RegioesBrasileira.SUDESTE, new BigDecimal("7.85"));
		this.tabela.put(RegioesBrasileira.CENTRO_OESTE, new BigDecimal("12.50"));
	}

	public BigDecimal buscarPorRegiao(RegioesBrasileira regiao) {
		Supplier<NullPointerException> semPrecoPorRegiao = () -> new NullPointerException(
				"Sem preço de frete configurado p/ regiao brasileira: " + regiao);

		int digitosDaMoeda = this.simboloDaMoeda.getDefaultFractionDigits();

		return Optional.ofNullable(tabela.get(regiao)) //
				.map(bd -> bd.setScale(digitosDaMoeda)) //
				.orElseThrow(semPrecoPorRegiao);
	}

	public void salvarPorRegiao(RegioesBrasileira regiao, BigDecimal valor) {
		Optional.ofNullable(valor) //
				.ifPresent(v -> tabela.put(regiao, valor));
	}

	public void limpar() {
		tabela.clear();
	}

	public String getSimboloDaMoeda() {
		return simboloDaMoeda.getSymbol();
	}

}
