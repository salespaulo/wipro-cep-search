package br.com.wipro.cep.search.server;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConsultarEnderecoResposta {

	private String cep;
	private String rua;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private Float frete;
	
}
