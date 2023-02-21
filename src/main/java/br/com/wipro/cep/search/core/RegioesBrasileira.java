package br.com.wipro.cep.search.core;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum RegioesBrasileira {
	SUL("PR", "SC", "RS"), //
	NORTE("AC", "RO", "AM", "PA", "TO", "AP", "RR"), //
	NORDESTE("MA", "PI", "CE", "RN", "PB", "PE", "AL", "SE", "BA"), //
	SUDESTE("SP", "RJ", "ES", "MG"), //
	CENTRO_OESTE("MT", "GO", "GO", "DF");

	private List<String> estados;

	RegioesBrasileira(String... estados) {
		this.estados = Arrays.asList(estados);
	}

	public final static RegioesBrasileira porEstado(String estado) {
		Stream<RegioesBrasileira> streamDeRegioes = Stream.of(RegioesBrasileira.values());
		Predicate<RegioesBrasileira> regiaoPorEstado = regiao -> regiao.estados.contains(estado);

		return streamDeRegioes //
				.filter(regiaoPorEstado) //
				.findFirst() //
				.orElseThrow(() -> //
					new NullPointerException("Região brasileira não encontrada para o estado: " + estado));
	}
}