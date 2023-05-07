package com.br.cardvalidator;

import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoValidator {
    public boolean validarCartaoCredito(String numeroCartao) {
        // Remover caracteres não numéricos
        String numeroLimpo = numeroCartao.replaceAll("[^0-9]", "");

        // Verificar comprimento mínimo e máximo
        int comprimentoCartao = numeroLimpo.length();
        if (comprimentoCartao < 13 || comprimentoCartao > 19) {
            return false;
        }

        // Realizar validação do algoritmo de Luhn
        if (!validarLuhn(numeroLimpo)) {
            return false;
        }

        // Verificar o primeiro dígito do cartão
        int primeiroDigito = Character.getNumericValue(numeroLimpo.charAt(0));
        if (primeiroDigito != 4 && primeiroDigito != 5 && primeiroDigito != 6) {
            return false;
        }

        // Adicione outras regras de validação, se necessário

        return true; // Cartão válido
    }

    private boolean validarLuhn(String numeroCartao) {
        int soma = 0;
        boolean alternar = false;

        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            alternar = !alternar;
        }

        return soma % 10 == 0;
    }
}

