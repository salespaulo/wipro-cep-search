package br.com.wipro.cep.search.server;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wipro.cep.search.client.error.CepInvalidoProvedorBuscaException;
import br.com.wipro.cep.search.client.error.CepNaoEncontradoProvedorBuscaException;

@SpringBootTest
@AutoConfigureMockMvc
public class TestConsultarEnderecoController {

	@Autowired
	private MockMvc mvc;

	@Test
	public void consultarEnderecoOK() throws Exception {
		String cep = "18190000";

		ConsultarEnderecoRequisicao requisicao = new ConsultarEnderecoRequisicao();
		requisicao.setCep(cep);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/consulta-endereco")
				.content(asJsonString(requisicao)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request).andExpect(status().isOk()) //
				.andExpect(jsonPath("$.cep").exists()) //
				.andExpect(jsonPath("$.rua").exists()) //
				.andExpect(jsonPath("$.complemento").exists()) //
				.andExpect(jsonPath("$.cidade").exists()) //
				.andExpect(jsonPath("$.estado").exists()) //
				.andExpect(jsonPath("$.estado").value("SP")) //
				.andExpect(jsonPath("$.frete").exists()) //
				.andExpect(jsonPath("$.frete").isNumber()) //
				.andExpect(jsonPath("$.frete").value(7.85)) //
		;
	}

	@Test
	public void consultarEnderecoCepInvalido() throws Exception {
		String cep = "181900A";

		ConsultarEnderecoRequisicao requisicao = new ConsultarEnderecoRequisicao();
		requisicao.setCep(cep);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/consulta-endereco")
				.content(asJsonString(requisicao)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request).andExpect(status().isNotFound());
	}

	@Test
	public void consultarEnderecoCepNaoEncontrado() throws Exception {
		String cep = "18100000";

		ConsultarEnderecoRequisicao requisicao = new ConsultarEnderecoRequisicao();
		requisicao.setCep(cep);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/consulta-endereco")
				.content(asJsonString(requisicao)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request).andExpect(status().isNotFound());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static JsonPathResultMatchers jsonPath(String path) {
		return MockMvcResultMatchers.jsonPath(path);
	}

}
