package com.br.cardvalidator;

import com.br.cardvalidator.dto.NewCardRequest;
import com.br.cardvalidator.repository.NewCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;




@WebMvcTest
@AutoConfigureMockMvc
public class NewCardControllerTest {

    @MockBean
    private NewCardRepository newCardRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSaveCard_InvalidFields() throws Exception {
        // ...
        String requestBody = "{\"numeroCartao\":\"1234567890\",\"nomeTitular\":\"\",\"typeCard\":\"DEBIT\"}";


        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0]").value("nomeTitular: must not be blank"));


    }
    @Test
    public void saveSucess() throws Exception {
        // ...
        String requestBody = "{\"numeroCartao\":\"1234567890\",\"nomeTitular\":\"JohnSenna\",\"typeCard\":\"DEBIT\"}";


        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cartão salvo com sucesso"));
    }
    @Test
    public void fieldCampBeNull() throws Exception {
        String requestBody = "{\"numeroCartao\":\"1234567890\",\"nomeTitular\":\"JohnSenna\",\"typeCard\":\"\"}";

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("O valor fornecido para o campo 'typeCard' é inválido"));

    }

}
