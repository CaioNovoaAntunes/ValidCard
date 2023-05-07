package com.br.cardvalidator;

import com.br.cardvalidator.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
public class CartaoControllerTest {

    @MockBean
    private CartaoRepository cartaoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSalvarCartao() throws Exception {
        String requestBody = "{}"; // Corpo da requisição vazio para simular um campo vazio

        mockMvc.perform(MockMvcRequestBuilders.post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("O número do cartão não pode estar vazio"));
    }

    @Test
    public void testSalvarCartao_TitularNulo() throws Exception {
        String requestBody = "{\"numeroCartao\": \"1234567890\", \"nomeTitular\": olamundo}";

        mockMvc.perform(MockMvcRequestBuilders.post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("O campo 'titular' não pode ser nulo"));
    }


    @Test
    public void testSalvarCartao_TitularPresent() throws Exception {
        String requestBody = "{\"numeroCartao\": \"1234567890\", \"nomeTitular\": \"olamundo\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
