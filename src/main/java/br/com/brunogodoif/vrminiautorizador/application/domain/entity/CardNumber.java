package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardNumberException;

import java.util.regex.Pattern;

public class CardNumber {

    private static final Pattern DIGITS_ONLY = Pattern.compile("\\d+");

    private final String number;

    public CardNumber(String number) {
        if (number == null || number.isBlank()) {
            throw new InvalidCardNumberException("O número do cartão é obrigatório");
        }
        if (!DIGITS_ONLY.matcher(number).matches() || number.length() != 16) {
            throw new InvalidCardNumberException("O número do cartão deve conter apenas dígitos e ter exatamente 16 dígitos");
        }
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}