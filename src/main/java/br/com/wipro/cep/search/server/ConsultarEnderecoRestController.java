package br.com.wipro.cep.search.server;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wipro.cep.search.client.error.ProvedorBuscaException;
import br.com.wipro.cep.search.frete.calc.ICalculadoraDeFreteServico;
import lombok.Builder;
import lombok.Getter;

@RestController
public class ConsultarEnderecoRestController {

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	private ICalculadoraDeFreteServico servicoCalculadoraDeFrete;

	@Getter
	@Builder
	static class StatusServerResponse {
		private String ip;
		private String serverPort;
	}

	@GetMapping(value = "/")
	public @ResponseBody StatusServerResponse getStatusServer() {
		return StatusServerResponse.builder() //
				.serverPort(serverPort) //
				.ip(InetAddress.getLoopbackAddress() //
						.getHostAddress()) //
				.build();
	}

	@PostMapping(value = "/consulta-endereco")
	public @ResponseBody ConsultarEnderecoResposta consultarEndereco(@RequestBody ConsultarEnderecoRequisicao req) throws ProvedorBuscaException {
			return servicoCalculadoraDeFrete.calcularPorCep(req.getCep());
	}

}
