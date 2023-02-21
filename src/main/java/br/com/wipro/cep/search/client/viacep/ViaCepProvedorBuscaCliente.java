package br.com.wipro.cep.search.client.viacep;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import br.com.wipro.cep.search.client.IProvedorBuscaEnderecoCliente;
import br.com.wipro.cep.search.client.error.CepInvalidoProvedorBuscaException;
import br.com.wipro.cep.search.client.error.CepNaoEncontradoProvedorBuscaException;
import br.com.wipro.cep.search.client.error.ProvedorBuscaException;

@Component
public class ViaCepProvedorBuscaCliente implements IProvedorBuscaEnderecoCliente {


	private static final String URL = "https://viacep.com.br/ws";
	private final RestTemplate restTemplate;

	public ViaCepProvedorBuscaCliente(@Autowired RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public ViaCepProvedorBuscaResposta buscarPorCep(String cep) throws ProvedorBuscaException {
		try {
			String cepSemFormat =  Optional.ofNullable(cep) //
					.map(c -> c.replaceAll("[^0-9]", "")) //
					.filter(c -> c.length() == 8) //
					.orElseThrow(() -> new CepInvalidoProvedorBuscaException());

			StringBuilder urlCompleta = new StringBuilder() //
					.append(URL) //
					.append("/") //
					.append(cepSemFormat) //
					.append("/json");

			ViaCepProvedorBuscaResposta resp = restTemplate.getForObject(urlCompleta.toString(),
					ViaCepProvedorBuscaResposta.class);

			if (resp.getErro() == null || !resp.getErro()) {
				return resp;
			}

			throw new CepNaoEncontradoProvedorBuscaException();
		} catch (HttpClientErrorException eRequest) {
			throw new CepInvalidoProvedorBuscaException(eRequest);
		} catch (HttpServerErrorException | UnknownHttpStatusCodeException e) {
			throw new ProvedorBuscaException("Erro desconhecido no servidor, tente novamente.", e);
		}
	}

}
