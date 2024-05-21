package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardPasswordException;

import java.util.regex.Pattern;

public class CardPassword {

    private static final Pattern DIGITS_ONLY = Pattern.compile("\\d+");

    private final String password;

    public CardPassword(String password) {
        if (password == null || password.isBlank())
            throw new InvalidCardPasswordException("A senha é obrigatória");

        if (!DIGITS_ONLY.matcher(password).matches() || password.length() != 4)
            throw new InvalidCardPasswordException("A senha deve conter apenas dígitos e ter exatamente 4 dígitos");

        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}