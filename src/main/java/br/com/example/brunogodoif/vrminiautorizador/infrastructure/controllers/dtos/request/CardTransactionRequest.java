package br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardTransactionRequest {

    @NotBlank(message = "O número do cartão é obrigatório")
    @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas dígitos")
    @Size(min = 16, max = 16, message = "O número do cartão deve ter exatamente 16 dígitos")
    @Schema(description = "Número do cartão", example = "1234567890123456")
    private final String numeroCartao;

    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "\\d+", message = "A senha deve conter apenas dígitos")
    @Size(min = 4, max = 4, message = "A senha deve ter exatamente 4 dígitos")
    @Schema(description = "Senha do cartão", example = "1234")
    private final String senhaCartao;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior a 0.00")
    @Schema(description = "Valor da transação", example = "100.00")
    private double valor;
}