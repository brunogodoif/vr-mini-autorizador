package br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases;

public interface GetBalanceInterface {
    Long execute(String cardNumber);
}
