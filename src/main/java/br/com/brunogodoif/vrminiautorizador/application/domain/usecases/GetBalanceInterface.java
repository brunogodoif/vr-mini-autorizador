package br.com.brunogodoif.vrminiautorizador.application.domain.usecases;

import java.math.BigDecimal;

public interface GetBalanceInterface {
    BigDecimal execute(String cardNumber);
}
