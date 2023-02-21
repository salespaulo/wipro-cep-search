package br.com.wipro.cep.search.client.viacep;

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

	private final String url;
	private final RestTemplate restTemplate;

	public ViaCepProvedorBuscaCliente(@Value("${cliente.url.viacep}") String url,
			@Autowired RestTemplate restTemplate) {
		this.url = url;
		this.restTemplate = restTemplate;
	}

	@Override
	public ViaCepProvedorBuscaResposta buscarPorCep(String cep) throws ProvedorBuscaException {
		try {
			StringBuilder urlCompleta = new StringBuilder().append(url).append("/").append(cep).append("/json");
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
