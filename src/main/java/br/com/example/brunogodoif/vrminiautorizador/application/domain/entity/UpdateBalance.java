package br.com.example.brunogodoif.vrminiautorizador.application.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UpdateBalance {
    private String cardNumber;
    private BigDecimal amount;
}
